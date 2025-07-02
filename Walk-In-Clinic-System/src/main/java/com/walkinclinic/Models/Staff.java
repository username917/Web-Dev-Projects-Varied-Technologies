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
@Table(name = "staff")
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_staff")
	private int id_staff;
	
	@OneToOne
    @JoinColumn(name = "userid")
    private User user;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;

	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;

	public int getId_staff() {
		return id_staff;
	}

	public void setId_staff(int id_staff) {
		this.id_staff = id_staff;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	private Staff(int id_staff, User user, String role, String first_name, String last_name, String email, String phone) {
		super();
		this.id_staff = id_staff;
		this.user = user;
		this.role = role;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;

	}

	private Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
