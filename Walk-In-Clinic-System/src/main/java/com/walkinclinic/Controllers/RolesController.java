package com.walkinclinic.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkinclinic.DTO.RolesDTO;
import com.walkinclinic.Models.Role;
import com.walkinclinic.Services.RoleService;

@RestController
@RequestMapping("/api")
public class RolesController {
	
	@Autowired
	RoleService roleService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/get-roles")
	public ResponseEntity<List<RolesDTO>> retrieveRoles() {
		
		List<RolesDTO> rolesData = roleService.retrieveRoles();
		
		if (!rolesData.isEmpty()) {
			
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(rolesData);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete-role")
	public ResponseEntity<?> deleteRole(@RequestParam("roleid") Integer roleid) {
		
		boolean deleted = roleService.deleteRole(roleid);
		
		if (deleted) {
			
			return ResponseEntity.ok("Role has been deleted");
		
		} else {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update-role")
	public ResponseEntity<Role> editRole(@RequestParam("roleid") Integer roleid,
			@RequestBody RolesDTO request) {
		
		System.out.println("Editing role...");
		
		if (!roleid.equals(request.getRoleid())) {
			return ResponseEntity.badRequest().build();
		}
		
		System.out.println("Role DTO at the API is: " + request.toString());
		
		Role role = roleService.editRole(roleid, request);
		
		return ResponseEntity.ok(role);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/create-role")
	public ResponseEntity<Role> addRole(@RequestBody Role request) {
		
		Role roleRec = roleService.addRole(request);
		
		return new ResponseEntity<>(roleRec, HttpStatus.CREATED);
	}
	

}
