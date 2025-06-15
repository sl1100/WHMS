package com.bttls.whs1;

import com.bttls.kafka.AbstractKafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class SensorAlarmConsumer extends AbstractKafkaConsumer<NotificationMessage> {

	@KafkaListener(
		topics = "${com.bttls.whs1.kafka.sensor.alarm.threshold.topic}",
		groupId = "${spring.kafka.consumer.group-id}"
	)
	@Override
	protected void consume(NotificationMessage message, Acknowledgment ack) {
		super.consume(message, ack);
	}

	@Override
	protected void consume(NotificationMessage message) {
		log.info("Tragedy is bound to happen: {}", message);
	}
}
