package com.walkinclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walkinclinic.Models.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

}
