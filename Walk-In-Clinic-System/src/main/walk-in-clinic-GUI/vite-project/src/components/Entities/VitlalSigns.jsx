/**
 * this component will keep track of vital signs of each patient, such as:
 * 1. Sufficient caffeine
 * 2. Enough cigarettes
 * 3. Gum chewed in 24 hours
 * 4. Number of sleepless nights
 * 5. Number of shots per week to deal with life's bs.
 * 6. Curse words per hour
 * 7. 6/6 Measuerement for heatlhy or unhealthy
 */

import React, { useState, useEffect } from 'react';
import apiService from "../../services/apiService";
import { Table, Button, Modal, Form } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';


const VitalSigns = () => {
	
	const [vitalSigns, setVitalSigns] = useState([]);
	const [editingVitalSigns, setEditingVitalSigns] = useState([]);
	
	const [modalVisible, setModalVisible] = useState(false);
	
	const [formData, setFormData] = useState({
		id_vitals: '',
		id_visit: '',
		temperature: '',
		blood_pressure: '',
		heart_rate: '',
		respiratory_rate: '',
		weight: '',
		height: ''
	})
	
	useEffect (() => {
		readVitalSigns();
	}, [])
	
	const readVitalSigns = async () => {
		
		try {
			
			const readVitalSigns = await apiService.getVitalSigns();
			console.log("The response from the vital signs request is: ", readVitalSigns.data);
			
			if (Array.isArray(readVitalSigns.data)) {
				
				const vitalSigns = readVitalSigns.data;
				setVitalSigns(vitalSigns);
			} else {
				
				console.log("No vital signs data has been found");
				
				return;
			}
			
			
		} catch (error) {
			
			console.log("The error in reading vital signs is: ", error);
		}
		
		
	}
	
}

export default VitalSigns;