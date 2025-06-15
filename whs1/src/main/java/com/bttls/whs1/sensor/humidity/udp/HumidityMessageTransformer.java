package com.bttls.whs1.sensor.humidity.udp;

import com.bttls.whs1.sensor.message.Measurement;
import com.bttls.whs1.sensor.message.MessageParser;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import static com.bttls.whs1.sensor.humidity.udp.HumiditySensorUdpConfig.INBOUND_HUMIDITY_CHANNEL;
import static com.bttls.whs1.sensor.humidity.udp.HumiditySensorUdpConfig.INBOUND_HUMIDITY_MSM_CHANNEL;

@RequiredArgsConstructor
@Component
public class HumidityMessageTransformer {

	private final MessageParser messageParser;

	@Transformer(inputChannel = INBOUND_HUMIDITY_CHANNEL, outputChannel = INBOUND_HUMIDITY_MSM_CHANNEL)
	public Measurement transform(String message) {
		return messageParser.parse(message);
	}

}
