package com.bttls.cms.sensor.repository;

import com.bttls.cms.sensor.model.Sensor;
import com.bttls.cms.sensor.model.SensorConfig;
import com.bttls.cms.sensor.model.SensorType;
import com.bttls.cms.sensor.model.Warehouse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Repository
class InMemSensorConfigRepository implements SensorConfigRepository {

	private final Map<String, Sensor> sensors = new ConcurrentHashMap<>();
	private final Map<String, Warehouse> warehouses = new ConcurrentHashMap<>();
	private final List<SensorConfig> sensorConfigList = new CopyOnWriteArrayList<>();

	public InMemSensorConfigRepository() {
		sensors.putAll(createSensors().stream().collect(Collectors.toMap(Sensor::sensorId, identity())));
		warehouses.putAll(createWarehouses().stream().collect(Collectors.toMap(Warehouse::id, identity())));
		sensorConfigList.addAll(createSensorConfigs());
	}

	@Override
	public Optional<SensorConfig> getSensorConfig(String sensorId) {
		return sensorConfigList.stream()
			.filter(sensorConfig -> Objects.equals(sensorConfig.sensor().sensorId(), sensorId))
			.findFirst();
	}

	private List<Sensor> createSensors() {
		return List.of(
			new Sensor("t1", SensorType.TEMPERATURE),
			new Sensor("t2", SensorType.TEMPERATURE),
			new Sensor("t3", SensorType.TEMPERATURE),
			new Sensor("h1", SensorType.HUMIDITY),
			new Sensor("t4", SensorType.TEMPERATURE),
			new Sensor("t5", SensorType.TEMPERATURE),
			new Sensor("t6", SensorType.TEMPERATURE),
			new Sensor("h2", SensorType.HUMIDITY)
		);
	}

	private List<Warehouse> createWarehouses() {
		return List.of(
			new Warehouse("whs1", true),
			new Warehouse("whs2", true)
		);
	}

	private List<SensorConfig> createSensorConfigs() {
		var whs1 = warehouses.get("whs1");
		var whs2 = warehouses.get("whs2");
		return List.of(
			new SensorConfig(whs1, sensors.get("t1"), 10, true),
			new SensorConfig(whs1, sensors.get("t2"), 20, true),
			new SensorConfig(whs1, sensors.get("t3"), 30, true),
			new SensorConfig(whs1, sensors.get("h1"), 10, true),
			new SensorConfig(whs2, sensors.get("t4"), 40, true),
			new SensorConfig(whs2, sensors.get("t5"), 50, true),
			new SensorConfig(whs2, sensors.get("t6"), 60, true),
			new SensorConfig(whs2, sensors.get("h2"), 20, true)
		);
	}
}
