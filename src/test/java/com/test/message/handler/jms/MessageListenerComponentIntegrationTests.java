package com.test.message.handler.jms;

import static org.junit.Assert.assertEquals;

import java.util.Objects;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.broker.BrokerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.message.handler.model.RequestRouteDto;
import com.test.message.handler.util.JaxbUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageListenerComponentIntegrationTests {

	private static final int MAX_ATTEMPTS = 3;
	private static final long WAIT_INTERVAL_TIME = 3000L;
	private static final String MESSAGE_TEST = "<UC_STOCK_LEVEL_IFD>\r\n" + 
			"\r\n" + 
			"<CTRL_SEG>\r\n" + 
			"\r\n" + 
			"<TRNNAM>UC_STOCK_LEVEL</TRNNAM>\r\n" + 
			"\r\n" + 
			"<TRNVER>20180100</TRNVER>\r\n" + 
			"\r\n" + 
			"<UUID>0de01919-81eb-4cc7-a51d-15f6085fc1a4</UUID>\r\n" + 
			"\r\n" + 
			"<WH_ID>WHS01</WH_ID>\r\n" + 
			"\r\n" + 
			"<CLIENT_ID>CLIE01</CLIENT_ID>\r\n" + 
			"\r\n" + 
			"<ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME>\r\n" + 
			"\r\n" + 
			"<REQUEST_ID>bc2a55e8-5a07-4af6-85fd-8290d3ccfb51</REQUEST_ID>\r\n" + 
			"\r\n" + 
			"<ROUTE_ID>186</ROUTE_ID>\r\n" + 
			"\r\n" + 
			"</CTRL_SEG>\r\n" + 
			"\r\n" + 
			"</UC_STOCK_LEVEL_IFD>";
	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
	@Value("${spring.activemq.broker-url-topic}")
	private String topicBrokerUrl;
	@Value("${topic.store}")
	private String topicDestination;
	@Value("${queue.stock}")
	private String queueDestination;
	@Autowired
	@Qualifier(JmsConfig.TOPIC_QUALIFIER)
	private JmsTemplate topicJmsTemplate;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	@Qualifier(JmsConfig.TOPIC_QUALIFIER)
	private ConnectionFactory topicConnectionFactory;
	private Connection topicConnection;
	private Session topicSession;
	private BrokerService broker;
	private TopicListener listener;

	@Before
	public void setUp() throws Exception {
		startBroker();
		topicConnection = topicConnectionFactory.createConnection();
		topicConnection.start();
		topicSession = topicConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	@Test
	public void shouldReceiveTopicFromQueue() throws Exception {
		consumesTopic();
		jmsTemplate.convertAndSend(queueDestination, MESSAGE_TEST);		
		waitForTopicMessage();
		
		RequestRouteDto requestRouteDto = JaxbUtils.unmarshal(listener.getReceivedMessage(), RequestRouteDto.class);
		RequestRouteDto expectedRequestRouteDto = new RequestRouteDto("bc2a55e8-5a07-4af6-85fd-8290d3ccfb51", 186L);
		assertEquals(expectedRequestRouteDto, requestRouteDto);
	}

	private void waitForTopicMessage() throws InterruptedException {
		int attempt = 0;
		while (Objects.isNull(listener.getReceivedMessage()) && ++attempt <= MAX_ATTEMPTS) {
			Thread.sleep(WAIT_INTERVAL_TIME);
		}
	}

	private void consumesTopic() throws JMSException {
		Topic topic = topicSession.createTopic(topicDestination);
		MessageConsumer consumer = topicSession.createConsumer(topic);
		listener = new TopicListener();
		consumer.setMessageListener(listener);
	}

	private void startBroker() throws Exception {
		broker = new BrokerService();
		broker.addConnector(brokerUrl);
		if (!Objects.equals(brokerUrl, topicBrokerUrl)) {
			broker.addConnector(topicBrokerUrl);
		}
		broker.setPersistent(false);
		broker.start();
	}

	@After
	public void tearDown() throws Exception {
		topicSession.close();
		topicConnection.close();
		broker.stop();
	}

}
