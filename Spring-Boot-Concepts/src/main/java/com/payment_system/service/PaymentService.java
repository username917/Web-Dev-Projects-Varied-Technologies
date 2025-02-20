package com.payment_system.service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class PaymentService {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
	private final Counter paymentCounter;
	
	public PaymentService(MeterRegistry meterRegistry) {
		this.paymentCounter = meterRegistry.counter("payments.processed");
	}
	
	public String processPayment(PaymentGateway paymentGateway) {
		
		String transactionId = UUID.randomUUID().toString(); // Generate unique ID
		Instant startTime = Instant.now(); // Start time
		
		logger.info("Transaction ID: {} | Payment Start | Method: {} | Time {}",
				transactionId, paymentGateway.getClass().getSimpleName(), startTime);
		
		String result = paymentGateway.processPayment(); // Simulate processing
		
		Instant endTime = Instant.now(); // end time.
		long duration = Duration.between(startTime, endTime).toMillis(); // calculate execution time
		
		
        logger.info("Transaction ID: {} | Payment Completed | Method {} | Time Taken: {} ms",
        		transactionId, paymentGateway.getClass().getSimpleName(), duration);
        
        return "Transaction ID: " + transactionId + " | " + result;
    }
}
