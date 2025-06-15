package com.bttls.kafka;

import org.springframework.kafka.core.KafkaTemplate;

public class AbstractMessagePublisher<T> {

	protected final KafkaTemplate<String, Object> kafkaTemplate;

	public AbstractMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String topic, T message) {
		kafkaTemplate.send(topic, message);
	}
}
