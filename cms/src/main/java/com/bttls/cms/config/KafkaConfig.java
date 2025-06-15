package com.bttls.cms.config;

import com.bttls.cms.sensor.Measurement;
import com.bttls.config.AbstractKafkaConfigFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConfig extends AbstractKafkaConfigFactory {

	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Measurement> kafkaListenerContainerFactory() {
		return createKafkaListenerContainerFactory(consumerFactory());
	}

	@Bean
	public ConsumerFactory<String, Measurement> consumerFactory() {
		return createConsumerFactory(groupId, Measurement.class);
	}

}
