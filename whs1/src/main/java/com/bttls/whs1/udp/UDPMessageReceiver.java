package com.bttls.whs1.udp;

import com.bttls.whs1.adapter.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Log4j2
@Component
public class UDPMessageReceiver {

	private final MessageProducer producer;

	@ServiceActivator(inputChannel = "udpChannel")
	public void handleMessage(String message) {
		log.info("Received UDP message, sending further: {} ", message);
		producer.sendMessage(message);
	}
}
