package com.walkinclinic.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkinclinic.DTO.PrescriptionDTO;
import com.walkinclinic.Models.Prescription;
import com.walkinclinic.repository.PrescriptionRepository;

@Service
public class PrescriptionService {
	
	@Autowired
	PrescriptionRepository prescriptionRepo;

	public List<PrescriptionDTO> retrievePrescriptions() {
		// TODO Auto-generated method stub
		return prescriptionRepo.retrrievePrescription();
	}

	public boolean deletePrescription(Integer id_prescription) {
		
		int rowDeleted = prescriptionRepo.deletePrescription(id_prescription);
		
		return rowDeleted > 0;
	}

	public Prescription editPrescription(Integer id_prescription, PrescriptionDTO request) {
		
		Prescription existingPrescription = prescriptionRepo.findById(id_prescription)
				.orElseThrow(() -> new RuntimeException("Prescription not found"));
		
		existingPrescription.setId_prescription(request.getId_prescription());
		existingPrescription.setDosage(request.getDosage());
		existingPrescription.setDrug_name(request.getDrugName());
		existingPrescription.setDuration(request.getDuration());
		existingPrescription.setFrequency(request.getFrequency());
		existingPrescription.setNotes(request.getNotes());
		
		return prescriptionRepo.save(existingPrescription);
	}

	public Prescription addPrescription(Prescription request) {

		return prescriptionRepo.save(request);
	}

}
