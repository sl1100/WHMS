package com.bttls.cms.sensor.repository;

import com.bttls.cms.sensor.model.SensorConfig;

import java.util.Optional;

public interface SensorConfigRepository {

	Optional<SensorConfig> getSensorConfig(String sensorId);

}
