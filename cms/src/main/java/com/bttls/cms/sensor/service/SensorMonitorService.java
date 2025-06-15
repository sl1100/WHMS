package com.bttls.cms.sensor.service;

import com.bttls.cms.sensor.Measurement;
import com.bttls.cms.sensor.repository.SensorConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SensorMonitorService implements SensorMonitor {

	private final SensorConfigRepository sensorConfigRepository;
	private final ThresholdAlert thresholdAlert;

	@Override
	public void monitor(Measurement measurement) {
		var sensorConfig = sensorConfigRepository.getSensorConfig(measurement.sensorId())
			.orElseThrow(() -> new IllegalArgumentException("Sensor not found" + measurement.sensorId()));
		if (measurement.value() > sensorConfig.threshold()) {
			thresholdAlert.raiseAlarm(new NotificationMessage(measurement.sensorId(), "Sensor threshold exceeded"));
		}
	}
}
