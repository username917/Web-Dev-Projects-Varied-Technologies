package com.walkinclinic.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkinclinic.DTO.VitalSignsDTO;
import com.walkinclinic.Models.VitalSigns;
import com.walkinclinic.repository.VitalSignsRepository;

@Service
public class VitalSignsService {
	
	@Autowired
	VitalSignsRepository vitalSignsRepo;

	public List<VitalSignsDTO> retrieveVitalSigns() {
		// TODO Auto-generated method stub
		return vitalSignsRepo.retrieveVitalSigns();
	}

	public boolean deleteVitalSignsRecord(Integer id_vitals) {
		
		int rowDeleted = vitalSignsRepo.deleteVitalSignsRecord(id_vitals);
		
		return rowDeleted > 0;
	}

	public VitalSigns editVitalSignsRecord(Integer id_vitals, VitalSignsDTO request) {
		
		VitalSigns existingVitalSigns = vitalSignsRepo.findById(id_vitals)
				.orElseThrow(() -> new RuntimeException("Vital Signs record not found"));;
	
		existingVitalSigns.setId_visit(request.getId_visit());
		existingVitalSigns.setBlood_pressure(request.getBlood_pressure());
		existingVitalSigns.setHeart_rate(request.getHeart_rate());
		existingVitalSigns.setHeight(request.getHeight());
		existingVitalSigns.setId_vitals(request.getId_vitals());
		existingVitalSigns.setRespirator_rate(request.getRespirator_rate());
		existingVitalSigns.setTemperatute(request.getTemperature());
		existingVitalSigns.setWeight(request.getWeight());
		
		return vitalSignsRepo.save(existingVitalSigns);
	}

	public VitalSigns addVitalSignsRecord(VitalSigns request) {
		
		return vitalSignsRepo.save(request);
	}

}
