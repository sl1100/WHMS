package com.bttls.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public abstract class AbstractKafkaConsumer<M> {

	protected void consume(M message, Acknowledgment ack) {
		try {
			log.info("Received message {}", message);
			consume(message);
			ack.acknowledge();
		} catch (Exception e) {
			log.error("Failed to consume message", e);
		}
	}

	protected abstract void consume(M message);
}
