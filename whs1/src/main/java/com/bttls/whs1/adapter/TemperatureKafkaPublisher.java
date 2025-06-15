package com.bttls.whs1.adapter;

import com.bttls.kafka.AbstractMessagePublisher;
import com.bttls.whs1.sensor.message.Measurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TemperatureKafkaPublisher extends AbstractMessagePublisher<Measurement> {

	public TemperatureKafkaPublisher(KafkaTemplate<String, Object> kafkaTemplate,
									 @Value("${com.bttls.whs1.topics.sensor.temperature}") String topic) {
		super(kafkaTemplate, topic);
	}

}
