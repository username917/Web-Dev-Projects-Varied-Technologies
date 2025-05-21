package com.walkinclinic.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "labresults")
public class LabResults {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLabResult")
	private int idLabResult;
	
	@Column(name = "idVisit")
	private int idVisit;
	
	@Column(name = "testType")
	private String testType;
	
	@Column(name = "result")
	private String result;
	
	@Column(name = "units")
	private String units;
	
	@Column(name = "normalRange")
	private String normalRange;
	
	@Column(name = "dateConducted")
	private String dateConducted;

	public int getIdLabResult() {
		return idLabResult;
	}

	public void setIdLabResult(int idLabResult) {
		this.idLabResult = idLabResult;
	}

	public int getIdVisit() {
		return idVisit;
	}

	public void setIdVisit(int idVisit) {
		this.idVisit = idVisit;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getNormalRange() {
		return normalRange;
	}

	public void setNormalRange(String normalRange) {
		this.normalRange = normalRange;
	}

	public String getDateConducted() {
		return dateConducted;
	}

	public void setDateConducted(String dateConducted) {
		this.dateConducted = dateConducted;
	}

	@Override
	public String toString() {
		return "LabResults [idLabResult=" + idLabResult + ", idVisit=" + idVisit + ", testType=" + testType
				+ ", result=" + result + ", units=" + units + ", normalRange=" + normalRange + ", dateConducted="
				+ dateConducted + "]";
	}

	private LabResults(int idLabResult, int idVisit, String testType, String result, String units, String normalRange,
			String dateConducted) {
		super();
		this.idLabResult = idLabResult;
		this.idVisit = idVisit;
		this.testType = testType;
		this.result = result;
		this.units = units;
		this.normalRange = normalRange;
		this.dateConducted = dateConducted;
	}

	private LabResults() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
