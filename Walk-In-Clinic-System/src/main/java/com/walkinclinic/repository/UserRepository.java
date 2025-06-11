package com.walkinclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walkinclinic.Models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Object findByUsername(String username);

}
