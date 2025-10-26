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
		
	async getHealthRecords() {
		
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.get(API_BASE_URL + "/get-health-records", {
			  headers: {
			    Authorization: `Bearer ${token}`
			  }
			});
			
		} catch (error) {
			
			console.log("The error in retrieving health records is: ", error);
		}
	},
	
	async deleteHealthRecord(id_health_record) {
		
		console.log("The id in trying to delete a health record is: ", id_health_record);
						
		const token = localStorage.getItem("token");
				
		console.log("The content of token at deleting a health record is: ", token);
		
		try {
			
			return await axios.delete(API_BASE_URL + "/delete-health-record", {
				
				params: {
					id_health_record: id_health_record
				},
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
			});
			
		} catch (error) {
			
			console.log("The error in deleting a health record is: ", error);
		}
	},
	
	async editHealthRecord(formData) {
		
		console.log("The content of formData for health records at axios is: ", formData);
				
		const token = localStorage.getItem("token");
						
		try {
			
			return await axios.put(API_BASE_URL + "/update-health-record",
			  
				formData, // body
			  {
				
			    headers: {
					
			      Authorization: `Bearer ${token}`,
				  
			    },
				
			    params: {
					
			      id_health_record: formData.id_health_record, // must match exactly
			    
			  	} 
			  }
			);
	
		} catch (error) {
			
			console.log("The error in editing health record is: ", error);
		}
	},
	
	async createHealthRecord(formData) {
		
		console.log("The form data being submitted for the creation of a health record is: ", formData);
								
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.post(API_BASE_URL + "/create-health-record", formData, {
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
				
			});
			
		} catch (error) {
			
			console.log("The error in creating a health record is: ", error)
		}
	},
	
	async getLabResults() {
			
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.get(API_BASE_URL + "/get-lab-results", {
			  headers: {
			    Authorization: `Bearer ${token}`
			  }
			});
			
		} catch (error) {
			
			console.log("The error in retrieving lab results is: ", error);
		}
	},
	
	async deleteLabResult(id_lab_result) {
			
		console.log("The id in trying to delete a health record lab result is: ", id_lab_result);
						
		const token = localStorage.getItem("token");
				
		console.log("The content of token at deleting a lab result is: ", token);
		
		try {
			
			return await axios.delete(API_BASE_URL + "/delete-lab-result", {
				
				params: {
					id_lab_result: parseInt(id_lab_result)
				},
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
			});
			
		} catch (error) {
			
			console.log("The error in deleting a lab result is: ", error);
		}
	},
	
	async editLabResult(formData) {
			
		console.log("The content of formData for lab results at axios is: ", formData);
				
		const token = localStorage.getItem("token");
						
		try {
			
			return await axios.put(`${API_BASE_URL}/update-lab-result`, formData, {
			  headers: { Authorization: `Bearer ${token}` },
			  params: { id_lab_result: formData.id_lab_result }
			});

		} catch (error) {
			
			console.log("The error in editing a lab result is: ", error);
		}
	},
	
	async createLabResult(formData) {
			
		console.log("The form data being submitted for the creation of a lab result is: ", formData);
								
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.post(API_BASE_URL + "/create-lab-result", formData, {
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
				
			});
			
		} catch (error) {
			
			console.log("The error in creating a lab result is: ", error)
		}
	},
	
	async getPatientRecords() {
				
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.get(API_BASE_URL + "/get-patient-records", {
			  headers: {
			    Authorization: `Bearer ${token}`
			  }
			});
			
		} catch (error) {
			
			console.log("The error in retrieving patients is: ", error);
		}
	},
	
	async editPatientRecord(formData) {
				
		console.log("The content of formData for patient record at axios is: ", formData);
				
		const token = localStorage.getItem("token");
						
		try {
			
			return await axios.put(`${API_BASE_URL}/update-patient-record`, formData, {
			  headers: { Authorization: `Bearer ${token}` },
			  params: { id_patient: formData.id_patient }
			});

		} catch (error) {
			
			console.log("The error in editing a patient record is: ", error);
		}
	},
	
	async deletePatientRecord(id_patient) {
				
		console.log("The id in trying to delete a patient record is: ", id_patient);
						
		const token = localStorage.getItem("token");
				
		console.log("The content of token at deleting a patient record is: ", token);
		
		try {
			
			return await axios.delete(API_BASE_URL + "/delete-patient-record", {
				
				params: {
					id_patient: parseInt(id_patient)
				},
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
			});
			
		} catch (error) {
			
			console.log("The error in deleting a patient record is: ", error);
		}
	},
	
	async createPatientRecord(formData) {
			
		console.log("The form data being submitted for the creation of a patient record is: ", formData);
								
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.post(API_BASE_URL + "/create-patient-record", formData, {
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
				
			});
			
		} catch (error) {
			
			console.log("The error in creating a patient record is: ", error)
		}
	},
	
	// this function is going to retreive the collection of vital signs from the database
	
	// there is a point that they need to be per patient to be useful than just a giant dump table
	
	async getVitalSigns() {
		
		try {
			
			return await axios.get("/get-vital-signs-records")
			
		} catch (error) {
			
			console.log("The error in retrieving data about vital signs is: ", error);
		}
	},
	
	// this functions is going to delete a vital signs record
	
	async deleteVitalSignsRecord(id_vitals) {
		
		const token = localStorage.getItem("token");
		
		try {
			
			return await axios.delete("/delete-vital-signs-record"			, {
								
				params: {
					id_vitals: parseInt(id_vitals)
				},
				
				headers: {
				    Authorization: `Bearer ${token}`
  			    }
			});
			
		} catch (error) {
			
			console.log("The error in deleting a vital signs record is: ", error);
		}
		
	},
	
	// this functions is going to edit a vital signs record
	
	async editVitalSignsRecord(formData) {
		
		try {
			
			/*
			return await axios.put(`${API_BASE_URL}/update-patient-record`, formData, {
			  headers: { Authorization: `Bearer ${token}` },
			  params: { id_patient: formData.id_patient }
			});
			*/
			
			return await axios.update(`${API_BASE_URL}/edit-vital-signs-record`, formData, {
				eaders: { Authorization: `Bearer ${token}` },
				params: { id_vitals: formData.id_vitals}
			})
			
		} catch (error) {
			
			console.log("The error in ")
		}
	},
	
	// this function is going to create a new vital signs record
	
	async createVitalSignsRecord(formData) {
		
		try {
			
			return await axios.post(API_BASE_URL + "/create-vital-signs-record", formData, {
							
				headers: {
				    Authorization: `Bearer ${token}`
			    }
				
			});
			
		} catch (error) {
			
			console.log("The error in creating a vital signs record is: ", error);
		}
	}
	

	
}

export default apiService;