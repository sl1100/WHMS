package com.bttls.cms.sensor.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
class InMemSensorConfigRepository implements SensorConfigRepository {

	private final List<SensorConfig> sensorConfigList = new CopyOnWriteArrayList<>();

	public InMemSensorConfigRepository() {
		sensorConfigList.addAll(List.of(
				new SensorConfig("whs1", "t1", 30),
				new SensorConfig("whs1", "t2", 20),
				new SensorConfig("whs1", "t3", 30),
				new SensorConfig("whs1", "h1", 50),
				new SensorConfig("whs2", "t4", 30),
				new SensorConfig("whs2", "t5", 30),
				new SensorConfig("whs2", "t6", 30),
				new SensorConfig("whs2", "h2", 60)
			)
		);
	}

	@Override
	public Optional<SensorConfig> getSensorConfig(String sensorId) {
		return sensorConfigList.stream()
			.filter(sensorConfig -> Objects.equals(sensorConfig.sensorId(), sensorId))
			.findFirst();
	}
}
