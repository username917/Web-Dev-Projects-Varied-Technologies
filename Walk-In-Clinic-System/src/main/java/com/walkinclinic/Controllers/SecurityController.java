package com.walkinclinic.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.JwtService;
import com.walkinclinic.DTO.LoginData;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SecurityController {
	

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtService jwtService;
	
	@PostMapping("/evaluate-login")
	public ResponseEntity<String> evaluateLogin(@RequestBody LoginData loginData) {
		System.out.println("The contents of login data at Spring Boot is: " + loginData.getUsername() + " and password: " + loginData.getPassword());
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword())
				);
		
		
		// 1. Authenticate the user
		
		
		
		System.out.println("The principal is: " + authentication.getPrincipal());
		
		
		try {
			
			
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
			
			// Generate jwt token
			
			
			String token = jwtService.generateToken(userDetails);
			
			// return the token in JSON response.
			
			Map<String, String> response = new HashMap<>();
			
			response.put("token", token);
			
			return ResponseEntity.ok(token);
			
		} catch (Error r) {
			
			System.out.println("Authentication failed: " + r.getMessage());
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
		
		//return ResponseEntity.ok("Login received successfully"); 
	}

}
