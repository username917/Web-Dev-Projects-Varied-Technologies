/**
 * this component is going to maintain all details regarding Roles definitions in the project
 */

import React, { useState, useEffect } from 'react';
import apiService from "../../services/apiService";
import { Table, Button, Modal, Form } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';

const Roles = () => {
	
	const [roles, setRoles] = useState([]);
	const [editingRole, setEditingRole] = useState([]);
	
	const [modalVisible, setModalVisible] = useState(false);
	
	const [formData, setFormData] = useState({
		
		roleid: "",
		rolename: ""
	});
	
	useEffect(() => {
		
		readRoleRecords();
	})	
	
	const readRoleRecords = async () => {
		
		try {
			
			const readRoles = await apiService.readRoleRecords();
			console.log("The response for roles is: ", readRoles.data);
			
			if (Array.isArray(readRoles.data)) {
				
				const roles = readRoles.data;
				setRoles(roles);
				
			}
			
		} catch (error) {
			
			console.log("The error in retrieving roles is: ", error);
			return;
		}
	}
	
	// this function is going to edit a roles record
	
	const editRole = (role) => {
		
		setEditingRole(role);
		setFormData(role);
		setModalVisible(true);
		
		console.log("Editing a orle with id: ", role);
		
	}
		
	
	// this function is going to create a role and add it to the relevant table in the database
	
	const createRole = () => {
		
		setFormData("");
		setModalVisible(true);
	}
	
	// this function is going to handle a deletion of a role
	
	const deleteRole = async (roleid) => {
		
		console.log("Deleteing role record with id: ", roleid);
		
		await apiService.deleteRole(roleid);
		
		setRoles([]);
		readRoleRecords();
		
	}
	
	// this function is going to handle the showing of the modal
	
	const handleShowAdd = () => {
		
		setEditingRole(null);
		
		setFormData({
			roleid: '',
			rolename: ''
		})
		
		createRole();
	}
	
	// this function is going ot handle the subission of a new or modified role record
	
	const handleSubmit = async (e) => {
		
		e.preventDefault();
		
		if (editingRole) {
			
			await apiService.editRole(formData);
			
		} else {
			
			await apiService.createRole(formData);
		}
		
		setModalVisible(false);
		readRoleRecords();
	}
	
	// this function is going to handle changing events in the frontend element of the Roles odule
	
	const handleChange = (e) => {
		
		setFormData(prev => ({
			
			...prev,
			[e.target.name]: e.target.value
		}));
	} 
	
	return (
		
		<>
			<h3>Roles</h3>
			<Button variant="primary" onClick={handleShowAdd}>Create New Role Instance</Button>
			<br/>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>Role</th>
						<th>Role Definition</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					{roles.map(record => (
						<tr key={record.roleid}>
							<td>{record.roleid}</td>
							<td>{record.rolename}</td>
							<td>
								<Button
									variant="warning"
									size="sm" className="ms-2"
									onClick={() => editRole(record)}
								>
									Edit
								</Button>
								<Button
									variant="danger"
									size="sm"
									onClick={() => deleteRole(record.roleid)}
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
					<Modal.Title>{editingRole ? "Edit Role" : "Create a New Role"}</Modal.Title>
				</Modal.Header>
				<Form onSubmit={handleSubmit}>
					<Modal.Body>
						<Form.Group>
							<Form.Label>Role Name</Form.Label>
							<Form.Control name="rolename" value={formData.rolename} onChange={handleChange} required></Form.Control>
						</Form.Group>
					</Modal.Body>
				</Form>
			</Modal>
		</>
	)
		
}

export default Roles;