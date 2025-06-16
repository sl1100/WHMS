package com.bttls.whs1.config.udp.humidity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.messaging.MessageChannel;

@Configuration
public class HumiditySensorUdpConfig {

	@Value("${com.bttls.whs1.udp.sensor.humidity.port}")
	private int udpPort;

	public static final String INBOUND_HUMIDITY_CHANNEL = "humidityChannel";
	public static final String INBOUND_HUMIDITY_MSM_CHANNEL = "humidityMeasurementChannel";

	@Bean
	public UnicastReceivingChannelAdapter udpInboundAdapterSensorHumidity() {
		UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(udpPort);
		adapter.setOutputChannel(udpChannel());
		return adapter;
	}

	@Bean(name = INBOUND_HUMIDITY_CHANNEL)
	public MessageChannel udpChannel() {
		return new DirectChannel();
	}
}
