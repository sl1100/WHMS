package com.bttls.cms.sensor.service;

import com.bttls.cms.sensor.model.Measurement;
import com.bttls.cms.sensor.model.SensorConfig;
import com.bttls.cms.sensor.model.Warehouse;
import com.bttls.cms.sensor.repository.SensorConfigRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SensorMonitorServiceTest {
	@Autowired
	private SensorMonitorService sensorMonitorService;

	@MockitoBean
	private SensorConfigRepository sensorConfigRepository;

	@MockitoBean
	private ThresholdAlertService thresholdAlertService;

	@Test
	void testMonitorWhenSensorMeasurementIsBelowThreshold() {
		var sensorId = "t1";
		var config = mock(SensorConfig.class);
		when(config.threshold()).thenReturn(30.0);
		when(sensorConfigRepository.getSensorConfig(sensorId)).thenReturn(Optional.of(config));

		sensorMonitorService.monitor(new Measurement(sensorId, 20.0));

		verifyNoInteractions(thresholdAlertService);
	}

	@Test
	void testMonitorWhenSensorMeasurementIsOverThreshold() {
		var sensorId = "t1";
		var warehouseId = "whs1";
		var config = mock(SensorConfig.class);
		when(config.threshold()).thenReturn(30.0);
		when(config.warehouse()).thenReturn(new Warehouse(warehouseId, true));
		when(sensorConfigRepository.getSensorConfig(sensorId)).thenReturn(Optional.of(config));

		sensorMonitorService.monitor(new Measurement(sensorId, 50.0));

		verify(thresholdAlertService).raiseAlarm(eq(warehouseId), any());
	}
}
