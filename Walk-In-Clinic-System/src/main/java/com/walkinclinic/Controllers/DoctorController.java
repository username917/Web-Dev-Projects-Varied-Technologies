package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.DTO.DoctorDTO;
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
		
		System.out.println("The value of doctor list is :"  + doctors);
		
		if (doctors.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(doctors);
	}
	
	@PostMapping("/add-doctor")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		
		Doctor doc = docService.addDoctor(doctor);
		
		return new ResponseEntity<>(doc, HttpStatus.CREATED);
	}
	
	@PutMapping("edit-doctor")
	public ResponseEntity<Doctor> editDoctor(@RequestParam("idDoctor") Integer idDoctor, @RequestBody DoctorDTO docDTO) {
		
		if (!idDoctor.equals(docDTO.getIdDoctor())) {
			return ResponseEntity.badRequest().build();
		}
		
		Doctor doctor = docService.editDoctor(idDoctor, docDTO);
		
		return ResponseEntity.ok(doctor);
	}
	
	@DeleteMapping("/remove-doctor")
	public ResponseEntity<?> removeDoctor(@RequestParam("idDoctor") Integer idDoctor) {
		
		boolean deleted = docService.removeDoctor(idDoctor);
		
		if (deleted) {
			return ResponseEntity.ok("Docor has been removed successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

}
