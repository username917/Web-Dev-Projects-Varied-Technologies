package com.walkinclinic.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkinclinic.Models.Doctor;
import com.walkinclinic.repository.DoctorRepo;

@Service
public class DoctorService {

	@Autowired
	DoctorRepo docRepo;
	
	public List<Doctor> getDoctorBySpecialty(String specialty) {
		
		return docRepo.getDoctorsBySpecialty(specialty);
	
	}
}
