package com.walkinclinic.DTO;

import jakarta.persistence.Column;

public class LabResultsDTO {
	
private int id_lab_result;
	
	
	private int id_visit;
	
	
	private String test_type;
	
	
	private String result;
	
	
	private String units;
	
	private String normal_range;
	
	
	private String date_conducted;


	public int getId_lab_result() {
		return id_lab_result;
	}


	public void setId_lab_result(int id_lab_result) {
		this.id_lab_result = id_lab_result;
	}


	public int getId_visit() {
		return id_visit;
	}


	public void setId_visit(int id_visit) {
		this.id_visit = id_visit;
	}


	public String getTest_type() {
		return test_type;
	}


	public void setTest_type(String test_type) {
		this.test_type = test_type;
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


	public String getNormal_range() {
		return normal_range;
	}


	public void setNormal_range(String normal_range) {
		this.normal_range = normal_range;
	}


	public String getDate_conducted() {
		return date_conducted;
	}


	public void setDate_conducted(String date_conducted) {
		this.date_conducted = date_conducted;
	}


	private LabResultsDTO(int id_lab_result, int id_visit, String test_type, String result, String units,
			String normal_range, String date_conducted) {
		super();
		this.id_lab_result = id_lab_result;
		this.id_visit = id_visit;
		this.test_type = test_type;
		this.result = result;
		this.units = units;
		this.normal_range = normal_range;
		this.date_conducted = date_conducted;
	}


	@Override
	public String toString() {
		return "LabResultsDTO [id_lab_result=" + id_lab_result + ", id_visit=" + id_visit + ", test_type=" + test_type
				+ ", result=" + result + ", units=" + units + ", normal_range=" + normal_range + ", date_conducted="
				+ date_conducted + "]";
	}


	private LabResultsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
