package com.payment_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.payment_system.service.PaymentGatewayClass;



@Configuration
public class AppConfig {

    @Bean
    public PaymentGatewayClass paymentGateway() {
        return new PaymentGatewayClass();
    }
}
