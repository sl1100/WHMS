package com.bttls.whs1.sensor.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MessageParserTest {

	@Autowired
	private MessageParser messageParser;

	@Test
	void testParseHappyPath() {
		var measurement = messageParser.parse("sensor_id=t1;value=12");
		assertEquals(new Measurement("t1", 12.0), measurement);
	}

	@Test
	void testParseWhenFieldFormatIsInvalid() {
		var message = "sensorId=t1; value=12";
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> messageParser.parse(message));
		assertEquals("Invalid message format: " + message, thrown.getMessage());
	}

}
