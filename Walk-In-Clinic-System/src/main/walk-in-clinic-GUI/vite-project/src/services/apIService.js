/**
 * this will be the endpoint interface for the API
 */

import axios from 'axios';

const API_BASE_URL = '/api'; // Spring Boot server URL

const apiService = {
	
	async getDoctorLst() {
		
		try {
			
			return await axios.get(API_BASE_URL + "/get-doctors");
			
		} catch (error) {
			
			console.log("The error in retrieving the docor list is: " ,error);
		}
	},
	
	async evaluateLogin(loginData) {
		
		console.log("The content of login data is: ", loginData);
		
		try {
			
			return await axios.post(API_BASE_URL + "/evaluate-login", loginData);
			
		} catch (error) {
			
			console.log("The error in validating login is:", error);
		}
	},
	
	async getAppointments() {
		
		try {
			
			return await axios.get(API_BASE_URL + "/view-appointments")
			
			
		} catch (error) {
			
			console.log("The error in retrieving appointments is: , error");
		}
	},
	
	// this function retrieves and refreshes the doctor list 
	
	async getDoctorList() {
		
		try {
			
			return await axios.get("/get-doctor-list");
			
			
		} catch (error) {
			
			console.log("The error in retrieving the doctor list is: ", error);
		}
		
		
	},
	
	async editDoctor(formData) {
		
		try {
			
			return await axios.put("/edit-doctor", formData);
			
			
		} catch (error) {
			
			console.log("The error in editing doctor is: ", error);
		}
		
		
	},
	
	async createDoctor(formData) {
		
		try {
			
			return await axios.post("/add-doctor", formData);
			
			
		} catch (error) {
			
			console.log("The error in creating a doctor is: ", error)
		}
		
		
	},
	
	async deleteDoctor(doctorid) {
		
		try {
			
			return await axios.delete("/remove-doctor", doctorid);
			
			
		} catch (error) {
			
			console.log("The error in deleting a doctor is: ", error);
		}
		
	}
	
}

export default apiService;