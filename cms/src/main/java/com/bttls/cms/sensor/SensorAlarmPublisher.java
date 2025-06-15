package com.bttls.cms.sensor;

import com.bttls.cms.common.AbstractMessagePublisher;
import com.bttls.cms.sensor.service.NotificationMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SensorAlarmPublisher extends AbstractMessagePublisher<NotificationMessage> {

	public SensorAlarmPublisher(KafkaTemplate<String, Object> kafkaTemplate,
								@Value("${com.bttls.cms.topics.alarm.threshold}") String topic) {
		super(kafkaTemplate, topic);
	}
}
