package com.MLConcepts.dto;

public class PredictionResponse {
	
	private String label;
	private double confidence;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getConfidence() {
		return confidence;
	}
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	public PredictionResponse(String label, double confidence) {
		super();
		this.label = label;
		this.confidence = confidence;
	}
	private PredictionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}
