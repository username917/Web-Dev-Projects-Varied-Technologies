/**
 * this component is for managing appointments in the adinsitrative component
 */
import React, { useState, useEffect } from 'react';
import apiService from "../../services/apiService";
import { Table, Button } from "react-bootstrap";

const Appointments = () => {
	
	const [appointments, setAppointments] = useState([]);
	
	useEffect(() => {
		
		readAppointments();
	
	}, []);
	
	const readAppointments = async () => {
		
		const respAppointments = await apiService.getAppointments();
		console.log("The response from the API service is: ", respAppointments.data);
		
		if (respAppointments) {
			
			const appointments = respAppointments.data;
			setAppointments(appointments);
		}
		
		
	}
	
	return (
		
		<>
			<h3>Appointments</h3>
			<Button variant="priamry" className="mb-3">Add New Record</Button>
			
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
						<tr key={app.idAppointment}>
							<td>{app.idPatient}</td>
							<td>{app.idDoctor}</td>
							<td>{app.dateAppointment}</td>
							<td>{app.status}</td>
							<td>{app.notes}</td>
							<td>
								<Button variant="warning" size='sm' className='me-2'>Edit</Button>
								<Button variant="danger" size='sm'>Delete</Button>
							</td>
						</tr>
					))}
				</tbody>
			</Table>
		</>
		
	)
	
	
}

export default Appointments;