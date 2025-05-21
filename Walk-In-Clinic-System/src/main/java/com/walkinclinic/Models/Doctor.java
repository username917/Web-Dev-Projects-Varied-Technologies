package com.walkinclinic.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDoctor")
	private int idDoctor;
	
	@Column(name = "fName")
	private String fName;
	
	@Column(name = "lName")
	private String lName;
	
	@Column(name = "specialty")
	private String specialty;
	
	@Column(name = "availability")
	private String availability;
	
	@Column(name = "contactInfo")
	private String contactInfo;
	
	@Column(name = "education")
	private String education;

	public int getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Override
	public String toString() {
		return "Doctor [idDoctor=" + idDoctor + ", fName=" + fName + ", lName=" + lName + ", specialty=" + specialty
				+ ", availability=" + availability + ", contactInfo=" + contactInfo + ", education=" + education + "]";
	}

	private Doctor(int idDoctor, String fName, String lName, String specialty, String availability, String contactInfo,
			String education) {
		super();
		this.idDoctor = idDoctor;
		this.fName = fName;
		this.lName = lName;
		this.specialty = specialty;
		this.availability = availability;
		this.contactInfo = contactInfo;
		this.education = education;
	}

	private Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
