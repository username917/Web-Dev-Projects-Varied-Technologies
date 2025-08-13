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
		
		const token = localStorage.getItem("token");
				
		console.log("The content of token is: ", token);
		
		try {
			
			return await axios.get(API_BASE_URL + "/view-appointments"			, {
			  headers: {
			    Authorization: `Bearer ${token}`
			  }
			});
			
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
				params: { idDoctor:  rmData.idDoctor }, // ✅ sends ?idDoctor=123 in UR
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
	
	async getBillingList() {
		
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.get(API_BASE_URL + "/get-billing-list", {
			  headers: {
			    Authorization: `Bearer ${token}`
			  }
			});
			
		} catch (error) {
			
			console.log("The error in retrieving the billing list is: ", error);
		}
	},
	
	async createBilling(formData) {
		
		console.log("The form data being submitted is: ", formData);
				
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.post(API_BASE_URL + "/book-billing", formData, {
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
				
			});
			
		} catch (error) {
			
			console.log("The error in creating a doctor is: ", error)
		}

	},
	
	async editBilling(formData) {
		
		const token = localStorage.getItem("token");
				
		try {
			
			return await axios.put(API_BASE_URL + "/edit-billing", formData, 			{
				
				params: { id_billing:  formData.id_boctor }, // ✅ sends ?idDoctor=123 in UR
				
				headers: {
					
			    	Authorization: `Bearer ${token}`
			  }
			});
			
		} catch (error) {
			
			console.log("The error in editing doctor is: ", error);
		}
		
	},
	
	async deleteBilling(id_billing) {
		
		console.log("The id in trying to delete a billing is: ", id_billing);
				
		const token = localStorage.getItem("token");
				
		console.log("The content of token at deleting a billing  is: ", token);
		
		try {
			
			return await axios.delete(API_BASE_URL + "/remove-billing", {
				
				params: {
					id_billing: id_billing
				},
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
			});
			
		} catch (error) {
			
			console.log("The error in deleting a billing is: ", error);
		}
			
	},
	
	async deleteAppointment(id_appointment) {
			
		console.log("The id in trying to delete an appointment is: ", id_appointment);
				
		const token = localStorage.getItem("token");
				
		console.log("The content of token at deleting an appointment is: ", token);
		
		try {
			
			return await axios.delete(API_BASE_URL + "/delete-appointment", {
				
				params: {
					id_appointment: id_appointment
				},
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
			});
			
		} catch (error) {
			
			console.log("The error in deleting an appointment is: ", error);
		}
			
	},
	
	async editAppointment(formData) {
		
		console.log("The contnet of formData at axios is: ", formData);
		
		const token = localStorage.getItem("token");
						
		try {
			
			return await axios.put(API_BASE_URL + "/update-appointment",
			  
				formData, // body
			  {
				
			    headers: {
					
			      Authorization: `Bearer ${token}`,
				  
			    },
				
			    params: {
					
			      id_appointment: formData.id_appointment, // must match exactly
			    
			  } 
			  }
			);

			
		} catch (error) {
			
			console.log("The error in editing appointment is: ", error);
		}
		
	},
	
	async createAppointment(formData) {
		
		console.log("The form data being submitted is: ", formData);
						
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.post(API_BASE_URL + "/book-appointment", formData, {
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
				
			});
			
		} catch (error) {
			
			console.log("The error in creating an appointment is: ", error)
		}
		
	},
		
		

	
	
}

export default apiService;