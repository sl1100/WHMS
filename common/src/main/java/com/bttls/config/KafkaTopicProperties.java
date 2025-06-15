package com.bttls.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "com.bttls.kafka")
public class KafkaTopicProperties {
	private Map<String, String> topics = new HashMap<>();

	public List<String> getTopicNames() {
		return new ArrayList<>(topics.values());
	}

}
