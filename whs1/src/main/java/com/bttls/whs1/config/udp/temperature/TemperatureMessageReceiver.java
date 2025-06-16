package com.bttls.whs1.config.udp.temperature;

import com.bttls.whs1.sensor.TemperatureKafkaPublisher;
import com.bttls.whs1.sensor.model.Measurement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.bttls.whs1.config.udp.temperature.TemperatureSensorUdpConfig.INBOUND_TEMPERATURE_MSM_CHANNEL;

@RequiredArgsConstructor
@Log4j2
@Component
public class TemperatureMessageReceiver {

	private final TemperatureKafkaPublisher temperatureKafkaProducer;

	@ServiceActivator(inputChannel = INBOUND_TEMPERATURE_MSM_CHANNEL)
	public void handleMessageH(Message<Measurement> message) {
		log.info("Received UDP message, sending further: {} ", message);
		temperatureKafkaProducer.sendMessage(message.getPayload());
	}

}
