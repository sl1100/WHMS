package com.bttls.whs1.udp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.messaging.MessageChannel;

@Configuration
public class UDPConfig {

	@Value("${com.bttls.whs1.udp.port}")
	private int udpPort;

	@Bean
	public UnicastReceivingChannelAdapter udpInboundAdapter() {
		UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(udpPort);
		adapter.setOutputChannel(udpChannel());
		return adapter;
	}

	@Bean
	public MessageChannel udpChannel() {
		return new DirectChannel();
	}
}
