package com.walkinclinic.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "availability")
public class Availability {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAvailability")
	private int idAvailability;
	
	@Column(name = "idDoctor")
	private int idDoctor;
	
	@Column(name = "dayOfWeek")
	private String dayOfWeek;
	
	@Column(name = "startTime")
	private String startTime;
	
	@Column(name = "endTime")
	private String endTime;

	public int getIdAvailability() {
		return idAvailability;
	}

	public void setIdAvailability(int idAvailability) {
		this.idAvailability = idAvailability;
	}

	public int getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Availability [idAvailability=" + idAvailability + ", idDoctor=" + idDoctor + ", dayOfWeek=" + dayOfWeek
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	public Availability(int idAvailability, int idDoctor, String dayOfWeek, String startTime, String endTime) {
		super();
		this.idAvailability = idAvailability;
		this.idDoctor = idDoctor;
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Availability() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
