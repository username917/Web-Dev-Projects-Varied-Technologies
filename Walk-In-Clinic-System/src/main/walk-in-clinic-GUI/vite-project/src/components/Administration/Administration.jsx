/**
 * this component is going to provide a view for managing the tables in the database
 */

import React, { useState } from 'react';
import { Container, Row, Col, ListGroup } from 'react-bootstrap';
import Appointment from "../Entities/Appointment.jsx";
import Billing from "../Entities/Billing.jsx";
import Doctor from "../Entities/Doctor.jsx";
import HealthRecords from "../Entities/HealthRecords.jsx";
import LabResults from "../Entities/LabResults.jsx";
import Patients from "../Entities/Patients.jsx";
import Prescriptions from "../Entities/Prescriptions.jsx";
import Reminders from "../Entities/Reminders.jsx";
import Roles from "../Entities/Roles.jsx";
import UserManagement from "../Entities/UserManagement.js";
import Visitors from "../Entities/Visitors.js";
import VitalSigns from "../Entities/VitlalSigns.jsx";


import { Sidebar, Menu, MenuItem } from 'react-pro-sidebar';
import "../../styling/Administration.css"

const Administration = () => {
	
	const [selectedMenu, setSelectedMenu] = useState("");
	
	const renderContent = () => {
		
		// previous  version in commented code for reference; to be removed later.
		
		/*
		switch (selectedMenu) {
			
			case "Doctors":
				return <Doctor />;
			case "Appointments":
				return <Appointment />;
			default:
				return null;
		}
		*/
		
	};
	
	return (
		<div className="admin-dashboard">
		
			<Sidebar id="admin-sidebar">
				<Menu>
					
					<MenuItem onClick={() => setSelectedMenu("Appointments")}>Appointments</MenuItem>
					<MenuItem onClick={() => setSelectedMenu("Billing")}>Billing</MenuItem>
					<MenuItem onClick={() => setSelectedMenu("Doctors")}>Doctors</MenuItem>
					<MenuItem onClick={() => setSelectedMenu("Health Records")}>Health Records</MenuItem>
					<MenuItem onClick={() => setSelectedMenu("Lab Results")}>Lab Results</MenuItem>
					<MenuItem onClick={() => setSelectedMenu("Patients")}>Patients</MenuItem>
					<MenuItem onClick={() => setSelectedMenu("Prescriptions")}>Prescriptions</MenuItem>
					<MenuItem onClick={() => setSelectedMenu("Reminders")}>Reminders</MenuItem>
					<MenuItem onClick={() => setSelectedMenu("Roles")}>Roles</MenuItem>
					<MenuItem onClick={() => setSelectedMenu("Visitors")}>Visits</MenuItem>
					<MenuItem onClick={() => setSelectedMenu("Vital Signs")}>Vital Signs</MenuItem>
					<MenuItem onClick={() => setSelectedMenu("User Management")}>User Management</MenuItem>
				</Menu>
			</Sidebar>
			
			<div className="admin-content">
				{selectedMenu === "Appointments" && <Appointment />}
				{selectedMenu === "Billing" && < Billing/>}
				{selectedMenu === "Doctors" && <Doctor />}
				{selectedMenu === "Health Records" && < HealthRecords/>}
				{selectedMenu === "Lab Results" && < LabResults/>}
				{selectedMenu === "Patients" && < Patients/>}
				{selectedMenu === "Prescriptions" && < Prescriptions/>}
				{selectedMenu === "Reminders" && < Reminders/>}
				{selectedMenu === "Roles" && < Roles/>}
				{selectedMenu === "User Management" && < UserManagement/>}
				{selectedMenu === "Visitors" && < Visitors/>}
				{selectedMenu === "Vital Signs" && < VitalSigns/>}
			
			</div>
		
			{ /* 
			<div className="sidebar">
				<ListGroup variant="flush">
					<ListGroup.Item
						action
						active={selectedMenu === "Doctors"}
						onClick={() => setSelectedMenu("Doctors")}
					>
						Doctors
					</ListGroup.Item>
					<ListGroup.Item
						action
						active={selectedMenu === "Appointments"}
						onClick={() => setSelectedMenu("Appointments")}
					>
						Appointments
					</ListGroup.Item>
				</ListGroup>
			</div>
			*/}
			<div className="main-content">
				<div className="content=wrapper">
					{renderContent()}
				</div>
			</div>
			
		
		</div>
		
				
			
	)


}

export default Administration;