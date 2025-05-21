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
	@Column(name = "idAppointmnent")
	private int idAppointment;
	
	@Column(name = "idPatient")
	private int idPatient;
	
	@Column(name = "idDoctor")
	private int idDoctor;
	
	@Column(name = "dateAppointment")
	private String dateAppointment;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "notes")
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

	public Appointment(int idAppointment, int idPatient, int idDoctor, String dateAppointment, String status,
			String notes) {
		super();
		this.idAppointment = idAppointment;
		this.idPatient = idPatient;
		this.idDoctor = idDoctor;
		this.dateAppointment = dateAppointment;
		this.status = status;
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Appointment [idAppointment=" + idAppointment + ", idPatient=" + idPatient + ", idDoctor=" + idDoctor
				+ ", dateAppointment=" + dateAppointment + ", status=" + status + ", notes=" + notes + "]";
	}

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
