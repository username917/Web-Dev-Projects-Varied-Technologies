package com.MLConcepts.service;

import java.util.Map;

public class DebugResult {
	
	 private final String label;
	    private final double confidence;
	    private final Map<String, Double> probabilities;
	    private final Map<String, Double> features;

	    public DebugResult(String label, double confidence,
	                       Map<String, Double> probabilities,
	                       Map<String, Double> features) {
	        this.label = label;
	        this.confidence = confidence;
	        this.probabilities = probabilities;
	        this.features = features;
	    }

	    public String getLabel() { return label; }
	    public double getConfidence() { return confidence; }
	    public Map<String, Double> getProbabilities() { return probabilities; }
	    public Map<String, Double> getFeatures() { return features; }	

}
