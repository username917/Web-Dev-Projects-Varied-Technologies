package com.payment_system.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
	
	public String processPayment(PaymentGateway paymentGateway) {
        logger.info("Processing payment with: {}", paymentGateway.getClass().getSimpleName());
        return paymentGateway.processPayment();
    }
}
