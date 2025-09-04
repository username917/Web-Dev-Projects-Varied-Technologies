package com.walkinclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walkinclinic.Models.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

}
