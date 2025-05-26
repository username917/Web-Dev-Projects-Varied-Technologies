package com.walkinclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walkinclinic.Models.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{

}
