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
	@Column(name ="id_prescription")
	private int id_prescription;
	
	@Column(name = "id_visit")
	private int id_visit;
	
	@Column(name = "drug_name")
	private String drug_name;
	
	@Column(name = "dosage")
	private String dosage;
	
	@Column(name = "frequency")
	private String frequency;
	
	@Column(name = "duration")
	private String duration;
	
	@Column(name  = "notes")
	private String notes;

	public int getId_prescription() {
		return id_prescription;
	}

	public void setId_prescription(int id_prescription) {
		this.id_prescription = id_prescription;
	}

	public int getId_visit() {
		return id_visit;
	}

	public void setId_visit(int id_visit) {
		this.id_visit = id_visit;
	}

	public String getDrug_name() {
		return drug_name;
	}

	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
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
		return "Prescription [id_prescription=" + id_prescription + ", id_visit=" + id_visit + ", drug_name="
				+ drug_name + ", dosage=" + dosage + ", frequency=" + frequency + ", duration=" + duration + ", notes="
				+ notes + "]";
	}

	private Prescription(int id_prescription, int id_visit, String drug_name, String dosage, String frequency,
			String duration, String notes) {
		super();
		this.id_prescription = id_prescription;
		this.id_visit = id_visit;
		this.drug_name = drug_name;
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
