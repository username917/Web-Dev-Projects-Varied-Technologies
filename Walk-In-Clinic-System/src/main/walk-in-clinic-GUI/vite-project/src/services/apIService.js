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
	}
	
	
}

export default apiService;