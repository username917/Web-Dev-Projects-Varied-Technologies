/**
 * this component is going to provide a view for managing the tables in the database
 */

import React, { useState } from 'react';
import { Container, Row, Col, ListGroup } from 'react-bootstrap';
import Doctor from "../Entities/Doctor.jsx";
import Appointment from "../Entities/Appointment.jsx";

const Administration = () => {
	
	const [selectedMenu, setSelectedMenu] = useState("");
	
	const renderContent = () => {
		
		switch (selectedMenu) {
			
			case "Doctors":
				return <Doctor />;
			case "Appointments":
				return <Appointment />;
			default:
				return null;
		}
		
	};
	
	return (
		<div className="admin-dashboard">
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
			
			<div className="main-content">
				<div className="content=wrapper">
					{renderContent()}
				</div>
			</div>
		
		</div>
		
				
			
	)


}

export default Administration;