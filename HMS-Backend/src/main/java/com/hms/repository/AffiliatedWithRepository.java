package com.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entity.Affiliated_With;

public interface AffiliatedWithRepository extends JpaRepository<Affiliated_With, Integer> {

	public List<Affiliated_With> findCompositeByCompositeDepartmentDepartmentId(Integer deptId);

	public List<Affiliated_With> findCompositeByCompositePhysicianEmployeeId(Integer physicianId);

	public Integer countByCompositeDepartmentDepartmentId(Integer deptId);

	public List<Affiliated_With> findPrimaryaffiliationByCompositePhysicianEmployeeId(Integer physicianId);
	
	public Optional<Affiliated_With> findByCompositePhysicianEmployeeIdAndCompositeDepartmentDepartmentId(Integer physicianId,Integer deptId);
}
