package com.walkinclinic.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkinclinic.DTO.HealthRecordDTO;
import com.walkinclinic.Models.Doctor;
import com.walkinclinic.Models.HealthRecord;
import com.walkinclinic.repository.HealthRecordRepository;

@Service
public class HealthRecordService {
	
	@Autowired
	HealthRecordRepository healthRecordRepo;

	public List<HealthRecord> retrieveHealthRecords() {
		return healthRecordRepo.retrieveHealthRecords();
	}

	public boolean deleteHeatlhRecord(Integer id_health_record) {
		// TODO Auto-generated method stub
		int rowDeleted = healthRecordRepo.deleteHealthRecord(id_health_record);
	
		return rowDeleted > 0;
	}

	public HealthRecord editHealthRecord(Integer id_health_record, HealthRecordDTO healthRecordDTO) {
		
		HealthRecord existingHealthRecord = healthRecordRepo.findById(id_health_record)
				.orElseThrow(() -> new RuntimeException("Health Record not found"));
	
		existingHealthRecord.setId_health_record(healthRecordDTO.getId_health_record());
		existingHealthRecord.setCreated_by(healthRecordDTO.getCreated_by());
		existingHealthRecord.setId_patient(healthRecordDTO.getId_patient());
		existingHealthRecord.setId_visit(healthRecordDTO.getId_visit());
		existingHealthRecord.setNotes(healthRecordDTO.getNotes());
		existingHealthRecord.setRecord_date(healthRecordDTO.getRecord_date());
		existingHealthRecord.setSummary(healthRecordDTO.getSummary());
	
		return healthRecordRepo.save(existingHealthRecord);
	}

	public HealthRecord addHealthRecord(HealthRecord healthRecord) {

		return healthRecordRepo.save(healthRecord);
	}

}
