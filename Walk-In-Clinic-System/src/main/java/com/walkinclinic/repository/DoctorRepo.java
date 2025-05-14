package com.walkinclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walkinclinic.Models.Doctor;

public interface DoctorRepo extends JpaRepository<DoctorRepo, Integer> {
	
	@Query(value = "SELECT doctor FROM doctors WHERE specialty = :specialty", nativeQuery = true)
	public List<Doctor> getDoctorsBySpecialty(@Param("spscialty") String specialty);
	

}
