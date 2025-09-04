package com.walkinclinic.DTO;

import com.walkinclinic.Models.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class PatientDTO {
	
	private Integer id_patient;
	
	private String first_name;
	
	private String last_name;
	
	private String date_of_birth;
	
	private String gender;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private String emergency_contact_name;
	
	private String emergency_contact_phone;

	@Override
	public String toString() {
		return "PatientDTO [id_patient=" + id_patient +  "first_name=" + first_name + ", last_name="
				+ last_name + ", date_of_birth=" + date_of_birth + ", gender=" + gender + ", phone=" + phone
				+ ", email=" + email + ", address=" + address + ", emergency_contact_name=" + emergency_contact_name
				+ ", emergency_contact_phone=" + emergency_contact_phone + "]";
	}

	public int getId_patient() {
		return id_patient;
	}

	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
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

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmergency_contact_name() {
		return emergency_contact_name;
	}

	public void setEmergency_contact_name(String emergency_contact_name) {
		this.emergency_contact_name = emergency_contact_name;
	}

	public String getEmergency_contact_phone() {
		return emergency_contact_phone;
	}

	public void setEmergency_contact_phone(String emergency_contact_phone) {
		this.emergency_contact_phone = emergency_contact_phone;
	}
	
	private PatientDTO(Integer id_patient, Integer user_id, String first_name, String last_name, String date_of_birth,
			String gender, String phone, String email, String address, String emergency_contact_name,
			String emergency_contact_phone) {
		super();
		this.id_patient = id_patient;
	
		this.first_name = first_name;
		this.last_name = last_name;
		this.date_of_birth = date_of_birth;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.emergency_contact_name = emergency_contact_name;
		this.emergency_contact_phone = emergency_contact_phone;
	}

	public void setId_patient(Integer id_patient) {
		this.id_patient = id_patient;
	}

	PatientDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
