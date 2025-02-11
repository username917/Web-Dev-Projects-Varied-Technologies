package com.payment_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment_system.service.PaymentGateway;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private final PaymentGateway creditCardPayment;
	private final PaymentGateway paypalPayment;
	private final PaymentGateway bankTransferPayment;
	
	@Autowired
	public PaymentController(
				@Qualifier("creditCardPaymentGateway") PaymentGateway creditCardPayment,
				@Qualifier("paypalPaymentGateway") PaymentGateway paypalPayment,
				@Qualifier("bankTransferPaymentGateway") PaymentGateway bankTransferPayment
			) {
		this.creditCardPayment = creditCardPayment;
		this.bankTransferPayment = bankTransferPayment;
		this.paypalPayment = paypalPayment;
	
	}
	
	@GetMapping("/process")
	public String processPayment(@RequestParam String method) {
		
		switch (method.toLowerCase()) {
		
		case "creditcard":
			return creditCardPayment.processPayment();
			
		case "paypal":
			return paypalPayment.processPayment();
			
		case "banktransfer":
			return bankTransferPayment.processPayment();
			
		default:
			return "Invalid payment method. Choose a credit card, paypal or a bank transfer";
		}
	}
	
	

}
