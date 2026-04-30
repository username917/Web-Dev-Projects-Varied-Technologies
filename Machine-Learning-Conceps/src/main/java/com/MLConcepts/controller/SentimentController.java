package com.MLConcepts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MLConcepts.dto.PredictionRequest;
import com.MLConcepts.dto.PredictionResponse;
import com.MLConcepts.service.SentimentModelService;

import DTO.DebugPredictionResponse;

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
	
	@PostMapping("/predict-debug")
	public ResponseEntity<DebugPredictionResponse> predictDebug(
	        @RequestBody PredictionRequest request) throws Exception {

	    var result = sentimentModelService.predictWithDebug(request.getText());

	    return ResponseEntity.ok(
	            new DebugPredictionResponse(
	                    result.getLabel(),
	                    result.getConfidence(),
	                    result.getProbabilities(),
	                    result.getFeatures()
	            )
	    );
	}

}
