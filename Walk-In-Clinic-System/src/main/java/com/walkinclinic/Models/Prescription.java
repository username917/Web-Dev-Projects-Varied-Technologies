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

	

	
}
