package com.bttls.cms.sensor;

import com.bttls.cms.sensor.model.AlarmMessage;
import com.bttls.kafka.AbstractMessagePublisher;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SensorAlarmPublisher extends AbstractMessagePublisher<AlarmMessage> {

	public SensorAlarmPublisher(KafkaTemplate<String, Object> kafkaTemplate,
								@Value("${com.bttls.kafka.topics.alarm.threshold}") String topic) {
		super(kafkaTemplate, topic);
	}
}
