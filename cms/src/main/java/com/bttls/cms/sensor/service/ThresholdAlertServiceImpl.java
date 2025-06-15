package com.bttls.cms.sensor.service;

import com.bttls.cms.kafka.KafkaTopicProperties;
import com.bttls.cms.sensor.model.AlarmMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ThresholdAlertServiceImpl implements ThresholdAlertService {

	private final SensorAlarmPublisher sensorAlarmPublisher;
	private final KafkaTopicProperties kafkaTopicProperties;

	@Override
	public void raiseAlarm(String warehouseId, AlarmMessage alarmMessage) {
		log.info("Sending a threshold alarm {}", alarmMessage);
		sensorAlarmPublisher.sendMessage(kafkaTopicProperties.getAlarmTopic(warehouseId), alarmMessage);
	}
}
