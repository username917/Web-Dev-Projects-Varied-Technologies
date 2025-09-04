package com.walkinclinic.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkinclinic.DTO.PatientDTO;
import com.walkinclinic.DTO.PatientMapper;
import com.walkinclinic.Models.Patient;
import com.walkinclinic.repository.PatientsRepository;


@Service
public class PatientsService {
	
  private final PatientsRepository patientRepo;
  private final PatientMapper mapper;

  public PatientsService(PatientsRepository patientRepo, PatientMapper mapper) {
    this.patientRepo = patientRepo; this.mapper = mapper;
  }

  public List<PatientDTO> retrievePatientRecords() {
    return patientRepo.findAll().stream().map(mapper::toDto).toList();
  }

  public PatientDTO addPatientRecord(PatientDTO dto) {
    Patient p = new Patient();
    mapper.copyFromDto(dto, p);
    // user link stays null for now (you'll add the reassignment endpoint later)
    return mapper.toDto(patientRepo.save(p));
  }

  public PatientDTO editPatientRecord(Integer id, PatientDTO dto) {
    Patient p = patientRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Patient record not found"));
    mapper.copyFromDto(dto, p);
    return mapper.toDto(patientRepo.save(p));
  }

  public boolean deletePatientRecord(int id_patient) {
    if (!patientRepo.existsById(id_patient)) return false;
    patientRepo.deletePatientRecord(id_patient);
    return true;
  }
}


/*
@Service
public class PatientsService {
	
	@Autowired
	PatientsRepository patientRepo;

	public List<Patient> retrievePatientRecords() {

		return patientRepo.retrievePatientRecords();
	}

	public boolean deletePatientRecor(int id_patient) {

		int rowDeleted = patientRepo.deletePatientRecord(id_patient);
		
		return rowDeleted > 0;
	}
	
	public Patient editPatientRecord(Integer id_patient, PatientDTO patientRecordDTO) {
		
		Patient existingPatientRecord = patientRepo.findById(id_patient)
				.orElseThrow(() -> new RuntimeException("Patient record not found"));
		
		existingPatientRecord.setAddress(patientRecordDTO.getAddress());
		existingPatientRecord.setDate_of_birth(patientRecordDTO.getDate_of_birth());
		existingPatientRecord.setEmail(patientRecordDTO.getEmail());
		existingPatientRecord.setEmergency_contact_name(patientRecordDTO.getEmergency_contact_name());
		existingPatientRecord.setEmergency_contact_phone(patientRecordDTO.getEmergency_contact_phone());
		existingPatientRecord.setFirst_name(patientRecordDTO.getFirst_name());
		existingPatientRecord.setLast_name(patientRecordDTO.getLast_name());
		existingPatientRecord.setGender(patientRecordDTO.getGender());
		existingPatientRecord.setPhone(patientRecordDTO.getPhone());
		existingPatientRecord.setId_patient(patientRecordDTO.getId_patient());
		
		return patientRepo.save(existingPatientRecord);
		
	}

	public Patient addPatientRecord(Patient patientRecord) {
		
		return patientRepo.save(patientRecord);
	}

}
*/
