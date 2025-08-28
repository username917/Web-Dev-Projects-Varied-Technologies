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

import com.walkinclinic.DTO.HealthRecordDTO;
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
			
			return ResponseEntity.noContent().build();		
		}
		
		return ResponseEntity.ok(healthRecordHistory);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete-health-record")
	public ResponseEntity<?> deleteHealthRecord(@RequestParam("id_health_record") Integer id_health_record) {
		
		boolean deleted = healthRecordService.deleteHeatlhRecord(id_health_record);
		
		if (deleted) {
			return ResponseEntity.ok("Health record cancelled successfully!");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update-health-record")
	public ResponseEntity<HealthRecord> editHealthRecord(@RequestParam("id_health_record") Integer id_health_record, @RequestBody HealthRecordDTO healthRecordDTO) {
		
		System.out.println("Editing heatlh record...");
		;
		if (!id_health_record.equals(healthRecordDTO.getId_health_record())) {
			return ResponseEntity.badRequest().build();
		}
		
		System.out.println("Health Record DTO at the API is: " + healthRecordDTO);
		
		HealthRecord healthRecord = healthRecordService.editHealthRecord(id_health_record, healthRecordDTO);
		
		return ResponseEntity.ok(healthRecord);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/create-health-record")
	public ResponseEntity<HealthRecord> addHealthRecord(@RequestBody HealthRecord healthRecord) {
		
		HealthRecord healthRec = healthRecordService.addHealthRecord(healthRecord);
		
		return new ResponseEntity<>(healthRec, HttpStatus.CREATED);
	}
	
	
	

}
