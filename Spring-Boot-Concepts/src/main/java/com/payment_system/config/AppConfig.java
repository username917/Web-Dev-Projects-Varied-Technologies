package com.payment_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.payment_system.service.PaymentGateway;



@Configuration
public class AppConfig {

    @Bean
    public PaymentGateway paymentGateway() {
        return new PaymentGateway();
    }
}
