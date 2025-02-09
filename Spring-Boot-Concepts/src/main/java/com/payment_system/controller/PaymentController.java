package com.payment_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment_system.service.PaymentGateway;
import com.payment_system.service.PaymentService;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private final PaymentService paymentService;
	private final PaymentGateway paymentGateway;
	
	@Autowired
	public PaymentController(PaymentService paymentService, PaymentGateway paymentGateway) {
		this.paymentService = paymentService;
		this.paymentGateway = paymentGateway;
	}
	
	@GetMapping("/process")
	public String processPayment() {
		return paymentGateway.connect() + " | " + paymentService.processPayment();
	}
	
	

}
