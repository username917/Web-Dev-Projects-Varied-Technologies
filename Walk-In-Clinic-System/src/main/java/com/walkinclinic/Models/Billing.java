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
	@Column(name = "idBilling")
	private int idBilling;
	
	@Column(name = "idVisit")
	private int idVisit;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "insuranceProvider")
	private String insuranceProvider;
	
	@Column(name = "paymentStatus")
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

	public String getInsuranceProvider() {
		return insuranceProvider;
	}

	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
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
				+ ", insuranceProvider=" + insuranceProvider + ", paymentStatus=" + paymentStatus + "]";
	}

	public Billing(int idBilling, int idVisit, double amount, String insuranceProvider, String paymentStatus) {
		super();
		this.idBilling = idBilling;
		this.idVisit = idVisit;
		this.amount = amount;
		this.insuranceProvider = insuranceProvider;
		this.paymentStatus = paymentStatus;
	}

	public Billing() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
