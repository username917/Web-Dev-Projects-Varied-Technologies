package com.walkinclinic.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clinicvisits")
public class ClinicVisit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVisit")
	private int idVisit;
	
	@Column(name = "idPatient")
	private int idPatient;
	
	@Column(name = "idDoctor")
	private int idDoctor;
	
	@Column(name = "visitDate")
	private String visitDate;

	@Column(name = "reasonVisit")
	private String reasonVisit;
	
	@Column(name = "diagnosis")
	private String diagnosis;
	
	@Column(name = "treatmentGiven")
	private String treatmentGiven;
	
	@Column(name = "followUpNeeded")
	private Boolean followUpNeeded;
	
	@Column(name = "followUpDate")
	private String followUpDate;

	public int getIdVisit() {
		return idVisit;
	}

	public void setIdVisit(int idVisit) {
		this.idVisit = idVisit;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public int getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(int idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getReasonVisit() {
		return reasonVisit;
	}

	public void setReasonVisit(String reasonVisit) {
		this.reasonVisit = reasonVisit;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatmentGiven() {
		return treatmentGiven;
	}

	public void setTreatmentGiven(String treatmentGiven) {
		this.treatmentGiven = treatmentGiven;
	}

	public Boolean getFollowUpNeeded() {
		return followUpNeeded;
	}

	public void setFollowUpNeeded(Boolean followUpNeeded) {
		this.followUpNeeded = followUpNeeded;
	}

	public String getFollowUpDate() {
		return followUpDate;
	}

	public void setFollowUpDate(String followUpDate) {
		this.followUpDate = followUpDate;
	}

	@Override
	public String toString() {
		return "ClinicVisit [idVisit=" + idVisit + ", idPatient=" + idPatient + ", idDoctor=" + idDoctor
				+ ", visitDate=" + visitDate + ", reasonVisit=" + reasonVisit + ", diagnosis=" + diagnosis
				+ ", treatmentGiven=" + treatmentGiven + ", followUpNeeded=" + followUpNeeded + ", followUpDate="
				+ followUpDate + "]";
	}

	public ClinicVisit(int idVisit, int idPatient, int idDoctor, String visitDate, String reasonVisit, String diagnosis,
			String treatmentGiven, Boolean followUpNeeded, String followUpDate) {
		super();
		this.idVisit = idVisit;
		this.idPatient = idPatient;
		this.idDoctor = idDoctor;
		this.visitDate = visitDate;
		this.reasonVisit = reasonVisit;
		this.diagnosis = diagnosis;
		this.treatmentGiven = treatmentGiven;
		this.followUpNeeded = followUpNeeded;
		this.followUpDate = followUpDate;
	}

	private ClinicVisit() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
