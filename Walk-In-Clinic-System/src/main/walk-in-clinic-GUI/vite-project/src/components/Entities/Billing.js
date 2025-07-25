/**
 * this component is going to contain all relevant artefacts for the Billing part of the project
 */

const Billing = () => {
	
	const [editingBilling, setEditingBilling] = useState("");
	const [billings, setBillings] = useState([]);
	const [modalVisible, setModalVisible] = useState(false);
	
	const [formData, setFormData] = useState({
		
		idBilling: null,
		idVisit: '',
		amount: '',
		insurancePolicy: '',
		paymentStatus: ''
	})
	
	// intiiate the component by loading the list of billings.
	
	useEffect(() => {
		
		recallBillingsList();
	
	}, [])
	
	const recallBillingsList = async () => {
		
		try {
			
			await respBillingList = await apiService.getBillingList();
			
		} catch (error) {
			
			console.log("The ")
		}
		
	}
	
}

export default Billing;