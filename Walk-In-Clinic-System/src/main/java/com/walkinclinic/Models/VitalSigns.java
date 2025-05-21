package com.walkinclinic.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vitalsigns")
public class VitalSigns {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVitals")
	private int idVitals;
	
	@Column(name = "idVisit")
	private int idVisit;
	
	@Column(name = "temperature")
	private double temperatute;
	
	@Column(name = "bloodPressure")
	private String bloodPressure;
	
	@Column(name = "heartRate")
	private int heartRate;
	
	@Column(name = "respiratoryRate")
	private int respiratoryRate;
	
	@Column(name = "weight")
	private int weight;
	
	@Column(name = "height")
	private int height;

	public int getIdVitals() {
		return idVitals;
	}

	public void setIdVitals(int idVitals) {
		this.idVitals = idVitals;
	}

	public int getIdVisit() {
		return idVisit;
	}

	public void setIdVisit(int idVisit) {
		this.idVisit = idVisit;
	}

	public double getTemperatute() {
		return temperatute;
	}

	public void setTemperatute(double temperatute) {
		this.temperatute = temperatute;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public int getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(int respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public VitalSigns(int idVitals, int idVisit, double temperatute, String bloodPressure, int heartRate,
			int respiratoryRate, int weight, int height) {
		super();
		this.idVitals = idVitals;
		this.idVisit = idVisit;
		this.temperatute = temperatute;
		this.bloodPressure = bloodPressure;
		this.heartRate = heartRate;
		this.respiratoryRate = respiratoryRate;
		this.weight = weight;
		this.height = height;
	}

	public VitalSigns() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "VitalSigns [idVitals=" + idVitals + ", idVisit=" + idVisit + ", temperatute=" + temperatute
				+ ", bloodPressure=" + bloodPressure + ", heartRate=" + heartRate + ", respiratoryRate="
				+ respiratoryRate + ", weight=" + weight + ", height=" + height + "]";
	}
	
	
	
}
