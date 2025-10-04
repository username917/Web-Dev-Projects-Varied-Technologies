package com.walkinclinic.DTO;

public class PrescriptionDTO {
	
	private Integer id_prescription;
    private String drugName;
    private String dosage;
    private String frequency;
    private String duration;
    private String notes;
    
	public Integer getId_prescription() {
		return id_prescription;
	}
	public void setId_prescription(Integer id_prescription) {
		this.id_prescription = id_prescription;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "PrescriptionDTO [id_prescription=" + id_prescription + ", drugName=" + drugName + ", dosage=" + dosage
				+ ", frequency=" + frequency + ", duration=" + duration + ", notes=" + notes + "]";
	}
	
	
    
    
}
