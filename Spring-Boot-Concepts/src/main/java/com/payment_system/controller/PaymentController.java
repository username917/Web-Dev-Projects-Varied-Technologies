package com.payment_system.controller;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment_system.service.PaymentGateway;
import com.payment_system.service.PaymentService;

import jakarta.servlet.http.HttpServletRequest;

import com.payment_system.exceptions.InvalidPaymentException;
import com.payment_system.exceptions.PaymentException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    
    private final Map<String, PaymentGateway> paymentGateways;
    private final PaymentService paymentService;

    public PaymentController(
                PaymentService paymentService,
                @Qualifier("creditCardPaymentGateway") PaymentGateway creditCardPayment,
                @Qualifier("paypalPaymentGateway") PaymentGateway paypalPayment,
                @Qualifier("bankTransferPaymentGateway") PaymentGateway bankTransferPayment
            ) {

         this.paymentService = paymentService;
         this.paymentGateways = new HashMap<>();
         this.paymentGateways.put("creditcard", creditCardPayment);
         this.paymentGateways.put("paypal", paypalPayment);
         this.paymentGateways.put("banktransfer", bankTransferPayment);
    }
    
    @GetMapping("/process")
    public String processPayment(@RequestParam String method, HttpServletRequest request) {
        
        String clientIp = request.getRemoteAddr();
        Instant requestTime = Instant.now();
        
        logger.info("Incoming Payment Request | Method: {} | IP: {} | Time: {}",
                method, clientIp, requestTime);
        
        PaymentGateway gateway = paymentGateways.get(method.toLowerCase());
        
        if (gateway == null) {
            logger.warn("Invalid Payment Method | Method: {} | IP: {} | Time: {}",
                    method, clientIp, requestTime);
            throw new InvalidPaymentException("Invalid payment method: " + method);  // Throw exception instead of returning string
        }
        
        try {
            return paymentService.processPayment(gateway);
        } catch (PaymentException e) {
            logger.error("Payment processing failed: {}", e.getMessage());
            throw e;  // Rethrow the exception to be caught by global handler
        }
    }
}
