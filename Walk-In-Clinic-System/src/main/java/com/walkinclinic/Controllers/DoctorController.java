package com.walkinclinic.Controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import com.walkinclinic.DTO.DoctorDTO;
import com.walkinclinic.Models.Doctor;
import com.walkinclinic.Services.DoctorService;

@RestController
@RequestMapping("/api")
public class DoctorController {
	
	@Autowired
	DoctorService docService;
	
	@GetMapping("/doctors-by-specialty")
	public List<Doctor> getDoctorsBySpecialty(@RequestParam("specialty") String specialty) {
		
		return docService.getDoctorBySpecialty(specialty);
		
	}
	

	@GetMapping("/get-doctor-list")
	public ResponseEntity<List<DoctorDTO>> getDoctorList() {
	    List<Doctor> doctors = docService.getAllDoctors();

	    List<DoctorDTO> dtoList = doctors.stream()
	        .map(doc -> {
	            DoctorDTO dto = new DoctorDTO();
	            dto.setIdDoctor(doc.getId_Doctor());
	            dto.setFirst_name(doc.getFirst_name());
	            dto.setLast_name(doc.getLast_name()); // corrected field
	            dto.setSpecialty(doc.getSpecialty());
	            dto.setAvailability(doc.getAvailability());
	            dto.setContact_info(doc.getContact_info());
	            dto.setEducation(doc.getEducation());
	            return dto;
	        })
	        .collect(Collectors.toList());

	    return ResponseEntity.ok(dtoList);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add-doctor")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		
		Doctor doc = docService.addDoctor(doctor);
		
		return new ResponseEntity<>(doc, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("edit-doctor")
	public ResponseEntity<Doctor> editDoctor(@RequestParam("idDoctor") Integer idDoctor, @RequestBody DoctorDTO docDTO) {
		
		System.out.println("Editing doctor...");
		;
		if (!idDoctor.equals(docDTO.getIdDoctor())) {
			return ResponseEntity.badRequest().build();
		}
		
		System.out.println("Doctor DTO at the API is: " + docDTO);
		
		Doctor doctor = docService.editDoctor(idDoctor, docDTO);
		
		return ResponseEntity.ok(doctor);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/remove-doctor")
	public ResponseEntity<?> removeDoctor(@RequestParam("id_doctor") Integer id_doctor) {
		
		boolean deleted = docService.removeDoctor(id_doctor);
		
		if (deleted) {
			return ResponseEntity.ok("Doctor has been removed successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

}
