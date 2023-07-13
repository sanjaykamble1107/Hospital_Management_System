package com.hms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.dto.DepartmentDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.entity.Department;
import com.hms.entity.Physician;
import com.hms.exception.model.DepartmentNotFoundException;
import com.hms.repository.DepartmentRepository;
import com.hms.service.inter.IDepartmentService;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	private ResponseMessageDto messageDto = new ResponseMessageDto();

	@Override
	public ResponseMessageDto addDepartment(DepartmentDto departmentDto) {
		Department department = new Department();
		Physician physician = new Physician();
		BeanUtils.copyProperties(departmentDto, department);
		physician.setEmployeeId(departmentDto.getHead());
		department.setHead(physician);
		departmentRepository.save(department);
		messageDto.setResponse("Record Created Successfully");
		return messageDto;
	}

	@Override
	public List<DepartmentDto> getDepartments() {
		List<Department> department = departmentRepository.findAll();
		List<DepartmentDto> depDtos = new ArrayList<>();
		for (Department dep : department) {
			DepartmentDto dto = new DepartmentDto();
			BeanUtils.copyProperties(dep, dto);
			dto.setHead(dep.getHead().getEmployeeId());
			depDtos.add(dto);
		}
		return depDtos;
	}

	@Override
	public DepartmentDto getDepartmentById(Integer departmentId) {
		Optional<Department> findById = departmentRepository.findById(departmentId);
		DepartmentDto departmentDto = new DepartmentDto();
		if (findById.isPresent()) {
			BeanUtils.copyProperties(findById.get(), departmentDto);
			departmentDto.setHead(findById.get().getHead().getEmployeeId());
			return departmentDto;
		}
		throw new DepartmentNotFoundException("No Department Found");
	}

	@Override
	public PhysicianDto getHeadOfDepartment(Integer departmentId) {
		Optional<Department> phycisian = departmentRepository.findById(departmentId);
		PhysicianDto phyDto = new PhysicianDto();
		if (phycisian.isPresent()) {
			phyDto.setEmployeeId(phycisian.get().getHead().getEmployeeId());
			phyDto.setPosition(phycisian.get().getHead().getPosition());
			phyDto.setSsn(phycisian.get().getHead().getSsn());
			phyDto.setName(phycisian.get().getHead().getName());
			return phyDto;
		}
		throw new DepartmentNotFoundException("No Physician Found");
	}

	@Override
	public List<DepartmentDto> getDepartmentsByHeadId(Integer head) {
		List<Department> findDepartmentByHeadId = departmentRepository.findDepartmentByHeadEmployeeId(head);
		if (findDepartmentByHeadId.isEmpty()) {
			throw new DepartmentNotFoundException("No Department Found with Head " + head);
		}
		List<DepartmentDto> departmentDto = new ArrayList<>();
		for (Department dep : findDepartmentByHeadId) {
			DepartmentDto dto = new DepartmentDto();
			Physician physician = new Physician();
			BeanUtils.copyProperties(dep, dto);
			physician.setEmployeeId(head);
			dto.setHead(physician.getEmployeeId());
			departmentDto.add(dto);
		}
		return departmentDto;
	}

	@Override
	public Boolean physicianIsHeadOfDepartment(Integer physicianId) {
		List<Department> findDepartmentByHead = departmentRepository.findDepartmentByHeadEmployeeId(physicianId);
		if (findDepartmentByHead.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public DepartmentDto updateDepartmentHeadId(Integer departmentId, DepartmentDto departmentDto) {
		Optional<Department> findById = departmentRepository.findById(departmentId);
		if (findById.isPresent()) {
			Physician physician = new Physician();
			Department department = new Department();
			BeanUtils.copyProperties(findById.get(), departmentDto);
			physician.setEmployeeId(departmentDto.getHead());
			BeanUtils.copyProperties(departmentDto, department);
			department.setHead(physician);
			departmentRepository.save(department);
			return departmentDto;
		}
		throw new DepartmentNotFoundException("No Department Found with Department Id: " + departmentId);
	}

	@Override
	public DepartmentDto updateNameDepartment(Integer departmentId, DepartmentDto departmentDto) {
		Optional<Department> findById = departmentRepository.findById(departmentId);
		if (findById.isPresent()) {
			Department department = new Department();
			Physician physician = new Physician();
			findById.get().setName(departmentDto.getName());
			BeanUtils.copyProperties(findById.get(), departmentDto);
			physician.setEmployeeId(departmentDto.getHead());
			BeanUtils.copyProperties(departmentDto, department);
			department.setHead(physician);
			departmentRepository.save(department);
			return departmentDto;
		}
		throw new DepartmentNotFoundException("No Department Found with Department Id: " + departmentId);
	}

	@Override
	public List<Object> getListOfCertitificatioByDeptId(Integer deptId) {
		List<Object> findCertificationOfHeadByDeptId = departmentRepository.findCertificationOfHeadByDeptId(deptId);
		if (findCertificationOfHeadByDeptId.isEmpty()) {
			throw new DepartmentNotFoundException("No Certifications found with " + deptId);
		}
		return findCertificationOfHeadByDeptId;
	}
}
