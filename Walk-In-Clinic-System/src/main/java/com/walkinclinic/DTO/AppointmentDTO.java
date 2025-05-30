package com.walkinclinic.DTO;

import jakarta.persistence.Column;

public class AppointmentDTO {
	
	private int idAppointment;
	
	
	private int idPatient;
	
	
	private int idDoctor;
	
	
	private String dateAppointment;
	
	
	private String status;
	
	
	private String notes;


	public int getIdAppointment() {
		return idAppointment;
	}


	public void setIdAppointment(int idAppointment) {
		this.idAppointment = idAppointment;
	}


	public int getIdPatient() {
		return idPatient;
	}


	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}


	public int getIdDoctor() {
		return idDoctor;
	}


	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}


	public String getDateAppointment() {
		return dateAppointment;
	}


	public void setDateAppointment(String dateAppointment) {
		this.dateAppointment = dateAppointment;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	private AppointmentDTO(int idAppointment, int idPatient, int idDoctor, String dateAppointment, String status,
			String notes) {
		super();
		this.idAppointment = idAppointment;
		this.idPatient = idPatient;
		this.idDoctor = idDoctor;
		this.dateAppointment = dateAppointment;
		this.status = status;
		this.notes = notes;
	}
	
	

}
