package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.DTO.AppointmentDTO;
import com.walkinclinic.Models.Appointment;
import com.walkinclinic.Services.AppointmentsService;

@RestController
@RequestMapping("/api")
public class AppointmentController {
	
	@Autowired
	AppointmentsService apptService;
	
	@GetMapping("/check-doctor-availability")
	public String checkDoctorAvaialbility(String docName) {
		
		// NEED TO HANDLE DESERIALIZATION OF DOCTOR NAME INTO FIRST AND LAST NAME
		
		return apptService.checkDoctorAvailability(docName);
	}
	
	@GetMapping("/view-appointments")
	public ResponseEntity<List<Appointment>> viewAndSearchApptHistory() {
		
		List<Appointment> apptHistory = apptService.viewAndSearchApptHistory();
		
		if (apptHistory.isEmpty()) {
			
			return ResponseEntity.noContent().build();		}
		
		return ResponseEntity.ok(apptHistory);
		
	}

	@PostMapping("/book-appointment")
	public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {
		
		Appointment appt = apptService.bookAppointment(appointment);
		
		return new ResponseEntity<>(appt, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-appointment")
	public ResponseEntity<Appointment> rescheduleAppointment(@RequestParam("idAppointment") Integer idAppointment, @RequestBody AppointmentDTO appointmentDTO) {
		
		if (!idAppointment.equals(appointmentDTO.getIdAppointment())) {
			
			return ResponseEntity.badRequest().build();
			
		}
		Appointment appt = apptService.rescheduleAppointment(idAppointment, appointmentDTO);
		
		return ResponseEntity.ok(appt);
	}
	
	@DeleteMapping("/delete-appointment")
	public ResponseEntity<?> deleteAppointment(@RequestParam("idAppointment") Integer idAppointment) {
		
		boolean deleted = apptService.cancelAppointment(idAppointment);
		
		if (deleted) {
			return ResponseEntity.ok("Appointment cancelled successfully!");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
