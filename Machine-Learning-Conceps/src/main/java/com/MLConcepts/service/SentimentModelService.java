package com.MLConcepts.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

@Service
public class SentimentModelService {
	
	private NaiveBayes classifier;
	private Instances trainingStructure;

	@PostConstruct
	public void init() throws Exception {
		
		this.trainingStructure = createDataStructure();
		Instances trainingData = new Instances(trainingStructure);
		
		loadTrainingDataFromCSV(trainingData);
	
		this.classifier = new NaiveBayes();
		this.classifier.buildClassifier(trainingData);
		
		System.out.println("Sentiment model trained with: " + trainingData.numInstances() + " examples.");
	}

	private void loadTrainingDataFromCSV(Instances trainingData) throws IOException {
		
		ClassPathResource resource = new ClassPathResource("/data.csv");
		

        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
	
        	String line;
        	boolean isHeader = true;
        	
        	while ((line = reader.readLine()) != null) {
        		
        		if (isHeader) {
        			isHeader = false;
        			continue;
        		}
        		
        		line = line.trim();
        		
        		if (line.isEmpty()) {
        			
        			continue;
        		}
        		
        		String[] parts = line.split(",", 2);
        		
        		if (parts.length < 2) {
        			
        			System.out.println("Skipping invalid csv row: " + line);
        			continue;
        		}
        		
        		String text = parts[0].trim();
        		String label = parts[1].trim();
        		
        		addTrainingExample(trainingData, text, label);
        	}
			
		} 
		
	}

	private void addTrainingExample(Instances data, String text, String label) {
		
		Instance instance = new DenseInstance(data.numAttributes());
		instance.setDataset(data);
		
		double[] features = extractFeatures(text);
		
		instance.setValue(0, features[0]);
		instance.setValue(1, features[1]);
		instance.setValue(2, features[2]);
		instance.setValue(3, features[3]);
		instance.setValue(4, features[4]);
		instance.setValue(5,  label);
		
		data.add(instance);

	}

	private double[] extractFeatures(String text) {
		
		String normalized = text.toLowerCase();
		
		List<String> positiveWords = Arrays.asList("love", "great", "amazing", "fantastic", "good", "happy", "wonderful", "excellent");
		List<String> negativeWords = Arrays.asList("hate", "terrible", "awful", "bad", "disappointing", "unhappy", "horrible", "worst");
		List<String> neutralWords = Arrays.asList("okay", "fine", "average", "normal", "alright", "standard");
		
		double containsPositiveWord = positiveWords.stream().anyMatch(normalized::contains) ? 1.0 : 0.0;
		double containsNegativeWord = negativeWords.stream().anyMatch(normalized::contains) ? 1.0 : 0.0;
		double containsNeutralWord = neutralWords.stream().anyMatch(normalized::contains) ? 1.0 : 0.0;
		
		double exclamationCount = text.chars().filter(ch -> ch == '!').count();
		double textLength = text.length();
		
		return new double[] {
				containsPositiveWord, containsNegativeWord, containsNeutralWord, exclamationCount, textLength};
	}

	private Instances createDataStructure() {
			
		ArrayList<Attribute> attributes = new ArrayList<>();
		
		attributes.add(new Attribute("containsPositiveWord"));
		attributes.add(new Attribute("containsNegativeWord"));
		attributes.add(new Attribute("containsNeutralWord"));
		attributes.add(new Attribute("exclamationCount"));
		attributes.add(new Attribute("textLength"));
		
		List<String> classValues = Arrays.asList("positive", "negative", "neutral");
		
		attributes.add(new Attribute("sentiment", classValues));
		
		Instances dataset = new Instances("SentimentTrainingData", attributes, 0);
		dataset.setClassIndex(dataset.numAttributes() - 1);
		
		return dataset;
	}
	
	public PredictionResult predict(String text) throws Exception {
		
		if (text == null || text.isBlank()) {
			
			throw new IllegalArgumentException("Text must not be blank.");
		}
		
		Instance instance = createPredictionInstance(text);
		
		double predictedClassIndex = classifier.classifyInstance(instance);
		double[] distribution = classifier.distributionForInstance(instance);
		
		String predictedLabel = trainingStructure.classAttribute().value((int) predictedClassIndex);
		double confidence = distribution[(int) predictedClassIndex];
		
		return new PredictionResult(predictedLabel, confidence);
	}
	
	private Instance createPredictionInstance(String text) {
		
		Instance instance = new DenseInstance(trainingStructure.numAttributes());
		instance.setDataset(trainingStructure);
		
		double[] features = extractFeatures(text);
		
		instance.setValue(0, features[0]);
		instance.setValue(1, features[1]);
		instance.setValue(2, features[2]);
		instance.setValue(3, features[3]);
		instance.setValue(4, features[4]);
		
		return instance;
	}

	public static class PredictionResult {
		
		private final String label;
		private final double confidence;
		
		public String getLabel() {
			return label;
		}
		public double getConfidence() {
			return confidence;
		}
		private PredictionResult(String label, double confidence) {
			super();
			this.label = label;
			this.confidence = confidence;
		}
	}
	
	public DebugResult predictWithDebug(String text) throws Exception {

	    Instance instance = createPredictionInstance(text);

	    double predictedClassIndex = classifier.classifyInstance(instance);
	    double[] distribution = classifier.distributionForInstance(instance);

	    String predictedLabel = trainingStructure.classAttribute().value((int) predictedClassIndex);
	    double confidence = distribution[(int) predictedClassIndex];

	    // Feature extraction again for visibility
	    double[] featuresArray = extractFeatures(text);

	    Map<String, Double> features = Map.of(
	            "containsPositiveWord", featuresArray[0],
	            "containsNegativeWord", featuresArray[1],
	            "containsNeutralWord", featuresArray[2],
	            "exclamationCount", featuresArray[3],
	            "textLength", featuresArray[4]
	    );

	    Map<String, Double> probabilities = Map.of(
	            "positive", distribution[0],
	            "negative", distribution[1],
	            "neutral", distribution[2]
	    );

	    return new DebugResult(predictedLabel, confidence, probabilities, features);
	}
	
}
