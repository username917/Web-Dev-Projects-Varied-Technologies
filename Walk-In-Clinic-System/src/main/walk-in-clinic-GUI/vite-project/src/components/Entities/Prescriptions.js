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
		drug_name,
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
	
	// this function is gong to edit a prescription record 
	
	const editPrescription = (prescription) => {
		
		setEditingPrescripion(prescription);
		setFormData(prescription);
		setModalVisible(rue);
		
	 	console.log("Editing prescription with id: ", prescription);
	}
	
	// this function is going to create a prescription and add it to the database
	
	const createPrescription = () => {
		
		setFormData("");
		setModalVisible(true);
	}
	
	// this function is going to handle the deletion of a prescription
	
	const deletePrescription = async (id_prescription) => {
		
		console.log("Deleting prescriptio record with id: ", prescription);
		
		await apiService.deletePrescription(id_prescription);
		
		setPrescriptions([]);
		readPrescriptions();
	
	}
	
	// this function is going to handle the showing of hte modal
	
	const handleShowAdd = () => {
		
		setEditingPrescripion(null);
		
		setFormData({
			id_prescription: '',
			id_visit: '',
			drug_name,
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
									onClick={() => editingPrescription(record)}
								>
									Edit
								</Button>
								<Button
									variant="danger"
									siz="sm"
									onClick={() => deletePrescription(record.id_prescription)}								
								>
									Delete
								</Button>
							</td>
						</tr>
					))}
				</tbody>
			</Table>
			
			<Modal show={modalVisible} onhide={}>
				<Modal.Header>
					<Modal.Title></Modal.Title>
				</Modal.Header>
				<Form>
					<Modal.Body>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control></Form.Control>
						</Form.Group>
					</Modal.Body>
				</Form>
			</Modal>
		</>
	)
}

export default Prescriptions;