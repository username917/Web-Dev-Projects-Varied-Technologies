package com.MLConcepts.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import com.MLConcepts.MachineLearningConcepsApplication;
import jakarta.annotation.PostConstruct;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

@Service
public class SentimentModelServiceHARDCODED {
	
	private NaiveBayes classifier;
	private Instances trainingStructure;

	@PostConstruct
	public void init() throws Exception {
		
		this.trainingStructure = createDataStructure();
		Instances trainingData = new Instances(trainingStructure);
		
		addTrainingExample(trainingData, "I love this product", "positive");		
		addTrainingExample(trainingData, "This is amazing", "positive");
		addTrainingExample(trainingData, "Fantastic and wonderful", "positive");
		addTrainingExample(trainingData, "I am very happy with this", "positive");
		
		addTrainingExample(trainingData, "I hate this", "negative");
		addTrainingExample(trainingData, "This is terrible", "negative");
		addTrainingExample(trainingData, "Awful and disappointing", "negative");
		addTrainingExample(trainingData, "I am unhappy with this", "negative");
	
		this.classifier = new NaiveBayes();
		this.classifier.buildClassifier(trainingData);
		
		System.out.println("Sentiment model trained with: " + trainingData.numInstances() + " examples.");
	}

	private void addTrainingExample(Instances data, String text, String label) {
		
		Instance instance = new DenseInstance(data.numAttributes());
		instance.setDataset(data);
		
		double[] features = extractFeatures(text);
		
		instance.setValue(0,  features[0]);
		instance.setValue(1,  features[1]);
		instance.setValue(2,  features[2]);
		instance.setValue(3,  features[3]);
		instance.setValue(4, label);
		
		data.add(instance);

	}

	private double[] extractFeatures(String text) {
		
		String normalized = text.toLowerCase();
		
		List<String> positiveWords = Arrays.asList("love", "great", "amazing", "fantastic", "good", "happy", "wonderful");
		List<String> negativeWords = Arrays.asList("hate", "terrible", "awful", "bad", "disappointing", "unhappy");
		
		double containsPositiveWord = positiveWords.stream().anyMatch(normalized::contains) ? 1.0 : 0.0;
		double containsNegativeWord = negativeWords.stream().anyMatch(normalized::contains) ? 1.0 : 0.0;
		double exclamationCount = text.chars().filter(ch -> ch == '!').count();
		double textLength = text.length();
		
		return new double[] {
				containsPositiveWord, containsNegativeWord, exclamationCount, textLength};
	}

	private Instances createDataStructure() {
			
		ArrayList<Attribute> attributes = new ArrayList<>();
		
		attributes.add(new Attribute("containsPositiveWord"));
		attributes.add(new Attribute("containsNegativeWord"));
		attributes.add(new Attribute("exclamationCount"));
		attributes.add(new Attribute("textLength"));
		
		List<String> classValues = Arrays.asList("positive", "negative");
		
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
	
}
