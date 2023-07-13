package com.hms.service.inter;

import java.util.List;

import com.hms.dto.NurseDto;
import com.hms.dto.ResponseMessageDto;

public interface INurseService {

	public ResponseMessageDto addNurse(NurseDto nurseDto);

	public List<NurseDto> getListOfNurse();

	public NurseDto getNurseDetailsById(Integer employeeId);

	public String getPositionOfNurse(Integer employeeId);

	public Boolean isNurseRegistered(Integer employeeId);

	public NurseDto updateRegistered(Integer employeeId, NurseDto dto);

	public NurseDto updateSSN(Integer employeeId, NurseDto dto);
}
