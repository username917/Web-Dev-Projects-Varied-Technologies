/**
 * this component is for managing appointments in the adinsitrative component
 */
import React, { useState, useEffect } from 'react';
import apiService from "../../services/apiService";
import { Table, Button, Modal, Form } from "react-bootstrap";

const Appointments = () => {
	
	const [appointments, setAppointments] = useState([]);
	
	const [editingAppt, setEditingAppt] = useState([]);
	const [modalVisible, setModalVisible] = useState(false);
	
	const [formData, setFormData] = useState({
		id_appointment: null,  
		id_patient: "",
		id_doctor: "",
		date_appointment: "",
		status: "",
		notes: ""
	})
	
	useEffect(() => {
		
		readAppointments();
	
	}, []);
	
	// this function retrieves the most current appointment list from the database.
	
	const readAppointments = async () => {

		try {
			
			const respAppointments = await apiService.getAppointments();
			console.log("The response from the API service for appointments is: ", respAppointments.data);
					
			if (Array.isArray(respAppointments.data)) {
				
				const appointments = respAppointments.data;
				setAppointments(appointments);
				
			} else {
				
				console.log("No appointments have been found.");
				return;
			}
			
		} catch (error) {
			
			console.log("The error in retrieving appointments is: ", error);
		}
		
	}
	
	// this function is going to edit the appointment list 
	
	const editAppointment = (appointment) => {
		
		setEditingAppt(appointment)
		setFormData(appointment);
		setModalVisible(true);
		
		console.log("Editing appointment with id: ", appointment);
		
	}
	
	// this function is going to add an appointment to the database
	
	const createAppointment = () => {
		
		setFormData("");
		setModalVisible(true);
	}
	
	// this function is going to handle the deletion of an appointment object
	
	const deleteAppointment = async(appointment) => {
		
		console.log("Deleting appintment with id: ", appointment.id_appointment);
		
		await apiService.deleteAppointment(appointment.id_appointment);
		readAppointments();
	}
	
	// this function is going to handle the showing of the modal
	
	// somehow need to integrate the display of text, not ids, for user context.
	
	const handleShowAdd = () => {
		
		setEditingAppt(null);
		
		setFormData({
			id_appointment: '',
			id_patient: "",
			id_doctor: "",
			date_appointment: "",
			status: "",
			notes: ""
		});
		
		createAppointment();
	}
	
	// this function is going to handle the submission of new or modifying data for an appointmetn
	
	const handleSubmit = async (e) => {
		
		e.preventDefault();
		
		if (editingAppt) {
			
			await apiService.editAppointment(formData);
			
		} else {
			
			await apiService.createAppointment(formData);
		}
		
		setModalVisible(false);
		readAppointments();
	}
	
	// this function will handle changing events in the frontend elements of the APpointment component
	
	const handleChange = (e) => {
		
		setFormData(prev => ({
			
			...prev,
			[e.target.name]: e.target.value
		}));
		
	};
	
	// consiser changing breaks with proper style={{}} tags once done initial implementation of all GUI elements
	return (
		
		<>
			<h3>Appointments</h3>
			<Button variant="primary" onClick={handleShowAdd}>Create New Appointment</Button>
			<br/>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>Patient</th>
						<th>Doctor</th>
						<th>Date</th>
						<th>Status</th>
						<th>Notes</th>
					</tr>
				</thead>
				<tbody>
					{appointments.map(app => (
						<tr key={app.id_appointment}>
							<td>{app.id_patient}</td>
							<td>{app.id_doctor}</td>
							<td>{app.date_appointment}</td>
							<td>{app.status}</td>
							<td>{app.notes}</td>
							<td>
								<Button 
									variant="warning" 
									size='sm' className='me-2'
									onClick={() => editAppointment(app)}
								>
									Edit
								</Button>
								<Button 
									variant="danger" 
									size='sm'
									onClick={() => deleteAppointment(app)}
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
					<Modal.Title>{editingAppt ? 'Edit Current Appointment' : 'Create New Appointment'}</Modal.Title>
				</Modal.Header>
				<Form onSubmit={handleSubmit}>
					<Modal.Body>
						<Form.Group>
							<Form.Label>Patient ID</Form.Label>
							<Form.Control name="id_patient" value={formData.id_patient} onChange={handleChange} required></Form.Control>
						</Form.Group>
							<Form.Label>Doctor ID</Form.Label>
							<Form.Control name="id_doctor" value={formData.id_doctor} onChange={handleChange} required></Form.Control>
						<Form.Group>
							<Form.Label>Appointment Date/Time</Form.Label>
							<Form.Control name="date_appointment" value={formData.date_appointment} onChange={handleChange} required></Form.Control>
						</Form.Group>
							<Form.Label>Status</Form.Label>
							<Form.Control name="status" value={formData.status} onChange={handleChange} required></Form.Control>
						<Form.Group>
							<Form.Label>Notes</Form.Label>
							<Form.Control name="notes" value={formData.notes} onChange={handleChange} required></Form.Control>
						</Form.Group>
					</Modal.Body>
					<Modal.Footer>
						<Button variant="primary" onClick={() => setModalVisible(false)}>Cancel</Button>
						<Button variant="secondary" type="submit">{editingAppt ? 'Update' : 'Add'}</Button>
					</Modal.Footer>
				</Form>
			</Modal>
		</>
		
	)
	
}

export default Appointments;