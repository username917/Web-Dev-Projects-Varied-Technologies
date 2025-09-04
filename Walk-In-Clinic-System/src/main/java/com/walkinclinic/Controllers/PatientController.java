package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.DTO.PatientDTO;
import com.walkinclinic.Services.PatientsService;

@RestController
@RequestMapping("/api")
public class PatientController {

  private final PatientsService patientService;

  public PatientController(PatientsService patientService) {
    this.patientService = patientService;
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(value="/get-patient-records", produces="application/json")
  public ResponseEntity<List<PatientDTO>> viewAndSearchPatientRecords() {
    List<PatientDTO> list = patientService.retrievePatientRecords();
    return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(value="/create-patient-record", consumes="application/json", produces="application/json")
  public ResponseEntity<PatientDTO> addPatientRecord(@RequestBody PatientDTO dto) {
    PatientDTO saved = patientService.addPatientRecord(dto);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping(value="/update-patient-record", consumes="application/json", produces="application/json")
  public ResponseEntity<PatientDTO> editPatientRecord(
      @RequestParam("id_patient") Integer id_patient,
      @RequestBody PatientDTO dto) {

    if (dto.getId_patient() == 0 || !id_patient.equals(dto.getId_patient())) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(patientService.editPatientRecord(id_patient, dto));
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/delete-patient-record")
  public ResponseEntity<?> deletePatientRecord(@RequestParam("id_patient") int id_patient) {
    return patientService.deletePatientRecord(id_patient)
        ? ResponseEntity.ok("Patient record removed sucessfully!")
        : ResponseEntity.notFound().build();
  }
}

/*
@RestController
@RequestMapping("/api")
public class PatientController {
	
	@Autowired
	PatientsService patientService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/get-patient-records")
	public ResponseEntity<List<Patient>> viewAndSearchPatientRecords() {
		
		List<Patient> patientRecordHistory = patientService.retrievePatientRecords();
		
		if (patientRecordHistory.isEmpty()) {
			
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(patientRecordHistory);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete-patient-record")
	public ResponseEntity<?> deletePatientRecord(@RequestParam("id_patient") int id_patient) {
		
		boolean deleted = patientService.deletePatientRecor(id_patient);
		
		if (deleted) {
			return ResponseEntity.ok("Patient record removed sucessfully!");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update-patient-record")
	public ResponseEntity<Patient> editPatientRecord (@RequestParam("id_patient") Integer id_patient, @RequestBody PatientDTO patientRecordDTO) {
	
		System.out.println("Editing patient record...");
		
		if (!id_patient.equals(patientRecordDTO.getId_patient())) {
			return ResponseEntity.badRequest().build();
		}
		
		System.out.println("Patient record DTO at the API is: " + patientRecordDTO);
		
		Patient patientRecord = patientService.editPatientRecord(id_patient, patientRecordDTO);
		
		return ResponseEntity.ok(patientRecord);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/create-patient-record")
	public ResponseEntity<Patient> addPatientRecord(@RequestBody Patient patientRecord) {
		
		Patient patientRec = patientService.addPatientRecord(patientRecord);
		
		return new ResponseEntity<>(patientRec, HttpStatus.CREATED);
	}

}
*/
