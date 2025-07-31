package com.walkinclinic.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "billing")
public class Billing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_billing")
	private int idBilling;
	
	@Column(name = "id_visit")
	private int idVisit;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "insurance_policy")
	private String insurancePolicy;
	
	@Column(name = "payment_status")
	private String paymentStatus;

	public int getIdBilling() {
		return idBilling;
	}

	public void setIdBilling(int idBilling) {
		this.idBilling = idBilling;
	}

	public int getIdVisit() {
		return idVisit;
	}

	public void setIdVisit(int idVisit) {
		this.idVisit = idVisit;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getInsurancePolicy() {
		return insurancePolicy;
	}

	public void setInsurancePolicy(String insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Billing [idBilling=" + idBilling + ", idVisit=" + idVisit + ", amount=" + amount
				+ ", insurancePolicy=" + insurancePolicy + ", paymentStatus=" + paymentStatus + "]";
	}

	public Billing(int idBilling, int idVisit, double amount, String insurancePolicy, String paymentStatus) {
		super();
		this.idBilling = idBilling;
		this.idVisit = idVisit;
		this.amount = amount;
		this.insurancePolicy = insurancePolicy;
		this.paymentStatus = paymentStatus;
	}

	public Billing() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
