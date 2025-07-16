package com.walkinclinic.DTO;

import jakarta.persistence.Column;

public class DoctorDTO {
	
	private int idDoctor;
	
	private String first_name;
	
	private String last_name;
	
	private String specialty;
	
	private String availability;
	
	private String contact_info;
	
	private String education;

	public int getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public String getContact_info() {
		return contact_info;
	}

	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	
	public DoctorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorDTO(int idDoctor, String first_name, String last_name, String specialty, String availability,
			String contact_info, String education) {
		super();
		this.idDoctor = idDoctor;
		this.first_name = first_name;
		this.last_name = last_name;
		this.specialty = specialty;
		this.availability = availability;
		this.contact_info = contact_info;
		this.education = education;
	}
	
	

}
