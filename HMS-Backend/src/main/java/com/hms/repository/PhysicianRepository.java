package com.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entity.Physician;

public interface PhysicianRepository extends JpaRepository<Physician, Integer> {

	Optional<Physician> findPhysicianByName(String name);

	List<Physician> findPhysicianByPosition(String position);
	

}
