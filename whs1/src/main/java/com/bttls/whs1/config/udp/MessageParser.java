package com.bttls.whs1.config.udp;

import com.bttls.whs1.sensor.model.Measurement;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class MessageParser {

	private static final String MEASUREMENT_PATTERN = "sensor_id=(.+?); ?value=(\\d+(.\\d+)?)";

	public Measurement parse(String message) {
		var pattern = Pattern.compile(MEASUREMENT_PATTERN);
		var matcher = pattern.matcher(message);
		if (matcher.matches()) {
			return new Measurement(matcher.group(1), Double.parseDouble(matcher.group(2)));
		} else {
			throw new IllegalArgumentException("Invalid message format: " + message);
		}
	}

}
