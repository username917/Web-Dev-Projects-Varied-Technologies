/**
 * this will be the endpoint interface for the API
 */

import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api'; // Spring Boot server URL

const apiService = {
	
	async getDoctorLst() {
		
		try {
			
			return await axios.get(API_BASE_URL = "/get-doctors");
			
			
			
			
		} catch (error) {
			
			console.log("The error in retrieving the docor list is: " ,error);
		}
	}
	
	
}

export default apIService;