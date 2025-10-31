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
	@Column(name = "id_vitals")
	private Integer id_vitals;
	
	@Column(name = "id_visit")
	private Integer id_visit;
	
	@Column(name = "temperature")
	private double temperature;
	
	@Column(name = "blood_pressure")
	private String blood_pressure;
	
	@Column(name = "heart_rate")
	private Integer heart_rate;
	
	@Column(name = "respiratory_rate")
	private Integer respiratory_rate;
	
	@Column(name = "weight")
	private Integer weight;
	
	@Column(name = "height")
	private Integer height;

	public Integer getId_vitals() {
		return id_vitals;
	}

	public void setId_vitals(Integer id_vitals) {
		this.id_vitals = id_vitals;
	}

	public Integer getId_visit() {
		return id_visit;
	}

	public void setId_visit(Integer id_visit) {
		this.id_visit = id_visit;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public String getBlood_pressure() {
		return blood_pressure;
	}

	public void setBlood_pressure(String blood_pressure) {
		this.blood_pressure = blood_pressure;
	}

	public Integer getHeart_rate() {
		return heart_rate;
	}

	public void setHeart_rate(Integer heart_rate) {
		this.heart_rate = heart_rate;
	}

	public Integer getRespiratory_rate() {
		return respiratory_rate;
	}

	public void setRespiratory_rate(Integer respiratory_rate) {
		this.respiratory_rate = respiratory_rate;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "VitalSigns [id_vitals=" + id_vitals + ", id_visit=" + id_visit + ", temperature=" + temperature
				+ ", blood_pressure=" + blood_pressure + ", heart_rate=" + heart_rate + ", respiratory_rate="
				+ respiratory_rate + ", weight=" + weight + ", height=" + height + "]";
	}

	private VitalSigns(Integer id_vitals, Integer id_visit, double temperature, String blood_pressure, Integer heart_rate,
			Integer respiratory_rate, Integer weight, Integer height) {
		super();
		this.id_vitals = id_vitals;
		this.id_visit = id_visit;
		this.temperature = temperature;
		this.blood_pressure = blood_pressure;
		this.heart_rate = heart_rate;
		this.respiratory_rate = respiratory_rate;
		this.weight = weight;
		this.height = height;
	}

	private VitalSigns() {
		super();
		// TODO Auto-generated constructor stub
	}

}
