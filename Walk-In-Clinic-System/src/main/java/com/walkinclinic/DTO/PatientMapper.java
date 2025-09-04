package com.walkinclinic.DTO;

import org.springframework.stereotype.Component;

import com.walkinclinic.Models.Patient;

@Component
public class PatientMapper {
  public PatientDTO toDto(Patient p) {
    PatientDTO dto = new PatientDTO();
    dto.setId_patient(p.getId_patient());
    dto.setFirst_name(p.getFirst_name());
    dto.setLast_name(p.getLast_name());
    dto.setDate_of_birth(p.getDate_of_birth());
    dto.setGender(p.getGender());
    dto.setPhone(p.getPhone());
    dto.setEmail(p.getEmail());
    dto.setAddress(p.getAddress());
    dto.setEmergency_contact_name(p.getEmergency_contact_name());
    dto.setEmergency_contact_phone(p.getEmergency_contact_phone());
    return dto;
  }

  public void copyFromDto(PatientDTO dto, Patient p) {
    p.setFirst_name(dto.getFirst_name());
    p.setLast_name(dto.getLast_name());
    p.setDate_of_birth(dto.getDate_of_birth());
    p.setGender(dto.getGender());
    p.setPhone(dto.getPhone());
    p.setEmail(dto.getEmail());
    p.setAddress(dto.getAddress());
    p.setEmergency_contact_name(dto.getEmergency_contact_name());
    p.setEmergency_contact_phone(dto.getEmergency_contact_phone());
  }
}

