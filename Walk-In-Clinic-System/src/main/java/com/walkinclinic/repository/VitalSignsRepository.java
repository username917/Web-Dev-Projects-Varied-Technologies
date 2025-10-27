package com.walkinclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walkinclinic.DTO.VitalSignsDTO;
import com.walkinclinic.Models.VitalSigns;

import jakarta.transaction.Transactional;

public interface VitalSignsRepository extends JpaRepository<VitalSigns, Integer>{

	@Query(value = "SELECT * FROM vitalsigns", nativeQuery = true)
	List<VitalSignsDTO> retrieveVitalSigns();

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM vitalsigns WHERE id_vitals = :id_vitals", nativeQuery = true)
	int deleteVitalSignsRecord(@Param("id_vitals") Integer id_vitals);

}
