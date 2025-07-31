/**
 * this component is going to contain all relevant artefacts for the Billing part of the project
 */
import React, { useState, useEffect } from 'react';
import apiService from "../../services/apiService";
import { Table, Button, Modal, Form } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';

const Billing = () => {
	
	const [editingBilling, setEditingBilling] = useState("");
	const [billings, setBillings] = useState([]);
	const [modalVisible, setModalVisible] = useState(false);
	
	const [formData, setFormData] = useState({
		
		id_billing: null,
		id_visit: '',
		amount: '',
		insurance_policy: '',
		payment_status: ''
	})
	
	// intiiate the component by loading the list of billings.
	
	useEffect(() => {
		
		recallBillingsList();
	
	}, [])
	
	const recallBillingsList = async () => {
		
		try {
			
			const respBillingList = await apiService.getBillingList();
			console.log("The response for billings list is: ", respBillingList.data);
			
			if (respBillingList.data) setBillings(respBillingList.data)
			
		} catch (error) {
			
			console.log("The error in getting billings list is: ", error);
		}
		
	}
	
	// this function handles showing of the modal for the addition of a new billing instance
	
	const handleShowAdd = () => {
			
			setEditingBilling(null);
			
			setFormData({
				id_billing: '',
				id_visit: '',
				amount: '',
				insurance_policy: '',
				payment_status: ''
			})
			
			 createBilling();
		}
		

	// this function is for creating a billing
	
	const createBilling = () => {
		
		setFormData("");
		setModalVisible(true);
	
	}
		
	// this function is for editing a billing
	
	const editBilling = (billing) => {
		
		setEditingBilling(billing);
		setFormData(billing);
		setModalVisible(true);
		
		console.log("Editing Billing with id: ", billing);
	
	
	}	
	
	// this function is for deleting a billing
	
	const deleteBilling = async (id_billing) => {
		
		console.log("Deleting Billing entry with id: ", id_billing);
		
		await apiService.deleteBilling(id_billing);
		recallBillingsList();
		
		
	} 
	
	// this function is going to handle the submit process for creating or editeing a billing via the modal
	
	const handleSubmit = async (e) => {
		
		e.preventDefault();
		
		if (editingBilling) {
			
			await apiService.editBilling(formData);
			
		} else {
			
			await apiService.createBilling(formData);
		}
		
		setModalVisible(false);
		recallBillingsList();
		
	}
	
	// this function is going to handle changes to the Billing object
	
	const handleChange = (e) => {
		
		setFormData(prev => ({
			
			...prev,
			[e.target.name]: e.target.value
		}));
	};
	
	return (
		
		<div>
			<h1>Billings Table</h1>
			<br/>
			<Button variant='primary' onClick={handleShowAdd}>Create New Billing</Button>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>Visit</th>
						<th>Amount Owed</th>
						<th>Insurance Policy</th>
						<th>Payment Status</th>
						<th>Actions</th>
					</tr>
				</thead>
				
				<tbody>
					{billings.map(billing => (
						<tr key={billing.id_billing}>
							<td>{billing.id_visit}</td>
							<td>{billing.amount}</td>
							<td>{billing.insurance_policy}</td>
							<td>{billing.payment_status}</td>
							<td>
								<Button 
									variant='warning'
									onClick={() => editBilling(billing)} // pass whole doctor object for editing
								>Modify</Button>
		
								<Button 
									variant='danger'
									onClick={() => deleteBilling(billing.id_billing)}
								>Delete</Button>
							</td>
						</tr>
					))}
				</tbody>
			</Table>
			
			<Modal show={modalVisible} onHide={() => setModalVisible(false)}>
				<Modal.Header closeButton>
					<Modal.Title>{editingBilling ? 'Edit Current Billing' : 'Create a New Billing'}</Modal.Title>
				</Modal.Header>
				<Form onSubmit={handleSubmit}>
					<Modal.Body>
						<Form.Group>
							<Form.Label>Visit</Form.Label>
							<Form.Control name="visit" value={formData.visit} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Amount Owed</Form.Label>
							<Form.Control name="amount" value={formData.amount} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Insurance Policy</Form.Label>
							<Form.Control name="insurance-policy" value={formData.insurance_policy} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label>Payment Status</Form.Label>
							<Form.Control name="payment-status" value={formData.payment_status} onChange={handleChange} required></Form.Control>
						</Form.Group>
					</Modal.Body>
					<Modal.Footer>
						<Button variant="secondary" onClick={() => setModalVisible(false)}>Cancel</Button>
						<Button variant="primary" type="submit">{editingBilling ? 'Update' : 'Add'}</Button>
					</Modal.Footer>
				</Form>
			</Modal>
			
		
		</div>
	)
	
}

export default Billing;