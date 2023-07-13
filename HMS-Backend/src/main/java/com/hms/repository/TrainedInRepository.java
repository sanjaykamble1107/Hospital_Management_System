package com.hms.repository;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hms.entity.TrainedIn;

public interface TrainedInRepository extends JpaRepository<TrainedIn, Integer> {

	public List<TrainedIn> findByCompositeIdPhysicianEmployeeId(Integer physicianId);

	public List<TrainedIn> findByCompositeIdTreatmentCode(Integer procedureId);

	public Optional<TrainedIn> findByCompositeIdPhysicianEmployeeIdAndCompositeIdTreatmentCode(Integer physicianId, Integer code);
	
	public List<TrainedIn> findByCertificationExpiresBetween(LocalDateTime startDate,LocalDateTime expiry);

	@Query("select distinct(t.compositeId.treatment) from TrainedIn t")
	public List<Object> findDistinctAll();
}
