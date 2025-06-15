package com.bttls.cms.sensor.service;

import com.bttls.cms.sensor.SensorAlarmPublisher;
import com.bttls.cms.sensor.model.AlarmMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ThresholdAlertServiceServiceImpl implements ThresholdAlertService {

	private final SensorAlarmPublisher sensorAlarmPublisher;

	@Override
	public void raiseAlarm(AlarmMessage alarmMessage) {
		log.info("Sending a threshold alarm {}", alarmMessage);
		sensorAlarmPublisher.sendMessage(alarmMessage);
	}
}
