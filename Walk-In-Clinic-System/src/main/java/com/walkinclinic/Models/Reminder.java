package com.walkinclinic.Models;

public class Reminder {
	
	private int clientId;
	private int apptId;
	private int numberDays;
	private String reminderText;
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getApptId() {
		return apptId;
	}
	public void setApptId(int apptId) {
		this.apptId = apptId;
	}
	public int getNumberDays() {
		return numberDays;
	}
	public void setNumberDays(int numberDays) {
		this.numberDays = numberDays;
	}
	public String getReminderText() {
		return reminderText;
	}
	public void setReminderText(String reminderText) {
		this.reminderText = reminderText;
	}
	private Reminder(int clientId, int apptId, int numberDays, String reminderText) {
		super();
		this.clientId = clientId;
		this.apptId = apptId;
		this.numberDays = numberDays;
		this.reminderText = reminderText;
	}

}
