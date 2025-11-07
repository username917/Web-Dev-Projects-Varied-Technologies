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

import com.walkinclinic.DTO.PrescriptionDTO;
import com.walkinclinic.Models.Prescription;
import com.walkinclinic.Services.PrescriptionService;

@RestController
@RequestMapping("/api")
public class PrescriptionController {
	
	@Autowired 
	PrescriptionService prescriptionService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/get-prescriptions")
	public ResponseEntity<List<PrescriptionDTO>> retrievePrescriptions() {
		
		List<PrescriptionDTO> prescriptionHistory = prescriptionService.retrievePrescriptions();
	
		if (prescriptionHistory.isEmpty()) {
			
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(prescriptionHistory );
	
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete-prescription")
	public ResponseEntity<?> deletePrescription(@RequestParam("id_prescription") Integer id_prescription) {
		
		boolean deleted = prescriptionService.deletePrescription(id_prescription);
		
		if (deleted) {
			
			return ResponseEntity.ok("Prescription has been deleted");
		
		} else {
			
			return ResponseEntity.notFound().build();
			
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update-prescription")
	public ResponseEntity<Prescription> editPrescription(@RequestParam("id_prescription") Integer id_prescription,
			@RequestBody PrescriptionDTO request) {
		
		System.out.println("Editing prescription...");
		
		if (!id_prescription.equals(request.getId_prescription())) {
			return ResponseEntity.badRequest().build();
		}
		
		System.out.println("Prescription DTO at the API is: " + request.toString());
		
		Prescription prescription = prescriptionService.editPrescription(id_prescription, request);
	
		return ResponseEntity.ok(prescription);
	}
	
	/*
	 * revise the posting method to use a DTO mapped to the entity in the future
	 */
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/create-prescription")
	public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription request) {
		
		Prescription prescriptionRec = prescriptionService.addPrescription(request);
		
		return new ResponseEntity<>(prescriptionRec, HttpStatus.CREATED);
	}
	
	

}
