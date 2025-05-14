package com.walkinclinic.DTO;

import java.util.Date;

public class InsuranceReport {

	String patient;
	String doctor;
	String insuranceClaim;
	Boolean outcome;
	Long cost;
	Long balance;
	Date nextAppt;
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getInsuranceClaim() {
		return insuranceClaim;
	}
	public void setInsuranceClaim(String insuranceClaim) {
		this.insuranceClaim = insuranceClaim;
	}
	public Boolean getOutcome() {
		return outcome;
	}
	public void setOutcome(Boolean outcome) {
		this.outcome = outcome;
	}
	public Long getCost() {
		return cost;
	}
	public void setCost(Long cost) {
		this.cost = cost;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public Date getNextAppt() {
		return nextAppt;
	}
	public void setNextAppt(Date nextAppt) {
		this.nextAppt = nextAppt;
	}
	public InsuranceReport(String patient, String doctor, String insuranceClaim, Boolean outcome, Long cost,
			Long balance, Date nextAppt) {
		super();
		this.patient = patient;
		this.doctor = doctor;
		this.insuranceClaim = insuranceClaim;
		this.outcome = outcome;
		this.cost = cost;
		this.balance = balance;
		this.nextAppt = nextAppt;
	}
	public InsuranceReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
