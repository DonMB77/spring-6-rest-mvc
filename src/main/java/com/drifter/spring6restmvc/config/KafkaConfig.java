package com.drifter.spring6restmvc.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    public static final String ORDER_PLACED_TOPIC = "order.placed";
    public static final String DRINK_REQUEST_ICE_COLD_TOPIC = "order.request.icecold";
    public static final String DRINK_REQUEST_COLD_TOPIC = "order.request.cold";
    public static final String DRINK_REQUEST_COOL_TOPIC = "order.request.cool";
    public static final String DRINK_PREPARED_TOPIC = "drink.prepared";
}
