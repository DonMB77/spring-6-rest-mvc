package com.drifter.spring6restmvc.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    public static final String ORDER_PLACED_TOPIC = "order.placed";
}
