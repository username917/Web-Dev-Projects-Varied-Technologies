package com.MLConcepts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MLConcepts.dto.PredictionRequest;
import com.MLConcepts.dto.PredictionResponse;
import com.MLConcepts.service.SentimentModelService;

@RestController
@RequestMapping("/api/sentiment")
public class SentimentController {
	
	private final SentimentModelService sentimentModelService;
	
	public SentimentController(SentimentModelService sentimentModelService) {
		this.sentimentModelService = sentimentModelService;
	}
	
	@PostMapping("/predict")
	public ResponseEntity<PredictionResponse> predict(@RequestBody PredictionRequest request) throws Exception {
		
		SentimentModelService.PredictionResult result = sentimentModelService.predict(request.getText());
		
		PredictionResponse response = new PredictionResponse(result.getLabel(), result.getConfidence());
		
		return ResponseEntity.ok(response);
	}

}
