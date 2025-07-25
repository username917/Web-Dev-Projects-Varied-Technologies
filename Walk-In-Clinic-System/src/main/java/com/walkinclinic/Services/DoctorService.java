package com.walkinclinic.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.walkinclinic.DTO.DoctorDTO;
import com.walkinclinic.Models.Doctor;
import com.walkinclinic.repository.DoctorRepo;

@Service
public class DoctorService {

	@Autowired
	DoctorRepo docRepo;
	
	public List<Doctor> getDoctorBySpecialty(String specialty) {
		
		return docRepo.getDoctorsBySpecialty(specialty);
	
	}

	public List<Doctor> getAllDoctors() {
		// TODO Auto-generated method stub
		return docRepo.findAllDoctors();
	}

	public Doctor addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return docRepo.save(doctor);
	}

	public Doctor editDoctor(Integer idDoctor, DoctorDTO docDTO) {
		// TODO Auto-generated method stub
		Doctor existingDoctor = docRepo.findById(idDoctor)
				.orElseThrow(() -> new RuntimeException("Doctor not found for queried id value."));
	
		existingDoctor.setAvailability(docDTO.getAvailability());
		existingDoctor.setContact_info(docDTO.getContact_info());
		existingDoctor.setEducation(docDTO.getEducation());
		existingDoctor.setFirst_name(docDTO.getFirst_name());
		existingDoctor.setLast_name(docDTO.getLast_name());
		existingDoctor.setSpecialty(docDTO.getSpecialty());
		
		return docRepo.save(existingDoctor);
	}

	public boolean removeDoctor(Integer id_doctor) {
		// TODO Auto-generated method stub
		int rowDeleted = docRepo.removeDoctor(id_doctor);
		
		return rowDeleted > 0;
	}
}
