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

import com.walkinclinic.DTO.VitalSignsDTO;
import com.walkinclinic.Models.VitalSigns;
import com.walkinclinic.Services.VitalSignsService;

@RestController
@RequestMapping("/api")
public class VitalSignsController {

	@Autowired
	VitalSignsService vitalSignsService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/get-vital-signs-records")
	public ResponseEntity<List<VitalSignsDTO>> retrieveVitalSigns() {
		
		List<VitalSignsDTO> vitalSignsHistory = vitalSignsService.retrieveVitalSigns();
		
		if (vitalSignsHistory.isEmpty()) {
			
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(vitalSignsHistory);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete-vital-signs-record")
	public ResponseEntity<?> deleteVitalSignsRecord(@RequestParam("id_vitals") Integer id_vitals) {
		
		boolean deleted = vitalSignsService.deleteVitalSignsRecord(id_vitals);
		
		if (deleted) {
			
			return ResponseEntity.ok("Vital Signs record has been deleted.");
		
		} else {
			
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/edit-vital-signs-record")
	public ResponseEntity<VitalSigns> editVitalSignsRecord(@RequestParam("id_vitals") Integer id_vitals,
			@RequestBody VitalSignsDTO request) {
		
		System.out.println("Editing vital signs...");
		
		if (!id_vitals.equals(request.getId_vitals())) {
			return ResponseEntity.badRequest().build();
		}
		
		System.out.println("Vitals DTO at the API is: " + request.toString());
		
		VitalSigns vitals = vitalSignsService.editVitalSignsRecord(id_vitals, request);
		
		return ResponseEntity.ok(vitals);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/create-vital-signs-record")
	public ResponseEntity<VitalSigns> addVitalSignsRecord(@RequestBody VitalSigns request) {
		
		VitalSigns vitalSignsRec = vitalSignsService.addVitalSignsRecord(request);
	
		return new ResponseEntity<>(vitalSignsRec, HttpStatus.CREATED);
	}

}
