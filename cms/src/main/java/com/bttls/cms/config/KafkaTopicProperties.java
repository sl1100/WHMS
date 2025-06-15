package com.bttls.cms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "com.bttls.cms")
public class KafkaTopicProperties {
	private Map<String, String> topics = new HashMap<>();

	public List<String> getTopicNames() {
		return new ArrayList<>(topics.values());
	}

}
