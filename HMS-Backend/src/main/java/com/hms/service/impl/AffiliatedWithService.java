package com.hms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dto.Affiliated_WithDto;
import com.hms.dto.DepartmentDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.entity.AffiliatedWithComposite;
import com.hms.entity.Affiliated_With;
import com.hms.entity.Department;
import com.hms.entity.Physician;
import com.hms.exception.model.AffiliatedWithNotFoundException;
import com.hms.exception.model.PhysicianNotFoundException;
import com.hms.exception.model.ValidationException;
import com.hms.repository.AffiliatedWithRepository;
import com.hms.service.inter.IAffiliatedWithService;

@Service
public class AffiliatedWithService implements IAffiliatedWithService {

	@Autowired
	private AffiliatedWithRepository affiliatedWithRepository;

	private ResponseMessageDto messageDto = new ResponseMessageDto();

	@Override
	public ResponseMessageDto saveAffiliated(Affiliated_WithDto affiliated_WithDto) {
		if (affiliated_WithDto.getDepartment() == null || affiliated_WithDto.getPhysician() == null
				|| affiliated_WithDto.getPrimaryAffiliation()) {
			throw new ValidationException("Validation failed");
		}
		try {
			Affiliated_With affiliated_With = new Affiliated_With();
			AffiliatedWithComposite compositeId = new AffiliatedWithComposite();
			Physician physician = new Physician();
			Department department = new Department();
			physician.setEmployeeId(affiliated_WithDto.getPhysician());
			department.setDepartmentId(affiliated_WithDto.getDepartment());
			compositeId.setPhysician(physician);
			compositeId.setDepartment(department);
			affiliated_With.setComposite(compositeId);
			affiliated_With.setPrimaryaffiliation(affiliated_WithDto.getPrimaryAffiliation());
			affiliatedWithRepository.save(affiliated_With);
			messageDto.setResponse("Record Created Successfully");
		} catch (Exception e) {
			throw new PhysicianNotFoundException("No Physician or Department Found!");
		}
		return messageDto;
	}

	@Override
	public List<PhysicianDto> getPhysicianByDepartmentId(Integer deptId) {
		List<Affiliated_With> findPatientByDepartmentId = affiliatedWithRepository
				.findCompositeByCompositeDepartmentDepartmentId(deptId);
		List<PhysicianDto> physicians = new ArrayList<>();

		for (Affiliated_With affiliated_With : findPatientByDepartmentId) {
			PhysicianDto physicianDto = new PhysicianDto();
			BeanUtils.copyProperties(affiliated_With.getComposite().getPhysician(), physicianDto);
			physicians.add(physicianDto);
		}
		return physicians;
	}

	@Override
	public List<DepartmentDto> getDepartmentsByPhysicianId(Integer physicianId) {
		List<Affiliated_With> findByPhysicianId = affiliatedWithRepository
				.findCompositeByCompositePhysicianEmployeeId(physicianId);
		List<DepartmentDto> departmentDtos = new ArrayList<>();

		for (Affiliated_With affiliated_With : findByPhysicianId) {
			DepartmentDto departmentDto = new DepartmentDto();
			BeanUtils.copyProperties(affiliated_With.getComposite().getDepartment(), departmentDto);
			departmentDto.setHead(affiliated_With.getComposite().getPhysician().getEmployeeId());
			departmentDtos.add(departmentDto);
		}
		return departmentDtos;
	}

	@Override
	public Integer countPhysicianByDepartment(Integer dept) {
		Integer countByCompositeDepartmentDepartmentId = affiliatedWithRepository
				.countByCompositeDepartmentDepartmentId(dept);
		return countByCompositeDepartmentDepartmentId;
	}

	@Override
	public List<Boolean> getPrimaryAffiliation(Integer physicianId) {
		List<Affiliated_With> findPrimaryaffiliationByCompositePhysicianEmployeeId = affiliatedWithRepository
				.findPrimaryaffiliationByCompositePhysicianEmployeeId(physicianId);
		List<Boolean> primaryAffiliationList = new ArrayList<>();
		for (Affiliated_With affiliated_With : findPrimaryaffiliationByCompositePhysicianEmployeeId) {
			primaryAffiliationList.add(affiliated_With.getPrimaryaffiliation());
		}
		return primaryAffiliationList;
	}

	@Override
	public Boolean updatePrimaryAffiliationByPhysicianIdAndDepartmentId(Integer physicianId, Integer deptId,
			Boolean primaryAffliation) {
		Optional<Affiliated_With> findByEmployeeIdAndDepartmentId = affiliatedWithRepository
				.findByCompositePhysicianEmployeeIdAndCompositeDepartmentDepartmentId(physicianId, deptId);
		if (findByEmployeeIdAndDepartmentId.isPresent()) {
			Affiliated_With affiliated_With = findByEmployeeIdAndDepartmentId.get();
			affiliated_With.setPrimaryaffiliation(primaryAffliation);
			affiliatedWithRepository.save(affiliated_With);
			return true;
		}
		throw new AffiliatedWithNotFoundException("No Affiliation Found with Given Physician ID and Department ID");
	}

}
