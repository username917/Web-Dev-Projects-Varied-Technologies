/**
 * this component is going to contain all details regarding Health Records for the project
 */

const HealthRecords = () => {
	
	const [healthRecords, setHealthRecords] = useState([]);
	const [editingHealthRecord, setEditingHealthRecord] = useState([]);
	
	const [modalVisible, setModalVisible] = useState(false);
	
	const [formData, setFormData] = useState({
		id_health_record: "",
		id_patient: "",
		id_visit: "",
		record_date: "",
		summary: "",
		notes: "",
		created_by: ""
	});
	
	useEffect(() => {
		readHealthRecords();
	}, []);
	
	// this function retrieves the most current list of health records from the database
	
	const readHealthRecords = async() => {
		
		try {
			
			const respHealthRecords = await apiService.getHealthRecords();
			console.log("The response from the API service for health records is: ", respHealthRecords.data);
			
			if (Array.isArray(respHealthRecords.data)) {
				
				const healthRecords = respHealthRecords.data;
				setHealthRecords(healthRecords);
			} else {
				
				console.log("No health records have been found");
				return;
			}
		
		} catch (error) {
			
			console.log("The error in retrieving health records is: ", error);
		}
	}
	
	// this function is going to edit a health record
	
	const editHealthRecord = (healthRecord) => {
		
		setEditingHealthRecord(healthRecord);
		setFormData(healthRecord);
		setModalVisible(false);
		
		console.log("Editing health record wiht id: ", healthRecord);
	}
	
	// this function is going to add a health record to the database
	
	const createHealthRecord = () => {
		
		setFormData("");
		setModalVisible(true);
	}
	
	// this function is going to handle the deletion of a health record
	
	const deleteHealthRecord = async(healthRecord) => {
		
		console.log("Deleting health record with id: ", healthRecord.id_health_record);
		
		await apiService.deleteHealthRecord(healthRecord.id_heatlh_record);
		readHealthRecords();
	}
	
	// this function is going to handle the showing of the modal
	
	const handleShowAdd = () => {
		
		setEditingHealthRecord(null);
		
		setFormData({
			id_health_record: "",
			id_patient: "",
			id_visit: "",
			record_date: "",
			summary: "",
			notes: "",
			created_by: ""
		})
		
		createHealthRecord();
	}
	
	// this function is going to handle the submission of new or modified health records
	
	const handleSubmit = async (e) => {
		
		e.preventDefault();
		
		if (editingHealthRecord) {
			
			await apiService.editHealthRecord(formData);
			
		} else {
			
			await userService.createHealthRecord(formData);
			
		}
		
		setModalVisible(false);
		readHealthRecords();
	}
	
	// this function will handle changing events in the frontend element of the Health Record appointment
	
	const handleChange = (e) => {
		
		setFormData(prev => ({
			
			...prev,
			[e.target.name]: e.target.value	
		
		}));
	};
	
	return (
		
		<>
			<h3>Health Records</h3>
			<Button variant="primary" onClick={handleShowAdd}></Button>
			<br/>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>Health Record</th>
						<th>Patient</th>
						<th>Visit</th>
						<th>Record Date</th>
						<th>Summary</th>
						<th>Notes</th>
						<th>Created By</th>
					</tr>
				</thead>
				<tbody>
					{healthRecords.map(record => (
						<tr key={record.id_health_record}>
							<td>{record.id_patient}</td>
							<td>{record.id_visit}</td>
							<td>{record.record_date}</td>
							<td>{record.summary}</td>
							<td>{record.notes}</td>
							<td>{record.created_by}</td>
							<td>
								<Button
									variant="warning"
									size="sm" className="ms-2"
									onClick={() => editHealthRecord(record)}
								>
									Edit
								</Button>
								<Button
									variant="danger"
									size="sm"
									onClick={() => deleteHealthRecord(record)}
								>
									Delete
								</Button>
							</td>
						</tr>
					))}
				</tbody>
			</Table>
			
			{/*
				
				id_patient: "",
							id_visit: "",
							record_date: "",
							summary: "",
							notes: "",
							created_by: ""
			*/}
			<Modal show={modalVisible} onHide={() => setModalVisible(false)}>
				<Moda.Header closeButton>
					<Modal.Title>{editingHealthRecord ? "Edit Health Record" : "Create New Health Record"}</Modal.Title>
				</Moda.Header>
				<Form onSubmit={handleSubmit}>
					<Modal.Body>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control name="id_patient" value={formData.id_patient} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control name="id_visit" value={formData.id_visit} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control name="record_date" value={formData.record_date} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control name="summary" value={formData.summary} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control name="notes" value={notes} onChange={handleChange} required></Form.Control>
						</Form.Group>
						<Form.Group>
							<Form.Label></Form.Label>
							<Form.Control name="created_by" value={formData.created_by} onChange={handleChange} required></Form.Control>
						</Form.Group>
					</Modal.Body>
					<Modal.Footer>
						<Button variant="primary" onClick={() => setModalVisible(false)}>Cancel</Button>
						<Button variant="secondary" type="submit">{editingAppt ? 'Update' : 'Add'}</Button>
					</Modal.Footer>
				</Form>
			</Modal>
			
		</>
	)
}

export default HealthRecords;