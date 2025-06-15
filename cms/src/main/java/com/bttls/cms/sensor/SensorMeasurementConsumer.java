package com.bttls.cms.sensor;

import com.bttls.cms.sensor.service.SensorMonitorService;
import com.bttls.kafka.AbstractKafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class SensorMeasurementConsumer extends AbstractKafkaConsumer<Measurement> {

	private final SensorMonitorService sensorMonitorService;

	@KafkaListener(
		topics = { "${com.bttls.cms.topics.sensor.temperature}", "${com.bttls.cms.topics.sensor.humidity}" },
		groupId = "${spring.kafka.consumer.group-id}"
	)
	@Override
	public void consume(Measurement message, Acknowledgment ack) {
		super.consume(message, ack);
	}

	@Override
	protected void consume(Measurement message) {
		sensorMonitorService.monitor(message);
	}
}
