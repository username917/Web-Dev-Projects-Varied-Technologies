/**
 * this component is going to contain all details with respect to the Lab Results part of the project
*/

import React, { useState, useEffect } from 'react';
import apiService from "../../services/apiService";
import { Table, Button, Modal, Form } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';

const LabResults = () => {
	
	const [labResults, setLabResults] = useState([]);
	const [editingLaResult, setEditingLabResult] = useState([]);
	
	const [modalVisible, setModalVisible] = useState(false);
	
	const [formData, setFormData] = useState({
		id_lab_result: "",
		id_visit: "",
		test_type: "",
		result: "",
		units: "",
		normal_range: "",
		date_conducted: "",
	});
	
	useEffect(() => {0
		readLabResults();
	}, [])
	
	// this function retrieves  the most current list of lab results
	
	const readLabResults = async() => {
		
		try {
			
			const respLabResults = await apiService.getLabResults();
			console.log("The response from the API service for lab results is: ", respLabResults.data);
			
			if (Array.isArray(respLabResults.data)) {
				
				const labResults = respLabResults.data;
				setLabResults(labResults);
				
			} else {
				
				console.log("No lab results have been found");
				return;
			}
			
		} catch (error) {
			
			console.log("The error in retrieving lab results is: ", error);
			
		}
		
	}
	
	// this function is going to edit a lab record
	
	const editLabRecord = (labRecord) => {
		
		setEditingLabResult(labRecord);
		setFormData(labRecord);
		setModalVisible(true);
		
		console.log('Editing lab result with id: ', labRecord);
		
	}
	
	// this function is going to add a lab record to the database
	
	const createLabRecord = () => {
		
		setFormData("");
		setModalVisible(true);
	}
	
	// this function is going to handle the deletion 
	
	const deleteLabResult = async(id_lab_result) => {
		
		console.log("Deleting lab result wiht id: ", id_lab_result);
		
		await apiService.deleteLabResult(id_lab_result);
		setLabResults([]);
		await readLabResults();
	}
	
	// this function is going to handle the showing of the modal
	
	const handleShowAdd = () => {
		
		setEditingLabResult(null);
		
		setFormData({
			id_lab_result: "",
			id_visit: "",
			test_type: "",
			result: "",
			units: "",
			normal_range: "",
			date_conducted: "",
		});
		
		createLabRecord();
	}
	
	// this function is going to handle the submission of new or modified lab results
	
	const handleSubmit = async(e) => {
		
		e.preventDefault();
		
		if (editingLaResult) {
			
			await apiService.editLabResult(formData);
			
		} else {
			
			await apiService.createLabResult(formData);
		}
		
		setModalVisible(false);
		readLabResults();
	}
	
	// this function is going to handle changing events in the frontend element of the the lab results

	const handleChange = (e) => {
		
		setFormData(prev => ({
			
			...prev,
			[e.target.name]: e.target.value
		}));
	}
	
	return (
		
		<>
			<h3>Lab Results</h3>
			<Button variant="primary" onClick={handleShowAdd}>Create New Lab Result</Button>
			<br/>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>Lab Result</th>
						<th>Visit</th>
						<th>Test Type</th>
						<th>Result</th>
						<th>Units</th>
						<th>Normal Range</th>
						<th>Date Conducted</th>
					</tr>
				</thead>
				<tbody>
					{labResults.map(record => (
						<tr key={record.id_lab_result}>
							<td>{record.id_lab_result}</td>
							<td>{record.id_visit}</td>
							<td>{record.test_type}</td>
							<td>{record.result}</td>
							<td>{record.units}</td>
							<td>{record.normal_range}</td>
							<td>{record.date_conducted}</td>
							<td>
								<Button
									variant="warning"
									size="sm"
									className='ms-2'
									onClick={() => editLabRecord(record)}
								>
								Edit
								</Button>
								<Button
									varinat="danger"
									size='sm'
									onClick={() => deleteLabResult(record.id_lab_result)}
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
					<Modal.Title>{editingLaResult ? 'Edit Lab Result' : "Create New Lab Result"}</Modal.Title>
				</Modal.Header>
				<Form onSubmit={handleSubmit}>
					<Modal.Body>
						<Form.Group>
							<Form.Label>Viist ID</Form.Label>
							<Form.Control name="id_visit" value={formData.id_visit} onChange={handleChange} required />
						</Form.Group>
						<Form.Group>
							<Form.Label>Test Type</Form.Label>
							<Form.Control name="test_type" value={formData.test_type} onChange={handleChange} required />
						</Form.Group>
						<Form.Group>
							<Form.Label>Result</Form.Label>	
							<Form.Control name="result" value={formData.result} onChange={handleChange} required />			
						</Form.Group>
						<Form.Group>
							<Form.Label>Units</Form.Label>
							<Form.Control name="units" value={formData.units} onChange={handleChange} required />
						</Form.Group>
						<Form.Group>
							<Form.Label>Normal Range</Form.Label>	
							<Form.Control name="normal_range" value={formData.normal_range} onChange={handleChange} required />
						</Form.Group>
						<Form.Group>
							<Form.Label>Date Conducted</Form.Label>
							<Form.Control name="date_conducted" value={formData.date_conducted} onChange={handleChange} required />				
						</Form.Group>
					</Modal.Body>
					<Modal.Footer>
						<Button variant="primary" onClick={() => setModalVisible(false)}>Cancel</Button>
						<Button variant="secondary" type="submit">{editingLaResult ? 'Update' : 'Add'}</Button>
					</Modal.Footer>
				</Form>
			</Modal>
		</>
	)

}

export default LabResults;