package com.bttls.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-common.properties")
@EnableConfigurationProperties(KafkaTopicProperties.class)
public class CommonPropertiesConfig {
}
