package com.bttls.whs1.config;

import com.bttls.config.AbstractKafkaConfigFactory;
import com.bttls.whs1.NotificationMessage;
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
	public ConcurrentKafkaListenerContainerFactory<String, NotificationMessage> kafkaListenerContainerFactory() {
		return createKafkaListenerContainerFactory(consumerFactory());
	}

	@Bean
	public ConsumerFactory<String, NotificationMessage> consumerFactory() {
		return createConsumerFactory(groupId, NotificationMessage.class);
	}

}
