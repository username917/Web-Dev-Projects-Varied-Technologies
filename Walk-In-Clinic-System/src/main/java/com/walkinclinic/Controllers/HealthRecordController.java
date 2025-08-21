package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.Models.Appointment;
import com.walkinclinic.Models.HealthRecord;
import com.walkinclinic.Services.HealthRecordService;

@RestController
@RequestMapping("/api")
public class HealthRecordController {
	
	@Autowired
	HealthRecordService healthRecordService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/get-health-records")
	public ResponseEntity<List<HealthRecord>> viewAndSearchHealthRecords() {
		
		List<HealthRecord> healthRecordHistory = healthRecordService.retrieveHealthRecords();
		
		if (healthRecordHistory.isEmpty()) {
			
			return ResponseEntity.noContent().build();		}
		
		return ResponseEntity.ok(healthRecordHistory);
		
	}
	

}
