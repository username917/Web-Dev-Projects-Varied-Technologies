package com.walkinclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walkinclinic.Models.LabResults;

import jakarta.transaction.Transactional;

public interface LabResultsRepository extends JpaRepository<LabResults, Integer>{

	@Query(value = "SELECT * FROM labresults", nativeQuery = true)
	List<LabResults> retrieveLabResults();

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM labresults WHERE id_lab_result = :id_lab_result", nativeQuery = true)
	int deteteLabResult(@Param("id_lab_result") Integer id_lab_result);

}
