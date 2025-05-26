package com.walkinclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walkinclinic.Models.Appointment;

public interface AppointmentsRepository extends JpaRepository<Appointment, Integer>{

}
