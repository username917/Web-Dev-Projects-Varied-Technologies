package com.walkinclinic.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_doctor")
	private int id_Doctor;
	
	@OneToOne
    @JoinColumn(name = "userid")
    private User user;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "specialty")
	private String specialty;
	
	@Column(name = "availability")
	private String availability;
	
	@Column(name = "contact_info")
	private String contact_info;
	
	@Column(name = "education")
	private String education;

	public int getId_Doctor() {
		return id_Doctor;
	}

	public void setId_Doctor(int id_Doctor) {
		this.id_Doctor = id_Doctor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	private Doctor(int id_Doctor, User user, String first_name, String last_name, String specialty, String availability,
			String contact_info, String education) {
		super();
		this.id_Doctor = id_Doctor;
		this.user = user;
		this.first_name = first_name;
		this.last_name = last_name;
		this.specialty = specialty;
		this.availability = availability;
		this.contact_info = contact_info;
		this.education = education;
	}

	private Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
