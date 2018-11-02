package com.test.message.handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MessageHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageHandlerApplication.class, args);
	}
}
