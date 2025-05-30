/**
 * this component is going to provide a view for managing the tables in the database
 */

import React, { useState } from 'react';
import { Container, Row, Col, ListGroup } from 'react-bootstrap';
import Doctor from "../Entities/Doctor";
import Appointment from "../Entities/Appointment";

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
		
		<Container fluid className="mt-4">
			<Row>
				<Col md={3}>
					<ListGroup>
						<ListGroup.Item
							action
							active={selectedMenu === "Doctors"}
							onClick={setSelectedMenu("Doctors")}
						>
							Doctors
						</ListGroup.Item>
						<ListGroup.Item
							action
							active={selectedMenu === "Appointments"}
							onClick={setSelectedMenu("Appointments")}
						>
							Appointments
						</ListGroup.Item>
					</ListGroup>
				</Col>
				<Col md={9}>
					{renderContent()}
				</Col>
			</Row>
		</Container>
	)


}

export default Administration;