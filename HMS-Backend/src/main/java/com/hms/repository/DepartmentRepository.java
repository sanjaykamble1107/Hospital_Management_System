package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	List<Department> findHeadByDepartmentId(Integer departmentId);

	List<Department> findDepartmentByHeadEmployeeId(Integer head);

	@Query(value = "select trained_in.* from trained_in join physician on trained_in.Physician = physician.EmployeeID join department on department.Head = physician.EmployeeID where department.DepartmentID =:deptId", nativeQuery = true)
	public List<Object> findCertificationOfHeadByDeptId(@Param("deptId") Integer deptId);

}
