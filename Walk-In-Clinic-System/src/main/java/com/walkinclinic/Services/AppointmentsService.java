package com.walkinclinic.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkinclinic.DTO.AppointmentDTO;
import com.walkinclinic.Models.Appointment;
import com.walkinclinic.repository.AppointmentsRepository;

@Service
public class AppointmentsService {
	
	@Autowired
	AppointmentsRepository apptRepo;
	// checking doctor availability
	
	public String checkDoctorAvailability(String docName) {
		
		return apptRepo.checkDoctorAvailability(docName);
		
	}
	
	
	// book an appointment
	
	public Appointment bookAppointment(Appointment apptData) {
		
		return apptRepo.save(apptData);
		
	}
	
	
	
	// reschedule appointment
	
	public Appointment rescheduleAppointment(Integer apptid, AppointmentDTO appointmentDTO) {

	    Appointment existingAppt = apptRepo.findById(apptid)
	            .orElseThrow(() -> new RuntimeException("Appointment not found with specified id."));

	    existingAppt.setId_patient(appointmentDTO.getId_patient());
	    existingAppt.setId_doctor(appointmentDTO.getId_doctor());
	    existingAppt.setDate_appointment(appointmentDTO.getDate_appointment());
	    existingAppt.setStatus(appointmentDTO.getStatus());
	    existingAppt.setNotes(appointmentDTO.getNotes());

	    return apptRepo.save(existingAppt);
	}

	
	
	// provide reason for appointment
	
	
	// send reminder
	
	public void sendReminder(Integer apptId) {
		
		
	}
	
	
	// cancel appointment
	
	public boolean cancelAppointment(Integer id_appointment) {
		
		int rowsDeleted = apptRepo.cancelAppointment(id_appointment);
		
		return rowsDeleted > 0;
		
	}
	
	
	// view and search appointment history
	
	public List<Appointment> viewAndSearchApptHistory() {
		
		return apptRepo.findAll();
	
	}
	
	
	// manage appointment status
	
	public void changeAppointmentStatus(Integer apptId) {
		
		
	}




}
