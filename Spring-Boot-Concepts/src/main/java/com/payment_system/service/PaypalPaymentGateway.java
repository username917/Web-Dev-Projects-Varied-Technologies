package com.payment_system.service;

import org.springframework.stereotype.Service;

@Service("paypalPaymentGateway")
public class PaypalPaymentGateway implements PaymentGateway {
	
	@Override
	public String processPayment() {
		return "Processing payment via Paypal...";
	}

}
