package com.bttls.cms.sensor.repository;

import java.util.Optional;

public interface SensorConfigRepository {

	Optional<SensorConfig> getSensorConfig(String sensorId);
}
