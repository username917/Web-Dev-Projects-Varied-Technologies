package com.walkinclinic.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkinclinic.Models.LabResults;
import com.walkinclinic.repository.LabResultsRepository;

@Service
public class LabResultsService {
	
	@Autowired
	LabResultsRepository labResultsRepo;

	public List<LabResults> retrieveLabResults() {

		return labResultsRepo.retrieveLabResults();

	}

}
