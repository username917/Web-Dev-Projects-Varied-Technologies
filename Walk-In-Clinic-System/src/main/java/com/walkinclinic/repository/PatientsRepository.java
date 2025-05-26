package com.walkinclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walkinclinic.Models.Patient;

public interface PatientsRepository extends JpaRepository<Patient, Integer> {

}
