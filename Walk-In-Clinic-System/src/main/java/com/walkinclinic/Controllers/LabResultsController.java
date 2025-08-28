package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.Models.LabResults;
import com.walkinclinic.Services.LabResultsService;

@RestController
@RequestMapping("/api")
public class LabResultsController {
	
	@Autowired
	LabResultsService labResultsService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/get-lab-results")
	public ResponseEntity<List<LabResults>> viewAndSearchLabResults() {
		
		List<LabResults> labRecordHistory = labResultsService.retrieveLabResults();
		
		if (labRecordHistory.isEmpty() ) {
			
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(labRecordHistory);
	}

}
