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
}

export default Roles;