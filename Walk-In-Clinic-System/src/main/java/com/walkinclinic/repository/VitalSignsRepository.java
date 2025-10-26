package com.walkinclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.walkinclinic.DTO.VitalSignsDTO;
import com.walkinclinic.Models.VitalSigns;

public interface VitalSignsRepository extends JpaRepository<VitalSigns, Integer>{

	@Query(value = "SELECT * FROM vitalsigns", nativeQuery = true)
	List<VitalSignsDTO> retrieveVitalSigns();

}
