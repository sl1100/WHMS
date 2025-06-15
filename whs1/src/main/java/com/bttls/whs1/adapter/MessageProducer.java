package com.bttls.whs1.adapter;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MessageProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;
	@Value("${com.bttls.whs1.topics.sensor.temperature}")
	private String topic;

	public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String message) {
		kafkaTemplate.send(topic, message);
	}
}
