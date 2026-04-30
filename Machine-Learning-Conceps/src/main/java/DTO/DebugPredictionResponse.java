package DTO;

import java.util.Map;

public class DebugPredictionResponse {

    private String label;
    private double confidence;
    private Map<String, Double> probabilities;
    private Map<String, Double> features;

    public DebugPredictionResponse(String label,
                                   double confidence,
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
