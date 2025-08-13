package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.DTO.AppointmentDTO;
import com.walkinclinic.Models.Appointment;
import com.walkinclinic.Services.AppointmentsService;

@CrossOrigin
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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/book-appointment")
	public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {
		
		Appointment appt = apptService.bookAppointment(appointment);
		
		return new ResponseEntity<>(appt, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update-appointment")
	public ResponseEntity<Appointment> rescheduleAppointment(@RequestParam Integer id_appointment, @RequestBody AppointmentDTO appointmentDTO) {
		
		System.out.println("The value of the dto being sent in is: " + appointmentDTO);
	
		
		if (!id_appointment.equals(appointmentDTO.getId_appointment())) {
			
			return ResponseEntity.badRequest().build();
			
		}
		Appointment appt = apptService.rescheduleAppointment(id_appointment, appointmentDTO);
		
		return ResponseEntity.ok(appt);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete-appointment")
	public ResponseEntity<?> deleteAppointment(@RequestParam("id_appointment") Integer id_appointment) {
		
		boolean deleted = apptService.cancelAppointment(id_appointment);
		
		if (deleted) {
			return ResponseEntity.ok("Appointment cancelled successfully!");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
