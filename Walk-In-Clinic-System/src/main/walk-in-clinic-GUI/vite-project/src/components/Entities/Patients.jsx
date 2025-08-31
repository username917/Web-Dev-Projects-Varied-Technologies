/**
 * this component is going to contain all details regarding the Patient(s) object and collections in the rpoject
 */

import apiService from "../../services/apiService";

const Patients = () => {
	
	const [patients, setPatients] = useState([]);
	const [editingPatient, setEditingPatient] = useState([]);
	
	const [modalVisible, setModalVisible] = uesState(false);
	
	const [formData, setFormData]= useState({
		id_patient: "",
		userid: "",
		first_name: "",
		last_name: "",
		date_of_birth: "",
		gender: "",
		phone: "",
		email: "",
		address: "",
		emergency_contact_name: "",
		emergency_contact_phone: ""
	})
	
	useEffect(() => {
		readPatientRecords();
	}, []);
	
	// this function retrieves the most current patient list
	
	const readPatientRecords = async() => {
		
		try {
			
			const respPatients = await apiService.getPatientRecords();
			console.log("The response for patient records is: ", respPatients.data);
			
			if (Array.isArray(respPatients.data)) {
				
				const patients = respPatients.data;
				setPatients(patients);
			} else {
				
				console.log("No patinets have been found.");
				return;
			}
			
		} catch (error) {
			
			console.log("The error in retrieving patient records is ", error);
		}
	}
	
	// this function is going ot edit a patient record
	
	const editPatientRecord = (patientRecord) => {
		
		setEditingPatient(patientRecord);
		setFormData(patientRecord);
		setModalVisible(true);
		
		console.log("Editing patient record with id: ", patientRecord);
	}
	
	// this function is going to add a patient record to the database
	
	const createPatientRecord = () => {
		
		setFormData("");
		setModalVisible(true);
	}
	
	// this function is going to handle the deletion of a patient record
	
	const deletePatientRecord = async(id_patient) => {
		
		console.log("Deleting patient record with id: ", id_patient);
		
		await apiService.deletePatientRecord(id_patient);
		setPatients([]);
		readPatientRecords();
	}
	
	// this function is going to handle the showing of the modal
	
	const handleShowAdd = () => {
		
		setEditingPatient(null);
		
		setFormData({
			id_patient: "",
			userid: "",
			first_name: "",
			last_name: "",
			date_of_birth: "",
			gender: "",
			phone: "",
			email: "",
			address: "",
			emergency_contact_name: "",
			emergency_contact_phone: ""
		})
		
		createPatientRecord();
	}
	
	// this function will handle the submisison of new or modified health records
	
	const handleSubmit = async (e) => {
		
		e.preventDefault();
		
		if (editingPatient) {
			
			await apiService.editPatientRecord(formData);
		} else {
			
			await apiService.createPatientRecord(formData);
		}
		
		setModalVisible(false);
		readPatientRecords();
	}
	
	// this function will handle the changing events in the frontned elements of the Patient component
	
	const handleChange = (e) => {
		
		setFormData(prev => ({
			
			...prev,
			[e.target.name]: e.target.value
		}))
	}
	
	return (
		<>
		</>
	)
	
	
}

export default Patients;