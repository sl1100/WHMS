package com.bttls.whs1.udp;

import lombok.extern.log4j.Log4j2;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UDPMessageReceiver {

	@ServiceActivator(inputChannel = "udpChannel")
	public void handleMessage(String message) {
		log.info("Received UDP message: {}", message);
	}
}
