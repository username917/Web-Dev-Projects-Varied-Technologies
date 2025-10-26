package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.DTO.VitalSignsDTO;
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

}
