package com.walkinclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walkinclinic.Models.Doctor;

import jakarta.transaction.Transactional;

public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
	
	@Query(value = "SELECT doctor FROM doctors WHERE specialty = :specialty", nativeQuery = true)
	public List<Doctor> getDoctorsBySpecialty(@Param("spscialty") String specialty);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM doctor WHERE idDoctor = :idDoctor", nativeQuery = true)
	public int removeDoctor(Integer idDoctor);
	

}
