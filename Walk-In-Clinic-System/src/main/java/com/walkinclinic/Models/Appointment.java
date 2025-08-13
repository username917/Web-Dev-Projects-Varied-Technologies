package com.walkinclinic.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_appointment")
	private int id_appointment;
	
	@Column(name = "id_patient")
	private int id_patient;
	
	@Column(name = "id_doctor")
	private int id_doctor;
	
	@Column(name = "date_appointment")
	private String date_appointment;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "notes")
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

	@Override
	public String toString() {
		return "Appointment [id_appointment=" + id_appointment + ", id_patient=" + id_patient + ", id_doctor="
				+ id_doctor + ", date_appointment=" + date_appointment + ", status=" + status + ", notes=" + notes
				+ "]";
	}

	private Appointment(int id_appointment, int id_patient, int id_doctor, String date_appointment, String status,
			String notes) {
		super();
		this.id_appointment = id_appointment;
		this.id_patient = id_patient;
		this.id_doctor = id_doctor;
		this.date_appointment = date_appointment;
		this.status = status;
		this.notes = notes;
	}

	private Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}


}