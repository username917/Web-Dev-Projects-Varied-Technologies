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
	
	// this function is going to edit a vital signs record

	const editVitalSignsRecord = (vitalSignsRecord) => {
		
		setEditingVitalSigns(vitalSignsRecord);
		setFormData(vitalSignsRecord);
		setModalVisible(true);
		
		console.log("Editing vital signs record with id: ", vitalSignsRecord);
	}
	
	// this function is going to create a vital signs record
	
	const createVitalSignsRecord = () => {
		
		setFormData("");
		setModalVisible(true);
	}
	
	// this function is going to handle the deleteion of a vital signs record
	
	const deleteVitalSignsRecord = async (id_vital_signs_record) => {
		
		console.log("Deleting a vital signs record with: ", id_vital_signs_record);
	
		await apiService.deleteVitalSignsRecord(id_vital_signs_record);
		
		setVitalSigns([]);
		readVitalSigns();
	}
	
	// this function is going to handle the showing of the modal
	
	const handleShowAdd = () => {
		
		setEditingVitalSigns(null);
		
		setFormData({
			id_vitals: '',
			id_visit: '',
			temperature: '',
			blood_pressure: '',
			heart_rate: '',
			respiratory_rate: '',
			weight: '',
			height: ''
		})
		
		createVitalSignsRecord();
	}
	
	// this function is going to handle the submission of a new or modified vital signs record
	
	const handleSubmit = async (e) => {
		
		e.preventDefault();
		
		if (editingVitalSigns) {
			
			await apiService.editVitalSignsRecord(formData);
		
		} else {
			
			await apiService.createVitalSignsRecord(formData);
		}
		
		setModalVisible(false);
		readVitalSigns();
	}
	
	// this function is going to handle changing events in the frontend element of the Vital Signs module
	
	const handleChange = (e) => {
		
		setFormData(prev => ({
			
			...prev,
			[e.target.name]: e.target.value
		}))
	}
	
	/*
		id_vitals: '',
		id_visit: '',
		temperature: '',
		blood_pressure: '',
		heart_rate: '',
		respiratory_rate: '',
		weight: '',
		height: ''
	*/
	
	return (
	
		<>
			<h3>Vital Signs</h3>
			<Button variant="primary" onClick={handleShowAdd}>Create New Vital Record</Button>
			<br/>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>Vitals ID</th>
						<th>Visit ID</th>
						<th>Temperature</th>
						<th>Blood Pressure</th>
						<th>Heart Rate</th>
						<th>Respiratory Rate</th>
						<th>Weight</th>
						<th>Height</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
					{vitalSigns.map(record => (
						<tr key={record.id_vital_sign}>
							<td>{record.id_visit}</td>
							<td>{record.temperature}</td>
							<td>{record.blood_pressure}</td>
							<td>{record.heart_rate}</td>
							<td>{record.respiratory_rate}</td>
							<td>{record.weight}</td>
							<td>{record.height}</td>
							<td>
								<Button
									variant="warning"
									size="sm" className="ms-2"
									onClick={() => editVitalSignsRecord(record)}
								>
									Edit
								</Button>
								<Button
									varinat="danger"
									size="sm"
									onClick={() => deleteVitalSignsRecord(record)}
								>
									Delete
								</Button>
							</td>
						</tr>
					))}
			</Table>
			
			<Modal>
				<Modal.Header>
					<Modal.Title></Modal.Title>
				</Modal.Header>
				<Form>
					<Modal.Body>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control></Form.Control>
						</Form.Group>
							<Form.Label></Form.Label>
							<Form.Control></Form.Control>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control></Form.Control>
						</Form.Group>
							<Form.Label></Form.Label>
							<Form.Control></Form.Control>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control></Form.Control>
						</Form.Group>
					
					</Modal.Body>
					<Modal.Footer>
						<Button></Button>
						<Button></Button>
					</Modal.Footer>
				</Form>
			</Modal>
		</>
	)
}

export default VitalSigns;