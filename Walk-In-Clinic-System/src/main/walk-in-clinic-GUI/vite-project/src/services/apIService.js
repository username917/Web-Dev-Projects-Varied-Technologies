/**
 * this will be the endpoint interface for the API
 */

import axios from 'axios';

const API_BASE_URL = '/api'; // Spring Boot server URL

const apiService = {
	
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
		
		const token = localStorage.getItem("token");
		
		console.log("The content of token is: ", token);
		
		try {
			
			return await axios.get(API_BASE_URL + "/get-doctor-list", {
			  headers: {
			    Authorization: `Bearer ${token}`
			  }
			});
			
		} catch (error) {
			
			console.log("The error in retrieving the doctor list is: ", error);
		}
		
		
	},
	
	async editDoctor(formData) {
		
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.put(API_BASE_URL + "/edit-doctor", formData, 			{
				params: { idDoctor: formData.idDoctor }, // ✅ sends ?idDoctor=123 in UR
				headers: {
			    Authorization: `Bearer ${token}`
			  }
			});
			
			
		} catch (error) {
			
			console.log("The error in editing doctor is: ", error);
		}
		
		
	},
	
	async createDoctor(formData) {
		
		console.log("The form data being submitted is: ", formData);
		
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.post(API_BASE_URL + "/add-doctor", formData, {
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
				
			});
			
			
		} catch (error) {
			
			console.log("The error in creating a doctor is: ", error)
		}
		
		
	},
	
	async deleteDoctor(id_doctor) {
		
		console.log("The id in trying to delete a doctor is: ", id_doctor);
		
		const token = localStorage.getItem("token");
				
		console.log("The content of token at deleting a doctor is: ", token);
		
		try {
			
			return await axios.delete(API_BASE_URL + "/remove-doctor", {
				
				params: {
					id_doctor: id_doctor
				},
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
			});
			
			
		} catch (error) {
			
			console.log("The error in deleting a doctor is: ", error);
		}
		
	},
	
	// this function is going to retrieve the billing list
	
	async getBilingList() {
		
		try {
			
			return await axios.get(API_BASE_URL + "/get-billing-list")
		} catch (error) {
			
			console.log("The error in retrieving the billing list is: ", error);
		}
	}
}

export default apiService;