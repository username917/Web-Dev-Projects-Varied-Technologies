package com.walkinclinic.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.DTO.LoginData;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SecurityController {
	
	@PostMapping("/evaluate-login")
	public ResponseEntity<String> evaluateLogin(@RequestBody LoginData loginData) {
		
		System.out.println("The contens of login data is: " + loginData.getUsername() + " and password: " + loginData.getPassword());
		
		return ResponseEntity.ok("Login received successfully"); 
	}

}
