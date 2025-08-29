package com.walkinclinic.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkinclinic.DTO.LabResultsDTO;
import com.walkinclinic.Models.LabResults;
import com.walkinclinic.repository.LabResultsRepository;

@Service
public class LabResultsService {
	
	@Autowired
	LabResultsRepository labResultsRepo;

	public List<LabResults> retrieveLabResults() {

		return labResultsRepo.retrieveLabResults();

	}

	public boolean deleteLabRecord(Integer id_lab_result) {

		int rowDeleted = labResultsRepo.deteteLabResult(id_lab_result);
		
		return rowDeleted >0;
	}

	public LabResults editLabResult(Integer id_lab_result, LabResultsDTO labResultsDTO) {
		
		LabResults existingLabRecord = labResultsRepo.findById(id_lab_result)
				.orElseThrow(()-> new RuntimeException("Lab Result not found"));
		
		existingLabRecord.setId_lab_result(labResultsDTO.getId_lab_result());
		existingLabRecord.setDate_conducted(labResultsDTO.getDate_conducted());
		existingLabRecord.setId_visit(labResultsDTO.getId_visit());
		existingLabRecord.setNormal_range(labResultsDTO.getNormal_range());
		existingLabRecord.setResult(labResultsDTO.getResult());
		existingLabRecord.setTest_type(labResultsDTO.getTest_type());
		existingLabRecord.setUnits(labResultsDTO.getUnits());
		
		return labResultsRepo.save(existingLabRecord);

	}

	public LabResults addLabResult(LabResults labResult) {

		return labResultsRepo.save(labResult);
	}

}
