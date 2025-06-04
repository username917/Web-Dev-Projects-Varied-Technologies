/**
 * this component is going to focus on managing the Doctor table of the project
 */
import React, { useState, useEffect } from 'react';
import apiService from "../../services/apiService";
import { Table, Button } from "react-bootstrap";

const Doctor = () => {
	
	const [doctor, setDoctor] = useState("");
	const [doctors, setDoctors] = useState([]);
	
	useEffect(() => {
		
		recallDoctorList();
		
	}, []);
	
	const recallDoctorList = async () => {
		
		try {
			const respDoctorList = await apiService.getDoctorList();
			
			if (respDoctorList){
						
				const respDoctors = respDoctorList.data;
				setDoctor(respDpctors);
			}
			
		} catch (error) {
			
			console.log("The doctor list function is not built yet.");
		}
		
		
		
	}
	
	return (
		
		<div>
			
		</div>
	)
}

export default Doctor;