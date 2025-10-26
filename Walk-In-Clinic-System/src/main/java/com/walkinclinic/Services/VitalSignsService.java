package com.walkinclinic.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkinclinic.DTO.VitalSignsDTO;
import com.walkinclinic.repository.VitalSignsRepository;

@Service
public class VitalSignsService {
	
	@Autowired
	VitalSignsRepository vitalSignsRepo;

	public List<VitalSignsDTO> retrieveVitalSigns() {
		// TODO Auto-generated method stub
		return vitalSignsRepo.retrieveVitalSigns();
	}

}
