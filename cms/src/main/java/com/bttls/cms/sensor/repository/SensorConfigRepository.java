package com.bttls.cms.sensor.repository;

import com.bttls.cms.sensor.model.SensorConfig;

import java.util.List;
import java.util.Optional;

public interface SensorConfigRepository {

	Optional<SensorConfig> getSensorConfig(String sensorId);

	//void enableWarehouses(List<String> warehouseIds);

	//List<String> getWarehouseIds();
}
