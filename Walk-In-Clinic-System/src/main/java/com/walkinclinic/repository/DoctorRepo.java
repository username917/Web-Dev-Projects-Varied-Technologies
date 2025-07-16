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
	@Query(value = "DELETE FROM doctors WHERE id_doctor = :id_doctor", nativeQuery = true)
	public int removeDoctor(@Param("id_doctor") Integer id_doctor);

	@Query(value = "SELECT * FROM `walk-in-clinic-schema`.doctors", nativeQuery = true)
	public List<Doctor> findAllDoctors();
	

}
