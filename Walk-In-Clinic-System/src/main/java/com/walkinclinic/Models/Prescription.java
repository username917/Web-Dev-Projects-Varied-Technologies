package com.walkinclinic.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="isPrescription")
	private int idPrescription;
	
	@Column(name = "idVisit")
	private int idVisit;
	
	@Column(name = "drugName")
	private String drugName;
	
	@Column(name = "dosage")
	private String dosage;
	
	@Column(name = "frequency")
	private String frequency;
	
	@Column(name = "duration")
	private String duration;
	
	@Column(name  = "notes")
	private String notes;

	public int getIdPrescription() {
		return idPrescription;
	}

	public void setIdPrescription(int idPrescription) {
		this.idPrescription = idPrescription;
	}

	public int getIdVisit() {
		return idVisit;
	}

	public void setIdVisit(int idVisit) {
		this.idVisit = idVisit;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Prescription [idPrescription=" + idPrescription + ", idVisit=" + idVisit + ", drugName=" + drugName
				+ ", dosage=" + dosage + ", frequency=" + frequency + ", duration=" + duration + ", notes=" + notes
				+ "]";
	}

	private Prescription(int idPrescription, int idVisit, String drugName, String dosage, String frequency,
			String duration, String notes) {
		super();
		this.idPrescription = idPrescription;
		this.idVisit = idVisit;
		this.drugName = drugName;
		this.dosage = dosage;
		this.frequency = frequency;
		this.duration = duration;
		this.notes = notes;
	}

	private Prescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
