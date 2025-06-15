package com.bttls.cms.sensor;

import com.bttls.cms.sensor.service.SensorMonitorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class SensorMeasurementConsumer {

	private final SensorMonitorService sensorMonitorService;

	@KafkaListener(
		topics = { "${com.bttls.cms.topics.sensor.temperature}", "${com.bttls.cms.topics.sensor.humidity}" },
		groupId = "${spring.kafka.consumer.group-id}"
	)
	public void consume(Measurement message) {
		log.info("Received message: {}", message);
		sensorMonitorService.monitor(message);
	}
}
