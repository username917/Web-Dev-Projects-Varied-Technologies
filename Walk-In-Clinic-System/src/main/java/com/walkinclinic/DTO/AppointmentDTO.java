package com.walkinclinic.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppointmentDTO {
	
	@JsonProperty("id_appointment")	
	private int id_appointment;
	
	@JsonProperty("id_patient")
	private int id_patient;
	
	@JsonProperty("id_doctor")
	private int id_doctor;
	
	@JsonProperty("date_appointment")
	private String date_appointment;
	
	private String status;
	
	private String notes;

	public int getId_appointment() {
		return id_appointment;
	}

	public void setId_appointment(int id_appointment) {
		this.id_appointment = id_appointment;
	}

	public int getId_patient() {
		return id_patient;
	}

	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}

	public int getId_doctor() {
		return id_doctor;
	}

	public void setId_doctor(int id_doctor) {
		this.id_doctor = id_doctor;
	}

	public String getDate_appointment() {
		return date_appointment;
	}

	public void setDate_appointment(String date_appointment) {
		this.date_appointment = date_appointment;
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

	private AppointmentDTO(int id_appointment, int id_patient, int id_doctor, String date_appointment, String status,
			String notes) {
		super();
		this.id_appointment = id_appointment;
		this.id_patient = id_patient;
		this.id_doctor = id_doctor;
		this.date_appointment = date_appointment;
		this.status = status;
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "AppointmentDTO [id_appointment=" + id_appointment + ", id_patient=" + id_patient + ", id_doctor="
				+ id_doctor + ", date_appointment=" + date_appointment + ", status=" + status + ", notes=" + notes
				+ "]";
	}

	
	
}