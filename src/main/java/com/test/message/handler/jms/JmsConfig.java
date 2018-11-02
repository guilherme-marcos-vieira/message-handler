package com.test.message.handler.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.test.message.handler.constant.ApplicationConfigurations;
import com.test.message.handler.constant.QualifierConstants;

@Configuration
@EnableJms
public class JmsConfig {

	@Value(ApplicationConfigurations.BROKER_URL)
	private String brokerUrl;
	@Value(ApplicationConfigurations.BROKER_URL_TOPIC)
	private String topicBrokerUrl;

	@Bean
	@Primary
	public ActiveMQConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(brokerUrl);
	}

	@Bean
	@Qualifier(QualifierConstants.TOPIC)
	public ActiveMQConnectionFactory connectionFactoryTopic() {
		return new ActiveMQConnectionFactory(topicBrokerUrl);
	}

	@Bean
	@Primary
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(connectionFactory());
	}

	@Bean
	@Qualifier(QualifierConstants.TOPIC)
	public JmsTemplate jmsTemplateTopic() {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactoryTopic());
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}
}
