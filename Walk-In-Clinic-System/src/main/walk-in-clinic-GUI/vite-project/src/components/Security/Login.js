/**
 * this will be the login component that secures access to the project 
 */

const Login = () => {
	
	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");
	const [error, setError] = useState("");
	
	const evaluateLogin = async () => {
		
		const loginData = {
			username: username,
			password: password
		}
		
		if (!email || !password) {
			
			setError("Email and password are required");
			return;
		}
		
		const respLogin = await apiService.evaluateLogin(loginData);
		console.log("The response from the login attempt is: ", respLogin.data);
		
	}
	
	return (
		
		<div>
			<Container className="mt-5">
				<Row className="justify-content-md-center">
					<Col md={4}>
						<h2 className="text-center">Login</h2>
						{error && <Alert variant="danger">{error}</Alert>}
						<Form>
							<Form.Group controlId="formEmail" className="mb-3">
								<Form.Label>Email address</Form.Label>
								<Form.Control
									type="email"
									placeholder="Enter email"
									value={username}
									onChange={(e) => setUsername(e.target.value)}
									required
								/>
							</Form.Group>
							
							<Form.Group>
								<Form.Label>Password</Form.Label>
								<Form.Control
									type="password"
									placeholder="Password"
									value={password}
									onChange={(e) => setPassword(e.target.value)}
									required
								/>
							</Form.Group>
							
							<Button variant="primary" type="submit" className="w-100" onCLick={evaluateLogin}>
								Login
							</Button>
						</Form>
					</Col>
				</Row>
			</Container>
		</div>
	)
	
}