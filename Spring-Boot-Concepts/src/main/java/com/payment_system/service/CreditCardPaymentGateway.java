package com.payment_system.service;

import org.springframework.stereotype.Service;

@Service("creditCardPaymentGateway")
public class CreditCardPaymentGateway implements PaymentGateway {
	
	@Override
	public String processPayment() {
		return "Processing payment via credit card...";
	}

}
