package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
