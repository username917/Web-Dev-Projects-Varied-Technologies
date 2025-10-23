/**
 * this component will contian all aspects about the prescriptions in the project
 */

import React, { useState, useEffect } from 'react';
import apiService from "../../services/apiService";
import { Table, Button, Modal, Form } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';

const Prescriptions = () => {
	
	const [prescriptions, setPrescriptions] = useState([]);
	const [editingPrescription, setEditingPrescripion] = useState([]);
	
	const [modalVisible, setModalVisible] = useState(false);
	
	const [formData, setFormData] = useState({
		id_prescription: '',
		id_visit: '',
		drug_name: '',
		dosage: '',
		frequency: '',
		duration: '',
		notes: ''
	})
	
	useEffect(() => {
		readPrescriptions();
	}, []);
	
	const readPrescriptions = async () => {
		
		try {
			
			const respPrescriptions = await apiService.getPrescriptoins();
			console.log("The reponse for prescriptions is: ", readPrescriptions.data);
			
			if (Array.isArray(respPrescriptions.data)) {
				
				const prescriptions = readPrescriptions.data;
				setPrescriptions(prescriptions);
			} else {
				
				console.log("No prescriptions have been found");
				return
			}
			
		} catch (error) {
			
			console.log("The error in getting prescriptions is: ", error);
		}
	}
	
	// this function is going to edit a prescription record 
	
	const editPrescription = (prescription) => {
		
		setEditingPrescripion(prescription);
		setFormData(prescription);
		setModalVisible(true);
		
	 	console.log("Editing prescription with id: ", prescription);
	}
	
	// this function is going to create a prescription and add it to the database
	
	const createPrescription = () => {
		
		setFormData("");
		setModalVisible(true);
	}
	
	// this function is going to handle the deletion of a prescription
	
	const deletePrescription = async (id_prescription) => {
		
		console.log("Deleting prescription record with id: ", id_prescription);
		
		await apiService.deletePrescription(id_prescription);
		
		setPrescriptions([]);
		readPrescriptions();
	
	}
	
	// this function is going to handle the showing of the modal
	
	const handleShowAdd = () => {
		
		setEditingPrescripion(null);
		
		setFormData({
			id_prescription: '',
			id_visit: '',
			drug_name: '',
			dosage: '',
			frequency: '',
			duration: '',
			notes: ''
		})
		
		createPrescription();
	}
	
	// thsi function is going to handle the submission of a new or modified prescription record
	
	const handleSubmit = async (e) => {
		
		e.preventDefault();
		
		if (editingPrescription) {
			
			await apiService.editPrescription(formData);
			
		} else {
			
			await apiService.createPrescription(formData);
		}
		
		setModalVisible(false);
		readPrescriptions();
	}
	
	// this function wll handle chanigng events in the frontend element of the Prescriptio module
	
	const handleChange = (e) => {
		
		setFormData(prev => ({
			
			...prev,
			[e.target.name]: e.target.value
		}));
	}
	
	/*
	const [formData, setFormData] = useState({
			id_prescription: '',
			id_visit: '',
			drug_name,
			dosage: '',
			frequency: '',
			duration: '',
			notes: ''
		})
		*/
	return (
		
		<>
			<h3>Prescriptions</h3>
			<Button variant="primary" onClick={handleShowAdd}>Create New Prescription</Button>
			<br/>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>Prescription</th>
						<th>Visit</th>
						<th>Drug Name</th>
						<th>Dosage</th>
						<th>Frequency</th>
						<th>Duration</th>
						<th>Notes</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					{prescriptions.map(record => (
						<tr key={record.id_prescription}>
							<td>{record.id_visit}</td>
							<td>{record.drug_name}</td>
							<td>{record.dosage}</td>
							<td>{record.frequency}</td>
							<td>{record.duration}</td>
							<td>{record.notes}</td>
							<td>
								<Button
									variant="warning"
									size="sm" className="ms-2"
									onClick={() => editPrescription(record)}
								>
									Edit
								</Button>
								<Button
									variant="danger"
									size="sm"
									onClick={() => deletePrescription(record.id_prescription)}								
								>
									Delete
								</Button>
							</td>
						</tr>
					))}
				</tbody>
			</Table>
			
			<Modal show={modalVisible} onhide={() => setModalVisible(false)}>
				<Modal.Header closeButton>
					<Modal.Title>{editingPrescription ? "Edit Prescription" : 'Create New Prescription'}</Modal.Title>
				</Modal.Header>
				<Form onSubmit={handleSubmit}>
					<Modal.Body>
						<Form.Group>
							<Form.Label>Drug Name</Form.Label>
							<Form.Control name="drug_name" value={formData.drug_name} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Dosage</Form.Label>
							<Form.Control name="dosage" value={formData.dosage} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Frequency</Form.Label>
							<Form.Control name="frequency" value={formData.frequency} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Duration</Form.Label>
							<Form.Control name="duration" value={formData.duration} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Notes</Form.Label>
							<Form.Control name="notes" value={formData.notes} onChange={handleChange} required></Form.Control>
						</Form.Group>
					</Modal.Body>
					<Modal.Footer>
						<Button variant="primary" onClick={() => setModalVisible(false)}>Cancel</Button>
						<Button variant="secondary" type="submit">{editingPrescription ? 'Update' : 'Add'}</Button>
					</Modal.Footer>
				</Form>
			</Modal>
		</>
	)
}

export default Prescriptions;