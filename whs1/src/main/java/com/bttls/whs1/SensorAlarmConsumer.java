package com.bttls.whs1;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class SensorAlarmConsumer {

	@KafkaListener(
		topics = "${com.bttls.whs1.topics.alarm.threshold}",
		groupId = "${spring.kafka.consumer.group-id}"
	)
	public void consume(NotificationMessage message) {
		log.info("Tragedy is bound to happen: {}", message);
	}
}
