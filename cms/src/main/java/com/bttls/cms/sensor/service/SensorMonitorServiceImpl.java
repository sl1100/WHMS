package com.bttls.cms.sensor.service;

import com.bttls.cms.sensor.model.Measurement;
import com.bttls.cms.sensor.model.AlarmMessage;
import com.bttls.cms.sensor.model.SensorConfig;
import com.bttls.cms.sensor.repository.SensorConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class SensorMonitorServiceImpl implements SensorMonitorService {

	private final SensorConfigRepository sensorConfigRepository;
	private final ThresholdAlertService thresholdAlertService;

	@Override
	public void monitor(Measurement measurement) {
		var sensorConfig = sensorConfigRepository.getSensorConfig(measurement.sensorId())
			.orElseThrow(() -> new IllegalArgumentException("Sensor not found" + measurement.sensorId()));
		if (isOverThreshold(measurement, sensorConfig)) {
			var warehouseId = sensorConfig.warehouse().getId();
			thresholdAlertService.raiseAlarm(warehouseId, new AlarmMessage(measurement.sensorId(), "Sensor threshold exceeded"));
		}
	}

	private boolean isOverThreshold(Measurement measurement, SensorConfig sensorConfig) {
		return measurement.value() > sensorConfig.threshold();
	}
}
