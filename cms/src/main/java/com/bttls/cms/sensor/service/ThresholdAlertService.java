package com.bttls.cms.sensor.service;

import com.bttls.cms.sensor.SensorAlarmPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class ThresholdAlertService implements ThresholdAlert {

	private final SensorAlarmPublisher sensorAlarmPublisher;

	@Override
	public void raiseAlarm(NotificationMessage notificationMessage) {
		log.info("Sending a threshold alarm {}", notificationMessage);
		sensorAlarmPublisher.sendMessage(notificationMessage);
	}
}
