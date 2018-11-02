package com.test.message.handler.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfig {

    public static final String TOPIC_QUALIFIER = "topic";
	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
    @Value("${spring.activemq.broker-url-topic}")
	private String topicBrokerUrl;    

	@Bean
    @Primary
	public ActiveMQConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(brokerUrl);
	}
	
	@Bean
	@Qualifier(TOPIC_QUALIFIER)
	public ActiveMQConnectionFactory connectionFactoryTopic() {
		return new ActiveMQConnectionFactory(topicBrokerUrl);
	}

	@Bean
    @Primary
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(connectionFactory());
	}

	@Bean
	@Qualifier(TOPIC_QUALIFIER)
	public JmsTemplate jmsTemplateTopic() {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactoryTopic());
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}
}
