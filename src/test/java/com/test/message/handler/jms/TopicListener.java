package com.test.message.handler.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TopicListener implements MessageListener {

	private String receivedMessage;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = TextMessage.class.cast(message);
			receivedMessage = textMessage.getText();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public String getReceivedMessage() {
		return receivedMessage;
	}

}
