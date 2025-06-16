package com.bttls.whs1.config.udp.humidity;

import com.bttls.whs1.sensor.HumidityKafkaPublisher;
import com.bttls.whs1.sensor.model.Measurement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.bttls.whs1.config.udp.humidity.HumiditySensorUdpConfig.INBOUND_HUMIDITY_MSM_CHANNEL;

@RequiredArgsConstructor
@Log4j2
@Component
public class HumidityMessageReceiver {

	private final HumidityKafkaPublisher humidityKafkaProducer;

	@ServiceActivator(inputChannel = INBOUND_HUMIDITY_MSM_CHANNEL)
	public void handleMessageH(Message<Measurement> message) {
		log.info("Received UDP message, sending further: {} ", message);
		humidityKafkaProducer.sendMessage(message.getPayload());
	}

}
