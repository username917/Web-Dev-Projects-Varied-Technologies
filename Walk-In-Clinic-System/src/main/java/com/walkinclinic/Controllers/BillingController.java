package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.Models.Billing;
import com.walkinclinic.repository.BIllingRepository;

@RestController
public class BillingController {
	
	@Autowired
	BIllingRepository billingRepo;
	
	@GetMapping("/get-billing-list")
	public ResponseEntity<List<Billing>> getBillingList() {
		
		List<Billing> billings = billingRepo.findAll();
		
		return ResponseEntity.ok(billings);
		
		
	}

}
