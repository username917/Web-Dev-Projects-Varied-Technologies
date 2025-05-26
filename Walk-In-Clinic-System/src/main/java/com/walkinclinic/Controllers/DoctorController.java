package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.Models.Doctor;
import com.walkinclinic.Services.DoctorService;

@RestController
public class DoctorController {
	
	@Autowired
	DoctorService docService;
	
	@GetMapping("/doctors-by-specialty")
	public List<Doctor> getDoctorsBySpecialty(@RequestParam("specialty") String specialty) {
		
		return docService.getDoctorBySpecialty(specialty);
		
	}
	
	@GetMapping("/get-doctor-list")
	public ResponseEntity<List<Doctor>> getDoctorList() {
		
		List<Doctor> doctors = docService.getAllDoctors();
		
		if (doctors.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(doctors);
	}

}
