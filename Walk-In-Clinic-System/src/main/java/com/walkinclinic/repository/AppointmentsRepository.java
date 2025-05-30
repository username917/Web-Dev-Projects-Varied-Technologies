package com.walkinclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walkinclinic.Models.Appointment;

import jakarta.transaction.Transactional;

public interface AppointmentsRepository extends JpaRepository<Appointment, Integer>{

	@Query(value = "SELECT availability FROM doctors WHERE lName = :lName", nativeQuery = true)
	String checkDoctorAvailability(@Param("lName") String lName);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM appointments WHERE idAppointment = :idAppointment", nativeQuery = true)
	int cancelAppointment(@Param("idAppointment") Integer idAppointment);

}
