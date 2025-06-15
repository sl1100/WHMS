package com.bttls.whs1.sensor.temperature.udp;

import com.bttls.whs1.sensor.message.Measurement;
import com.bttls.whs1.sensor.message.MessageParser;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import static com.bttls.whs1.sensor.temperature.udp.TemperatureSensorUdpConfig.INBOUND_TEMPERATURE_CHANNEL;
import static com.bttls.whs1.sensor.temperature.udp.TemperatureSensorUdpConfig.INBOUND_TEMPERATURE_MSM_CHANNEL;

@RequiredArgsConstructor
@Component
public class TemperatureMessageTransformer {

	private final MessageParser messageParser;

	@Transformer(inputChannel = INBOUND_TEMPERATURE_CHANNEL, outputChannel = INBOUND_TEMPERATURE_MSM_CHANNEL)
	public Measurement transform(String message) {
		return messageParser.parse(message);
	}

}
