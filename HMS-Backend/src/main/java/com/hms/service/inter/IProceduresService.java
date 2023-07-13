package com.hms.service.inter;

import java.util.List;

import com.hms.dto.ProceduresDto;
import com.hms.dto.ResponseMessageDto;

public interface IProceduresService {

	public ResponseMessageDto addNewTreatment(ProceduresDto procedureDto);

	public List<ProceduresDto> getListOfAvailableTreatment();

	public ProceduresDto getCostOfProcedureById(Integer code);

	public ProceduresDto getCostOfProcedureByName(String name);

	public ProceduresDto updateCostOfProcedureById(Integer code, ProceduresDto proceduresDto);

	public ProceduresDto updateNameOfProcedureById(Integer code, ProceduresDto proceduresDto);

	public ProceduresDto findByCode(Integer code);
}