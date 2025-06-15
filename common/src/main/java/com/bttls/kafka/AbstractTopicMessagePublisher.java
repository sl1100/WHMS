package com.bttls.kafka;

import org.springframework.kafka.core.KafkaTemplate;

public class AbstractTopicMessagePublisher<T> extends AbstractMessagePublisher<T> {

	private final String topic;

	public AbstractTopicMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate, String topic) {
		super(kafkaTemplate);
		this.topic = topic;
	}

	public void sendMessage(T measurement) {
		kafkaTemplate.send(topic, measurement);
	}
}
