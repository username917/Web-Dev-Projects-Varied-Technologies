package com.payment_system.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	
	private final PaymentGateway paymentGateway;
	
	public PaymentService(@Qualifier("creditCardPaymentGateway") PaymentGateway paymentGateway) {
		
		this.paymentGateway = paymentGateway;
	}
	
	public String processPayment() {
		return paymentGateway.processPayment();
	}

}
