package com.hms.service.inter;

import java.time.LocalDateTime;
import java.util.List;

import com.hms.dto.PhysicianDto;
import com.hms.dto.ProceduresDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.dto.TrainedInDto;

public interface ITrainedInService {

	public ResponseMessageDto addCertification(TrainedInDto trainedInDto);

	public List<Object> getListOfProcedure();

	public List<ProceduresDto> getListOfTreatmentByPhysicianId(Integer physicianId);

	public List<PhysicianDto> getListOfPhysicianByProcedureId(Integer treatment);

	public List<ProceduresDto> getListOfProceduresOfOneMonthExpiry();

	public Boolean updateCertificationExpiry(Integer physician, Integer procedure, LocalDateTime expiry);
}
