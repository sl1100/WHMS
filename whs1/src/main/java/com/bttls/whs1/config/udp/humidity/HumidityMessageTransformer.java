package com.bttls.whs1.config.udp.humidity;

import com.bttls.whs1.sensor.model.Measurement;
import com.bttls.whs1.config.udp.MessageParser;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import static com.bttls.whs1.config.udp.humidity.HumiditySensorUdpConfig.INBOUND_HUMIDITY_CHANNEL;
import static com.bttls.whs1.config.udp.humidity.HumiditySensorUdpConfig.INBOUND_HUMIDITY_MSM_CHANNEL;

@RequiredArgsConstructor
@Component
public class HumidityMessageTransformer {

	private final MessageParser messageParser;

	@Transformer(inputChannel = INBOUND_HUMIDITY_CHANNEL, outputChannel = INBOUND_HUMIDITY_MSM_CHANNEL)
	public Measurement transform(String message) {
		return messageParser.parse(message);
	}

}
