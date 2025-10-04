package com.walkinclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walkinclinic.DTO.PrescriptionDTO;
import com.walkinclinic.Models.Prescription;

import jakarta.transaction.Transactional;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{

	@Query(value = "SELECT * FROM prescriptions", nativeQuery = true)
	List<PrescriptionDTO> retrrievePrescription();

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM prescriptions WHERE id_prescription = :id_prescription", nativeQuery = true)
	int deletePrescription(@Param("id_prescription") Integer id_prescription);

	
	
}
