/**
 * this component is going to maintain all details regarding Roles definitions in the project
 */

import React, { useState, useEffect } from 'react';
import apiService from "../../services/apiService";
import { Table, Button, Modal, Form } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';

const Roles = () => {
	
	const [roles, setRoles] = useState([]);
	const [editingRole, setEditingRole] = useStae([]);
	
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
		
		await apiSerice.deleteRole(roleid);
		
		setRoles([]);
		readRoleRecords();
		
	}
		
}

export default Roles;