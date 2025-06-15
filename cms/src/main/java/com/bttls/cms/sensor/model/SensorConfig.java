package com.bttls.cms.sensor.model;

public record SensorConfig(Warehouse warehouse, Sensor sensor, double threshold, boolean enabled) {
}
