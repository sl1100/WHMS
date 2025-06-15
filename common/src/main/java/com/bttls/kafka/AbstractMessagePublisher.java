package com.bttls.kafka;

import org.springframework.kafka.core.KafkaTemplate;

public class AbstractMessagePublisher<T> {

	private final KafkaTemplate<String, Object> kafkaTemplate;
	private final String topic;

	public AbstractMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate, String topic) {
		this.kafkaTemplate = kafkaTemplate;
		this.topic = topic;
	}

	public void sendMessage(T measurement) {
		kafkaTemplate.send(topic, measurement);
	}
}
