package com.walkinclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walkinclinic.Models.Patient;

import jakarta.transaction.Transactional;

public interface PatientsRepository extends JpaRepository<Patient, Integer> {

	@Query(value = "SELECT * FROM patients", nativeQuery = true)
	List<Patient> retrievePatientRecords();

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM patients WHERE id_patient = :id_patient", nativeQuery = true)
	int deletePatientRecord(@Param("id_patient") int id_patient);

}
