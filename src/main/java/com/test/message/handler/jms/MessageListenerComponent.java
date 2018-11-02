package com.test.message.handler.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.test.message.handler.constant.ApplicationConfigurations;
import com.test.message.handler.constant.QualifierConstants;
import com.test.message.handler.model.RequestRouteDto;
import com.test.message.handler.model.StockLevelDto;
import com.test.message.handler.parse.StockLevelDtoParser;
import com.test.message.handler.util.JaxbUtils;

@Component
public class MessageListenerComponent {

	@Value(ApplicationConfigurations.TOPIC_STORE)
	private String topicDestination;
	@Autowired
	@Qualifier(QualifierConstants.TOPIC)
	private JmsTemplate topicJmsTemplate;

	@JmsListener(destination = ApplicationConfigurations.QUEUE_STOCK)
	public void onReceiverQueue(String message) throws Exception {
		StockLevelDto stockLevelDto = JaxbUtils.unmarshal(message, StockLevelDto.class);
		RequestRouteDto requestRouteDto = StockLevelDtoParser.parseRequestRouteDto(stockLevelDto);
		String xml = JaxbUtils.marshal(requestRouteDto);
		topicJmsTemplate.convertAndSend(topicDestination, xml);
	}

}