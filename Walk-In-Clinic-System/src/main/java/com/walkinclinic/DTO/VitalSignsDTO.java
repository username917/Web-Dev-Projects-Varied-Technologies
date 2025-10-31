package com.walkinclinic.DTO;

public class VitalSignsDTO {
	
	private Integer id_vitals;
	private Integer	id_visit;
	private double temperature;
	private String blood_pressure;
	private Integer heart_rate;
	private Integer respiratory_rate;
	private Integer weight;
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
	private VitalSignsDTO(Integer id_vitals, Integer id_visit, double temperature, String blood_pressure,
			Integer heart_rate, Integer respiratory_rate, Integer weight, Integer height) {
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
	private VitalSignsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
