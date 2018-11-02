package com.test.message.handler.constant;

public class ApplicationConfigurations {

	public static final String BROKER_URL = "${spring.activemq.broker-url}";
	public static final String BROKER_URL_TOPIC = "${spring.activemq.broker-url-topic}";
	public static final String TOPIC_STORE = "${topic.store}";
	public static final String QUEUE_STOCK = "${queue.stock}";
	
	private ApplicationConfigurations() {
	}
}
