package com.walkinclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.walkinclinic.DTO.RolesDTO;
import com.walkinclinic.Models.Role;

import jakarta.transaction.Transactional;

public interface RolesRepository extends JpaRepository<Role, Integer>{

	@Query(value = "SELECT * FROM roles", nativeQuery = true)
	List<RolesDTO> retrieveRoles();

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM roles WHERE roleid = :roleid", nativeQuery = true)
	int deletePrescription(Integer roleid);
	
	

}
