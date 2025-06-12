package com.walkinclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walkinclinic.Models.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

}
