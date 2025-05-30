package com.walkinclinic.DTO;

import jakarta.persistence.Column;

public class DoctorDTO {
	
	private int idDoctor;
	
	private String fName;
	
	private String lName;
	
	private String specialty;
	
	private String availability;
	
	private String contactInfo;
	
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

	private DoctorDTO(int idDoctor, String fName, String lName, String specialty, String availability,
			String contactInfo, String education) {
		super();
		this.idDoctor = idDoctor;
		this.fName = fName;
		this.lName = lName;
		this.specialty = specialty;
		this.availability = availability;
		this.contactInfo = contactInfo;
		this.education = education;
	}
	
	

}
