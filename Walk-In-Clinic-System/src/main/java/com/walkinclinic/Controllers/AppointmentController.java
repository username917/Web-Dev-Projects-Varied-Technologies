package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.Models.Appointment;
import com.walkinclinic.Services.AppointmentsService;

@RestController
@RequestMapping("/api")
public class AppointmentController {
	
	@Autowired
	AppointmentsService apptService;
	
	@GetMapping("/view-appointments")
	public ResponseEntity<List<Appointment>> viewAndSearchApptHistory() {
		
		List<Appointment> apptHistory = apptService.viewAndSearchApptHistory();
		
		if (apptHistory.isEmpty()) {
			
			return ResponseEntity.noContent().build();		}
		
		return ResponseEntity.ok(apptHistory);
		
	}

}
