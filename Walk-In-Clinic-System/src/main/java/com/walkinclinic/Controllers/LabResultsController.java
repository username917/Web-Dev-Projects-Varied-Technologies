package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.DTO.LabResultsDTO;
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete-lab-result")
	public ResponseEntity<?> deleteLabResult(@RequestParam("id_lab_result") Integer id_lab_result) {
	    boolean deleted = labResultsService.deleteLabRecord(id_lab_result);
	    return deleted ? ResponseEntity.ok("Lab Result deleted successfully")
	                   : ResponseEntity.notFound().build();
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update-lab-result")
	public ResponseEntity<LabResults> editLabResult(@RequestParam("id_lab_result") Integer id_lab_result,
			@RequestBody LabResultsDTO labResultsDTO) {
		
		System.out.println("Editing lab result...");
		
		if (!id_lab_result.equals(labResultsDTO.getId_lab_result())) {
			return ResponseEntity.badRequest().build();
		}
		
		System.out.println("Lab Result DTO at the API is: " + labResultsDTO);
		
		LabResults labResult = labResultsService.editLabResult(id_lab_result, labResultsDTO);
		
		return ResponseEntity.ok(labResult);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/create-lab-result")
	public ResponseEntity<LabResults> addLabResult(@RequestBody LabResults labResult) {
		
		LabResults labRes = labResultsService.addLabResult(labResult);
		
		return new ResponseEntity<>(labRes, HttpStatus.CREATED);
	}
}
