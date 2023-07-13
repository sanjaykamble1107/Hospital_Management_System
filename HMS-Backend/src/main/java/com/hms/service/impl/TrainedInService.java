package com.hms.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dto.PhysicianDto;
import com.hms.dto.ProceduresDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.dto.TrainedInDto;
import com.hms.entity.Physician;
import com.hms.entity.Procedures;
import com.hms.entity.TrainedIn;
import com.hms.entity.TrainedInCompositeId;
import com.hms.exception.model.TrainedInNotFoundException;
import com.hms.exception.model.ValidationException;
import com.hms.repository.TrainedInRepository;
import com.hms.service.inter.ITrainedInService;

@Service
public class TrainedInService implements ITrainedInService {

	@Autowired
	private TrainedInRepository trainedInRepository;
	private ResponseMessageDto messageDto = new ResponseMessageDto();

	@Override
	public ResponseMessageDto addCertification(TrainedInDto trainedInDto) {
		TrainedIn trainedIn = new TrainedIn();
		Physician physician = new Physician();
		Procedures procedures = new Procedures();
		TrainedInCompositeId compositeId = new TrainedInCompositeId();
		physician.setEmployeeId(trainedInDto.getPhysician());
		procedures.setCode(trainedInDto.getTreatment());
		compositeId.setPhysician(physician);
		compositeId.setTreatment(procedures);
		BeanUtils.copyProperties(trainedInDto, trainedIn);
		trainedIn.setCompositeId(compositeId);
		trainedInRepository.save(trainedIn);
		messageDto.setResponse("Record Created Successfully");
		return messageDto;
	}

	@Override
	public List<Object> getListOfProcedure() {
		List<Object> findAll = trainedInRepository.findDistinctAll();
		return findAll;
	}

	@Override
	public List<ProceduresDto> getListOfTreatmentByPhysicianId(Integer physicianid) {
		List<TrainedIn> findById = trainedInRepository.findByCompositeIdPhysicianEmployeeId(physicianid);
		if (findById.isEmpty()) {
			throw new TrainedInNotFoundException("No Procedures Found");
		}
		List<ProceduresDto> procedureList = new ArrayList<>();
		for (TrainedIn in : findById) {
			ProceduresDto dto = new ProceduresDto();
			dto.setCode(in.getCompositeId().getTreatment().getCode());
			dto.setCost(in.getCompositeId().getTreatment().getCost());
			dto.setName(in.getCompositeId().getTreatment().getName());
			procedureList.add(dto);
		}
		return procedureList;
	}

	@Override
	public List<PhysicianDto> getListOfPhysicianByProcedureId(Integer treatmentId) {

		List<TrainedIn> findByCompositeIdTreatmentCode = trainedInRepository
				.findByCompositeIdTreatmentCode(treatmentId);
		if (findByCompositeIdTreatmentCode.isEmpty()) {
			throw new TrainedInNotFoundException("No Physician Found");
		}
		List<PhysicianDto> physicianList = new ArrayList<>();
		for (TrainedIn in : findByCompositeIdTreatmentCode) {
			PhysicianDto dto = new PhysicianDto();
			dto.setEmployeeId(in.getCompositeId().getPhysician().getEmployeeId());
			dto.setName(in.getCompositeId().getPhysician().getName());
			dto.setPosition(in.getCompositeId().getPhysician().getPosition());
			dto.setSsn(in.getCompositeId().getPhysician().getSsn());
			physicianList.add(dto);
		}
		return physicianList;

	}

	@Override
	public List<ProceduresDto> getListOfProceduresOfOneMonthExpiry() {
		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime endDate = startDate.plusMonths(1);
		List<TrainedIn> findByCertificationExpiresBetween = trainedInRepository
				.findByCertificationExpiresBetween(startDate, endDate);
		if (findByCertificationExpiresBetween.isEmpty()) {
			throw new TrainedInNotFoundException("No Procedures Found");
		}
		List<ProceduresDto> dtos = new ArrayList<>();
		for (TrainedIn in : findByCertificationExpiresBetween) {
			ProceduresDto dto = new ProceduresDto();
			dto.setCode(in.getCompositeId().getTreatment().getCode());
			dto.setCost(in.getCompositeId().getTreatment().getCost());
			dto.setName(in.getCompositeId().getTreatment().getName());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public Boolean updateCertificationExpiry(Integer physician, Integer procedure, LocalDateTime expiry) {
		Optional<TrainedIn> findByCompositeIdPhysicianEmployeeIdAndCompositeIdTreatmentCode = trainedInRepository
				.findByCompositeIdPhysicianEmployeeIdAndCompositeIdTreatmentCode(physician, procedure);
		if (findByCompositeIdPhysicianEmployeeIdAndCompositeIdTreatmentCode.isPresent()) {
			TrainedIn in = new TrainedIn();
			BeanUtils.copyProperties(findByCompositeIdPhysicianEmployeeIdAndCompositeIdTreatmentCode.get(), in);
			in.setCertificationExpires(expiry);
			trainedInRepository.save(in);
			return true;
		}
		return false;
	}
}
