package com.bttls.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

public abstract class AbstractKafkaConfigFactory {

	@Value("${spring.kafka.bootstrap-servers}")
	protected String bootstrapServers;

	public <T> ConcurrentKafkaListenerContainerFactory<String, T> createKafkaListenerContainerFactory(ConsumerFactory<String, T> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	public <T> ConsumerFactory<String, T> createConsumerFactory(String groupId, Class<T> clazz) {
		JsonDeserializer<T> deserializer = new JsonDeserializer<>(clazz);
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

	public ProducerFactory<String, Object> createProducerFactory() {
		Map<String, Object> configProps = Map.of(
			ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
			ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
			ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	public <T> KafkaTemplate<String, T> createKafkaTemplate(ProducerFactory<String, T> producerFactory) {
		return new KafkaTemplate<>(producerFactory);
	}
}
