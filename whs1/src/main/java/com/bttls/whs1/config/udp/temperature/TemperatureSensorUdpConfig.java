package com.bttls.whs1.config.udp.temperature;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.messaging.MessageChannel;

@Configuration
public class TemperatureSensorUdpConfig {

	@Value("${com.bttls.whs1.udp.sensor.temperature.port}")
	private int udpPort;

	public static final String INBOUND_TEMPERATURE_CHANNEL = "temperatureChannel";
	public static final String INBOUND_TEMPERATURE_MSM_CHANNEL = "temperatureMeasurementChannel";

	@Bean
	public UnicastReceivingChannelAdapter udpInboundAdapterSensorTemperature() {
		UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(udpPort);
		adapter.setOutputChannel(udpChannel());
		return adapter;
	}

	@Bean(name = INBOUND_TEMPERATURE_CHANNEL)
	public MessageChannel udpChannel() {
		return new DirectChannel();
	}

}
