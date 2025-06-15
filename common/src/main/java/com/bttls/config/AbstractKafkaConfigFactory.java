package com.bttls.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractKafkaConfigFactory {

	public KafkaAdmin createKafkaAdmin(String bootstrapServers) {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		return new KafkaAdmin(configs);
	}

	public ConcurrentKafkaListenerContainerFactory<String, Object> createKafkaListenerContainerFactory(ConsumerFactory<String, Object> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	public ConsumerFactory<String, Object> createConsumerFactory(String bootstrapServers, String groupId) {
		JsonDeserializer<Object> deserializer = new JsonDeserializer<>(Object.class);
		deserializer.addTrustedPackages("*");
		deserializer.setRemoveTypeHeaders(true);
		deserializer.setUseTypeHeaders(false);
		deserializer.setUseTypeMapperForKey(false);

		Map<String, Object> configProps = Map.of(
			ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
			ConsumerConfig.GROUP_ID_CONFIG, groupId,
			ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
			ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), deserializer);
	}

	public ProducerFactory<String, Object> createProducerFactory(String bootstrapServers) {
		Map<String, Object> configProps = Map.of(
			ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
			ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
			ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	public KafkaTemplate<String, Object> createKafkaTemplate(ProducerFactory<String, Object> producerFactory) {
		return new KafkaTemplate<>(producerFactory);
	}
}
