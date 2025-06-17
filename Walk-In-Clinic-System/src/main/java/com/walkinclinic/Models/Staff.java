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
	@Column(name = "idStaff")
	private int idStaff;
	
	@Column(name = "userid")
	private int userid;
	
	@OneToOne
    @JoinColumn(name = "userid")
    private User user;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	// test another new hcange
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	public int getIdStaff() {
		return idStaff;
	}

	public void setIdStaff(int idStaff) {
		this.idStaff = idStaff;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	private Staff(int idStaff, int userid, String role, String firstName, String lastName, String email, String phone,
			String username, String password) {
		super();
		this.idStaff = idStaff;
		this.userid = userid;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}

	private Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
