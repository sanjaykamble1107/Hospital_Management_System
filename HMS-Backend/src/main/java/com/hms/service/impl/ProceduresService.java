package com.hms.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.hms.dto.ProceduresDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.entity.Procedures;
import com.hms.exception.model.ProcedureNotFoundException;
import com.hms.repository.ProceduresRepository;
import com.hms.service.inter.IProceduresService;

@Service
@Component
public class ProceduresService implements IProceduresService {

	@Autowired
	private ProceduresRepository repository;
	private ResponseMessageDto messageDto = new ResponseMessageDto();

	@Override
	public ResponseMessageDto addNewTreatment(ProceduresDto procedureDto) {
		Procedures procedures = new Procedures();
		BeanUtils.copyProperties(procedureDto, procedures);
		repository.save(procedures);
		messageDto.setResponse("Record Created Successfully");
		return messageDto;
	}

	@Override
	public List<ProceduresDto> getListOfAvailableTreatment() {
		List<Procedures> listOfprocedures = repository.findAll();

		List<ProceduresDto> proceduresDtos = new ArrayList<>();
		for (Procedures procedures : listOfprocedures) {
			ProceduresDto dto = new ProceduresDto();
			BeanUtils.copyProperties(procedures, dto);
			proceduresDtos.add(dto);
		}
		return proceduresDtos;
	}

	@Override
	public ProceduresDto getCostOfProcedureById(Integer code) {
		Optional<Procedures> findById = repository.findById(code);
		if (findById.isPresent()) {
			ProceduresDto proceduresDto = new ProceduresDto();
			BeanUtils.copyProperties(findById.get(), proceduresDto);
			return proceduresDto;
		}
		throw new ProcedureNotFoundException("No Procedure Found");
	}

	@Override
	public ProceduresDto getCostOfProcedureByName(String name) {
		Optional<Procedures> byName = repository.findCostOfProceduresByName(name);
		if (byName.isPresent()) {
			ProceduresDto proceduresDto = new ProceduresDto();
			BeanUtils.copyProperties(byName.get(), proceduresDto);
			return proceduresDto;
		}
		throw new ProcedureNotFoundException("No Procedure Found");
	}

	@Override
	public ProceduresDto updateCostOfProcedureById(Integer code, ProceduresDto proceduresDto) {
		Optional<Procedures> findById = repository.findById(code);
		if (findById.isPresent()) {
			ProceduresDto dto = new ProceduresDto();
			Procedures procedures = new Procedures();
			findById.get().setCost(proceduresDto.getCost());
			BeanUtils.copyProperties(findById.get(), dto);
			BeanUtils.copyProperties(dto, procedures);
			repository.save(procedures);
			return dto;
		}
		throw new ProcedureNotFoundException("No Procedure Found");
	}

	@Override
	public ProceduresDto updateNameOfProcedureById(Integer code, ProceduresDto proceduresDto) {
		Optional<Procedures> findById = repository.findById(code);
		if (findById.isPresent()) {
			ProceduresDto dto = new ProceduresDto();
			Procedures procedures = new Procedures();
			findById.get().setName(proceduresDto.getName());
			BeanUtils.copyProperties(findById.get(), dto);
			BeanUtils.copyProperties(dto, procedures);
			repository.save(procedures);
			return dto;
		}
		throw new ProcedureNotFoundException("No Procedure Found");
	}

	@Override
	public ProceduresDto findByCode(Integer code) {
		Optional<Procedures> findById = repository.findById(code);
		if (findById.isPresent()) {
			ProceduresDto dto = new ProceduresDto();
			BeanUtils.copyProperties(findById.get(), dto);
			return dto;
		}
		throw new ProcedureNotFoundException("No Procedure Found");
	}
}
