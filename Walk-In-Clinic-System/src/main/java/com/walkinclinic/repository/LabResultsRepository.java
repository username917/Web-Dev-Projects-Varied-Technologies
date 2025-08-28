package com.walkinclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.walkinclinic.Models.LabResults;

public interface LabResultsRepository extends JpaRepository<LabResults, Integer>{

	@Query(value = "SELECT * FROM labresults", nativeQuery = true)
	List<LabResults> retrieveLabResults();

}
