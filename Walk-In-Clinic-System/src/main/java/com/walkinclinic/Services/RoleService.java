package com.walkinclinic.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkinclinic.DTO.RolesDTO;
import com.walkinclinic.Models.Role;
import com.walkinclinic.repository.RolesRepository;

@Service
public class RoleService {
	
	@Autowired
	RolesRepository rolesRepo;

	public List<RolesDTO> retrieveRoles() {
		
		return rolesRepo.retrieveRoles();
	}

	public boolean deleteRole(Integer roleid) {
		
		int rowDeleted = rolesRepo.deletePrescription(roleid);
		
		return rowDeleted > 0;
	}

	public Role editRole(Integer roleid, RolesDTO request) {
		
		Role existingRole = rolesRepo.findById(roleid)
				.orElseThrow(() -> new RuntimeException("Role not found"));;
		
		existingRole.setRoleid(request.getRoleid());
		existingRole.setRolename(request.getRolename());
		
		return rolesRepo.save(existingRole);
	}

	public Role addRole(Role request) {
		
		return rolesRepo.save(request);
	}



}
