package com.bttls.cms.sensor;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MessageConsumer {

	@KafkaListener(
		topics = "${com.bttls.cms.topics.sensor.temperature}",
		groupId = "${spring.kafka.consumer.group-id}"
	)
	public void listen(String message) {
		log.info("Received message: {}", message);
	}
}
