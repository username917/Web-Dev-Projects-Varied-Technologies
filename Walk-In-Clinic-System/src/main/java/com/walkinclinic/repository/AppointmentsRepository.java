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
	@Query(value = "DELETE FROM appointments WHERE id_appointment = :id_appointment", nativeQuery = true)
	int cancelAppointment(@Param("id_appointment") Integer id_appointment);

}
