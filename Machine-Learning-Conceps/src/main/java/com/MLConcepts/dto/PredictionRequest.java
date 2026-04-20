package com.MLConcepts.dto;

public class PredictionRequest {

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private PredictionRequest(String text) {
		super();
		this.text = text;
	}

	private PredictionRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
}
