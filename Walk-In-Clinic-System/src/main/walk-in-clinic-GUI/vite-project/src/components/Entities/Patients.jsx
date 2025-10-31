/**
 * this component is going to contain all details regarding the Patient(s) object and collections in the rpoject
 */

import React, { useState, useEffect } from 'react';
import apiService from "../../services/apiService";
import { Table, Button, Modal, Form } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';

const Patients = () => {
	
	const [patients, setPatients] = useState([]);
	const [editingPatient, setEditingPatient] = useState([]);
	
	const [modalVisible, setModalVisible] = useState(false);
	
	const [formData, setFormData]= useState({
		
		id_patient: "",
		first_name: "",
		last_name: "",
		date_of_birth: "",
		gender: "",
		phone: "",
		email: "",
		address: "",
		emergency_contact_name: "",
		emergency_contact_phone: ""
	})
	
	useEffect(() => {
		readPatientRecords();
	}, []);
	
	// this function retrieves the most current patient list
	
	const readPatientRecords = async() => {
		
		try {
			
			const respPatients = await apiService.getPatientRecords();
			console.log("The response for patient records is: ", respPatients.data);
			
			if (Array.isArray(respPatients.data)) {
				
				const patients = respPatients.data;
				setPatients(patients);
			} else {
				
				console.log("No patinets have been found.");
				return;
			}
			
		} catch (error) {
			
			console.log("The error in retrieving patient records is ", error);
		}
	}
	
	// this function is going ot edit a patient record
	
	const editPatientRecord = (patientRecord) => {
		
		setEditingPatient(patientRecord);
		setFormData(patientRecord);
		setModalVisible(true);
		
		console.log("Editing patient record with id: ", patientRecord);
	}
	
	// this function is going to add a patient record to the database
	
	const createPatientRecord = () => {
		
		setFormData("");
		setModalVisible(true);
	}
	
	// this function is going to handle the deletion of a patient record
	
	const deletePatientRecord = async(id_patient) => {
		
		console.log("Deleting patient record with id: ", id_patient);
		
		await apiService.deletePatientRecord(id_patient);
		setPatients([]);
		readPatientRecords();
	}
	
	// this function is going to handle the showing of the modal
	
	const handleShowAdd = () => {
		
		setEditingPatient(null);
		
		setFormData({
			id_patient: "",
	
			first_name: "",
			last_name: "",
			date_of_birth: "",
			gender: "",
			phone: "",
			email: "",
			address: "",
			emergency_contact_name: "",
			emergency_contact_phone: ""
		})
		
		createPatientRecord();
	}
	
	// this function will handle the submisison of new or modified health records
	
	const handleSubmit = async (e) => {
		
		e.preventDefault();
		
		if (editingPatient) {
			
			await apiService.editPatientRecord(formData);
		} else {
			
			await apiService.createPatientRecord(formData);
		}
		
		setModalVisible(false);
		readPatientRecords();
	}
	
	// this function will handle the changing events in the frontned elements of the Patient component
	
	const handleChange = (e) => {
		
		setFormData(prev => ({
			
			...prev,
			[e.target.name]: e.target.value
		}))
	}
	
	return (
		<>
			<h3>Patient Records</h3>
			<Button variant="primary	" onClick={handleShowAdd}>Create New Patient Record</Button>
			<br/>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>Patient Record</th>
			
						<th>First Name</th>
						<th>Last Name</th>
						<th>Date of Birth</th>
						<th>Gender</th>
						<th>Phone</th>
						<th>Email</th>
						<th>Address</th>
						<th>Emergency Contact Name</th>
						<th>Emergency Contact Phone</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					{patients.map(patient => (
						<tr key={patient.id_patient}>
							<td>{patient.id_patient}</td>
						
							<td>{patient.first_name}</td>
							<td>{patient.last_name}</td>
							<td>{patient.date_of_birth}</td>
							<td>{patient.gender}</td>
							<td>{patient.phone}</td>
							<td>{patient.email}</td>
							<td>{patient.address}</td>
							<td>{patient.emergency_contact_name}</td>
							<td>{patient.emergency_contact_phone}</td>
							<td>
								<Button
									variant="warning"
									size="sm" className="ms-2"
									onClick={() => editPatientRecord(patient)}
								>
									Edit
								</Button>
								<Button
									variant="danger"
									size="sm"
									onClick={() => deletePatientRecord(patient.id_patient)}
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
					<Modal.Title>{editingPatient ? "Edit Patient Record" : "Create New Patient Record"}</Modal.Title>
				</Modal.Header>
				<Form onSubmit={handleSubmit}>
					<Modal.Body>
						
						<Form.Group>
							<Form.Label>First Name</Form.Label>
							<Form.Control name="first_name" value={formData.first_name} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Last Name</Form.Label>
							<Form.Control name="last_name" value={formData.last_name} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Date of Birth</Form.Label>
							<Form.Control name="date_of_birth" value={formData.date_of_birth} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Gender</Form.Label>
							<Form.Control name="gender" value={formData.gender} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Phone</Form.Label>
							<Form.Control name="phone" value={formData.phone} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Email</Form.Label>
							<Form.Control name="email" value={formData.email} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Address</Form.Label>
							<Form.Control name="address" value={formData.address} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Emergency Contact Name</Form.Label>
							<Form.Control name="emergency_contact_name" value={formData.emergency_contact_name} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Emergency Contact Phone</Form.Label>
							<Form.Control name="emergency_contact_phone" value={formData.emergency_contact_phone} onChange={handleChange} required></Form.Control>
						</Form.Group>
					</Modal.Body>
					<Modal.Footer>
						<Button variant="primary" onClick={() => setModalVisible(false)}>Cancel</Button>
						<Button variant="secondary" type="submit">{editingPatient ? "Update" : "Add"}</Button>
					</Modal.Footer>
					
				</Form>
				
			</Modal>
		</>
	)
}

export default Patients;