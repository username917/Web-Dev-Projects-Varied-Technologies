package com.walkinclinic.DTO;


// this DTO will be used to encapsulate the login data sent to the API

public class LoginData {
	
	String username;
	String password;
	
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
	private LoginData(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	private LoginData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
