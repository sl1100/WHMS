package com.bttls.cms.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "com.bttls.cms.kafka.sensor")
public class KafkaTopicProperties {
	private List<String> services = new ArrayList<>();
	private Map<String, String> monitor = new HashMap<>();
	@Value("${com.bttls.cms.kafka.sensor.alarm.topic-suffix}")
	private String alarmTopicSuffix;

	public List<String> getTopicNames() {
		return new ArrayList<>(monitor.values());
	}

	public String getAlarmTopic(String prefix) {
		return String.join(".", prefix, alarmTopicSuffix);
	}

}
