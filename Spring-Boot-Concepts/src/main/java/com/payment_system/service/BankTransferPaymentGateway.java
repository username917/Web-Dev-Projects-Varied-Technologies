package com.payment_system.service;

import org.springframework.stereotype.Service;

@Service("bankTransferPaymentGateway")
public class BankTransferPaymentGateway implements PaymentGateway{
	
	@Override
	public String processPayment() {
		return "Processing payment via bank transfer...";
	}

}
