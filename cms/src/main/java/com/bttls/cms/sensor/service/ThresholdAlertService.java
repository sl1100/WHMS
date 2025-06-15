package com.bttls.cms.sensor.service;

import com.bttls.cms.sensor.model.AlarmMessage;

public interface ThresholdAlertService {

	void raiseAlarm(AlarmMessage alarmMessage);
}
