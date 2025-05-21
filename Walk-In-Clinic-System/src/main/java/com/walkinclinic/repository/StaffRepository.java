package com.walkinclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walkinclinic.Models.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
