package com.hms.service.inter;

import java.util.List;

import com.hms.dto.DepartmentDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;

public interface IDepartmentService {

	public ResponseMessageDto addDepartment(DepartmentDto departmentDto);

	public List<DepartmentDto> getDepartments();

	public DepartmentDto getDepartmentById(Integer departmentId);

	public PhysicianDto getHeadOfDepartment(Integer departmentId);

	public List<DepartmentDto> getDepartmentsByHeadId(Integer head);

	public Boolean physicianIsHeadOfDepartment(Integer physicianId);

	public DepartmentDto updateDepartmentHeadId(Integer departmentId, DepartmentDto departmentDto);

	public DepartmentDto updateNameDepartment(Integer departmentId, DepartmentDto departmentDto);

	public List<Object> getListOfCertitificatioByDeptId(Integer deptId);

}
