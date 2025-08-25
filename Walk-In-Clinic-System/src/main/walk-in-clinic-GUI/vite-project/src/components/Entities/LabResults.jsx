/**
 * this component is going to contain all details with respect to the Lab Results part of the project
 */

const LabResults = () => {
	
	const [labResults, setLabResults] = useState([]);
	const [editingLaResult, setEditingLabResult] = useState([]);
	
	const [modalVisible, setModalVisible] = useState(false);
	
	const [formData, setFormData] = useState({
		id_lab_result: "",
		id_visit: "",
		test_type: "",
		result: "",
		units: "",
		normal_range: "",
		date_conducted: "",
	});
	
	useEffect(() => {0
		readLabResults();
	}, [])
	
	// this function retrieves  the most current list of lab results
	
	const readLabResults = async() => {
		
	}
	
}

export default LabResults;