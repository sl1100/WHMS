package com.bttls.cms.sensor.service;

import com.bttls.cms.sensor.Measurement;
import com.bttls.cms.sensor.model.AlarmMessage;
import com.bttls.cms.sensor.repository.SensorConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class SensorMonitorServiceServiceImpl implements SensorMonitorService {

	private final SensorConfigRepository sensorConfigRepository;
	private final ThresholdAlertService thresholdAlertService;

	@Override
	public void monitor(Measurement measurement) {
		var sensorConfig = sensorConfigRepository.getSensorConfig(measurement.sensorId())
			.orElseThrow(() -> new IllegalArgumentException("Sensor not found" + measurement.sensorId()));
		if (measurement.value() > sensorConfig.threshold()) {
			thresholdAlertService.raiseAlarm(new AlarmMessage(measurement.sensorId(), "Sensor threshold exceeded"));
		}
	}
}
