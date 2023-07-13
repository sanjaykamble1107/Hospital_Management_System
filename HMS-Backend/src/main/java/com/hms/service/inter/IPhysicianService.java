package com.hms.service.inter;

import java.util.List;

import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;


public interface IPhysicianService {

	public List<PhysicianDto> getListOfPhysicianDto();		//Extra EndPoint
	public ResponseMessageDto addPhysician(PhysicianDto physicianDto );
	public PhysicianDto getPhysicianByName(String name);
	public List<PhysicianDto> getPhysicianByPosition(String position);
	public PhysicianDto getPhysicianByEmpid(Integer employeeId);
	public PhysicianDto updatePhysicianPosition(Integer employeeId, PhysicianDto physicianDto);
	public PhysicianDto updatePhysicianName(Integer employeeId, PhysicianDto physicianDto);
	public PhysicianDto updateSsnOfPhysician(Integer employeeId, PhysicianDto physicianDto);

}
