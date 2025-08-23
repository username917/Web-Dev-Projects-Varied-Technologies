package com.walkinclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walkinclinic.Models.HealthRecord;

import jakarta.transaction.Transactional;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer> {

	@Query(value = "SELECT * FROM healthrecords", nativeQuery = true)
	List<HealthRecord> retrieveHealthRecords();

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM healthrecords WHERE id_health_record = :id_health_record", nativeQuery = true)
	int deleteHealthRecord(@Param("id_health_record") Integer id_health_record);

}
