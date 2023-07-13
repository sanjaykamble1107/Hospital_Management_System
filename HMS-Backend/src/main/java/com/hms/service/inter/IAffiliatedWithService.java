package com.hms.service.inter;

import java.util.List;

import com.hms.dto.Affiliated_WithDto;
import com.hms.dto.DepartmentDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;

public interface IAffiliatedWithService {

	public ResponseMessageDto saveAffiliated(Affiliated_WithDto affiliated_WithDto);

	public List<PhysicianDto> getPhysicianByDepartmentId(Integer deptId);

	public List<DepartmentDto> getDepartmentsByPhysicianId(Integer physicianId);

	public Integer countPhysicianByDepartment(Integer deptid);

	public List<Boolean> getPrimaryAffiliation(Integer physicianId);

	public Boolean updatePrimaryAffiliationByPhysicianIdAndDepartmentId(Integer physicianId, Integer deptId,
			Boolean primaryAffliation);

}
