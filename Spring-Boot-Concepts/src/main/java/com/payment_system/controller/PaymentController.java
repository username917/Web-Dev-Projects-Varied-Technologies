package com.payment_system.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment_system.service.PaymentGateway;
import com.payment_system.service.PaymentService;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	/*
	private final PaymentGateway creditCardPayment;
	private final PaymentGateway paypalPayment;
	private final PaymentGateway bankTransferPayment;
	*/
	
	private final Map<String, PaymentGateway> paymentGateways;
	private final PaymentService paymentService;
	@Autowired
	public PaymentController(
				PaymentService paymentService,
				@Qualifier("creditCardPaymentGateway") PaymentGateway creditCardPayment,
				@Qualifier("paypalPaymentGateway") PaymentGateway paypalPayment,
				@Qualifier("bankTransferPaymentGateway") PaymentGateway bankTransferPayment
			) {
		/*
		this.creditCardPayment = creditCardPayment;
		this.bankTransferPayment = bankTransferPayment;
		this.paypalPayment = paypalPayment;
		*/
		this.paymentService = paymentService;
		 this.paymentGateways = new HashMap<>();
	     this.paymentGateways.put("creditcard", creditCardPayment);
	     this.paymentGateways.put("paypal", paypalPayment);
	     this.paymentGateways.put("banktransfer", bankTransferPayment);
	}
	
	@GetMapping("/process")
	public String processPayment(@RequestParam String method) {
		
		PaymentGateway gateway = paymentGateways.get(method.toLowerCase());
		
		if (gateway == null) {
			return "Invalid payment method. Choose from one of the following: " + paymentGateways.keySet();
		}
		
		return paymentService.processPayment(gateway);
		
		
		/*
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
		*/
	}
	
	

}
