/**
 * this component is going to focus on managing the Doctor table of the project
 */
import React, { useState, useEffect } from 'react';
import apiService from "../../services/apiService";
import { Table, Button, Modal, Form } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';

const Doctor = () => {
	
	const [editingDoctor, setEditingDoctor] = useState("");
	const [doctors, setDoctors] = useState([]);
	const [modalVisible, setModalVisible] = useState(false);
	
	const [formData, setFormData] = useState({
		idDoctor: null,
		first_name: '',
		last_name: '',
		specialty: '',
		availability: '',
		contact_info: '',
		education: ''
	})
	
	useEffect(() => {
		
		recallDoctorList();
		
	}, []);
	
	const recallDoctorList = async () => {
		
		try {
			
			const respDoctorList = await apiService.getDoctorList();
			
			console.log("The response for doctor list is: ", respDoctorList);
			
			if (respDoctorList){
						
				const respDoctors = respDoctorList.data;
				setDoctors(respDoctors);
			}
			
			console.log("The docor list is: ", doctors);
			
		} catch (error) {
			
			console.log("The doctor list function is not built yet, and the error is; ", error);
		}
		
	};
	
	// this function is going to handle the editing of doctor data.
	
	const editDoctor = (doctor) => {
		
		setEditingDoctor(doctor);
		setFormData(doctor);
		setModalVisible(true);
		
		console.log("Editing doctor with id: ", doctor);
		
		
	}
	
	// this function is going to handle the deletion of a doctor object.
	
	const deleteDoctor = async (doctorid) => {
		
		console.log("Deleting doctor with id: ", doctorid);
		
		await apiService.deleteDoctor(doctorid);
		recallDoctorList();
		
		// I am making a change to my project.
		
	}
	
	// this functions handles the showing of the modal for the modal (?)
	
	const handleShowAdd = () => {
		
		setEditingDoctor(null);
		setFormData({
			idDoctor: '',
			first_name: '',
			last_name: '',
			specialty: '',
			avaialbility: '',
			contact_info: '',
			education: ''
		})
	}
	
	// this function hanldles both the editing and addition functions for the Doctor component.
	
	const handleSubmit = async (e) => {
		
		e.preventDefault();
		
		if (editingDoctor) {
			await apiService.editDoctor(formData);
		} else {
			await apiService.createDoctor(formData);
		}
		
		setModalVisible(false);
		recallDoctorList();
	}
	
	// this function is going ot handle changes in the doctor object
	
	const handleChange = (e) => {
		
		setFormData(prev => ({
			
			...prev,
			[e.target.name]: e.target.value
		}));
		
	};
	
	return (
		
		<div>
			<h1>Doctors' Table</h1>
			<br/>
			<Button variant='primary' onClick={handleShowAdd}>Add New Doctor</Button>
			<Table striped bordered hover>
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Specialty</th>
					<th>Availability</th>
					<th>Contact Info</th>
					<th>Education</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
			
				{doctors.map(doctor => (
					<tr key={doctor.id_doctor}>
						<td>{doctor.first_name}</td>
						<td>{doctor.last_name}</td>
						<td>{doctor.specialty}</td>
						<td>{doctor.availability}</td>
						<td>{doctor.contactInfo}</td>
						<td>{doctor.education}</td>
						<td>
							<Button 
								variant='warning'
								onClick={() => editDoctor(doctor)} // pass whole doctor object for editing
							>Modify</Button>
	
							<Button 
								variant='danger'
								onClick={() => deleteDoctor(doctor.id_doctor)}
							>Delete</Button>
						</td>
					</tr>
				))}
			</tbody>	
			</Table>
			
			<Modal show={modalVisible} onHide={() => setModalVisible(false)}>
				<Modal.Header closeButton>
					<Modal.Title>{editingDoctor ? 'Edit Doctor' : 'Add Doctor'}</Modal.Title>
				</Modal.Header>
				<Form onSubmit={handleSubmit}>
					<Modal.Body>
						<Form.Group>
							<Form.Label>First Name</Form.Label>
							<Form.Control name="first_name" value={formData.first_name} onChange={handleChange} required/>
						</Form.Group>
						<Form.Group>
							<Form.Label>Last Name</Form.Label>
							<Form.Control name="last_name" value={formData.last_name} onChange={handleChange} required/>
						</Form.Group>
							<Form.Label>Specialty</Form.Label>
							<Form.Control name="specialty" value={formData.specialty} onChange={handleChange} required/>
						<Form.Group>
							<Form.Label>Availability</Form.Label>
							<Form.Control name="availability" value={formData.availability} onChange={handleChange} required/>
						</Form.Group>
						<Form.Group>
							<Form.Label>Contact Information</Form.Label>
							<Form.Control name="contact-info" value={formData.contactInfo} onChange={handleChange} required/>				
						<Form.Group>
						</Form.Group>
							<Form.Label>Education</Form.Label>
							<Form.Control name="education" value={formData.education} onChange={handleChange} required/>
						</Form.Group>					
					</Modal.Body>
					<Modal.Footer>
						<Button variant="secondary" onClick={() => setModalVisible(false)}>Cancel</Button>
						<Button variant="primary" type="submit">{editingDoctor ? 'Update' : 'Add'}</Button>
					</Modal.Footer>
				</Form>		
				
			</Modal>
		
		</div>
	)
}

export default Doctor;