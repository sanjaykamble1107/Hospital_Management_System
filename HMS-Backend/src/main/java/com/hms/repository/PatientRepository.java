package com.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	Optional<Patient> findPatientBySsnAndPcpEmployeeId(Integer ssn, Integer pcp);

	List<Patient> findPatientByPcpEmployeeId(Integer pcp);


}
