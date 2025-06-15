package com.bttls.whs1.adapter;

import com.bttls.kafka.AbstractTopicMessagePublisher;
import com.bttls.whs1.sensor.message.Measurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class HumidityKafkaPublisher extends AbstractTopicMessagePublisher<Measurement> {

	public HumidityKafkaPublisher(KafkaTemplate<String, Object> kafkaTemplate,
								  @Value("${com.bttls.whs1.kafka.sensor.monitor.humidity.topic}") String topic) {
		super(kafkaTemplate, topic);
	}

}
