package com.bttls.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Component
public class KafkaTopicCreator {

	private final KafkaAdmin kafkaAdmin;
	private final KafkaTopicProperties topicProperties;

	@PostConstruct
	public void createTopics() {
		try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
			var existingTopics = adminClient.listTopics().names().get();
			var missingTopics = topicProperties.getTopicNames().stream()
				.filter(topic -> !existingTopics.contains(topic))
				.toList();

			if (!missingTopics.isEmpty()) {
				List<NewTopic> newTopics = missingTopics.stream()
					.map(name -> new NewTopic(name, 1, (short) 1))
					.toList();
				CreateTopicsResult result = adminClient.createTopics(newTopics);
				KafkaFuture<Void> all = result.all();
				all.get();
				log.info("Topics created: {}", missingTopics);
			}
		} catch (InterruptedException e) {
			log.error("Topic creation failed: " + e.getCause().getMessage(), e);
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			log.error("Topic creation failed:" + e.getCause().getMessage(), e);
		}
	}

}