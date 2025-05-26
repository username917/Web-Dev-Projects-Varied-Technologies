/**
 * this component is going to focus on managing the Doctor table of the project
 */
import apiService from "../services/apIService";

const Doctor = () => {
	
	const [doctor, setDoctor] = useState("");
	const [doctors, setDoctors] = useState([]);
	
	useEffect(() => {
		
		recallDoctorList();
		
	}, []);
	
	const recallDoctorList = () => {
		
		const respDoctorList = apiService.getDoctorList();
	}
	
	return (
		
		<div>
			
		</div>
	)
}