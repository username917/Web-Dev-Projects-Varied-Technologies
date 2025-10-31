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
	
	const deleteVitalSignsRecord = async (id_vitals) => {
		
		console.log("Deleting a vital signs record with: ", id_vitals);
	
		await apiService.deleteVitalSignsRecord(id_vitals);
		
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
				
					{vitalSigns.map(record => (
						<tr key={record.id_vitals}>
							<td>{record.id_vitals}</td>
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
									variant="danger"
									size="sm"
									onClick={() => deleteVitalSignsRecord(record.id_vitals)}
								>
									Delete
								</Button>
							</td>
						</tr>
					))}
				</tbody>
			</Table>
			
			<Modal show={modalVisible} onHide={() => setModalVisible(false)}>
				<Modal.Header closeButton>
					<Modal.Title>{editingVitalSigns ? "Edit Vital Signs Record" : "Create New Vital Signs Record"}</Modal.Title>
				</Modal.Header>
				<Form onSubmit={handleSubmit}>
					<Modal.Body>
						<Form.Group>
							<Form.Label>Temperature</Form.Label>
							<Form.Control name="temperature" value={formData.temperature} onChange={handleChange} required></Form.Control>
						</Form.Group>
							<Form.Label>Blood Pressure</Form.Label>
							<Form.Control name="blood_pressure" value={formData.blood_pressure} onChange={handleChange} required></Form.Control>
						<Form.Group>
							<Form.Label>Heart Rate</Form.Label>
							<Form.Control name="heart_rate" value={formData.heart_rate} onChange={handleChange} required></Form.Control>
						</Form.Group>
							<Form.Label>Respiratory Rate</Form.Label>
							<Form.Control name="respiratory_rate" value={formData.respiratory_rate} onChange={handleChange} required></Form.Control>
						<Form.Group>
							<Form.Label>Weight</Form.Label>
							<Form.Control name="weight" value={formData.weight} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Height</Form.Label>
							<Form.Control name="height" value={formData.height} onChange={handleChange} required></Form.Control>
						</Form.Group>
					
					</Modal.Body>
					<Modal.Footer>
						<Button variant="primary" onClick={() => setModalVisible(false)}>Cancel</Button>
						<Button variant="secondary" type="submit">{editingVitalSigns ? "Update" : "Add"}</Button>
					</Modal.Footer>
				</Form>
			</Modal>
		</>
	)
}

export default VitalSigns;