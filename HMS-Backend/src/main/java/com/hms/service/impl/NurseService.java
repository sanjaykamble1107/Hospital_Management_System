package com.hms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hms.dto.NurseDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.entity.Nurse;
import com.hms.exception.model.NurseNotFoundException;
import com.hms.exception.model.ValidationException;
import com.hms.repository.NurseRepository;
import com.hms.service.inter.INurseService;

@Service
@Component
public class NurseService implements INurseService {

	@Autowired
	private NurseRepository nurseRepository;

	private ResponseMessageDto messageDto = new ResponseMessageDto();

	@Override
	public List<NurseDto> getListOfNurse() {
		List<Nurse> listOfNurse = nurseRepository.findAll();

		List<NurseDto> nurseDtos = new ArrayList<>();
		for (Nurse nurse : listOfNurse) {
			NurseDto dto = new NurseDto();
			BeanUtils.copyProperties(nurse, dto);
			nurseDtos.add(dto);
		}
		return nurseDtos;
	}

	@Override
	public ResponseMessageDto addNurse(NurseDto nurseDto) {
		Nurse nurse = new Nurse();
		BeanUtils.copyProperties(nurseDto, nurse);
		nurseRepository.save(nurse);
		messageDto.setResponse("Record Created Successfully");
		return messageDto;
	}

	@Override
	public NurseDto getNurseDetailsById(Integer employeeId) {
		Optional<Nurse> findById = nurseRepository.findById(employeeId);
		if (findById.isPresent()) {
			NurseDto nurseDto = new NurseDto();
			BeanUtils.copyProperties(findById.get(), nurseDto);
			return nurseDto;
		}
		throw new NurseNotFoundException("Nurse Not Found with id: " + employeeId);
	}

	@Override
	public String getPositionOfNurse(Integer employeeId) {
		Optional<Nurse> findById = nurseRepository.findById(employeeId);
		if (findById.isPresent()) {
			Nurse nurse = findById.get();
			return nurse.getPosition();
		}
		throw new NurseNotFoundException("Nurse Not Found with id: " + employeeId);
	}

	@Override
	public Boolean isNurseRegistered(Integer employeeId) {
		boolean existsByRegisteredIsTrueAndEmployeeId = nurseRepository
				.existsByRegisteredIsTrueAndEmployeeId(employeeId);
		return existsByRegisteredIsTrueAndEmployeeId;
	}

	@Override
	public NurseDto updateRegistered(Integer employeeId, NurseDto dto) {
		Optional<Nurse> findById = nurseRepository.findById(employeeId);
		if (findById.isPresent()) {
			Nurse nurse = new Nurse();
			NurseDto nurseDto = new NurseDto();
			findById.get().setRegistered(dto.getRegistered());
			BeanUtils.copyProperties(findById.get(), nurseDto);
			BeanUtils.copyProperties(nurseDto, nurse);
			System.out.println(nurse);
			nurseRepository.save(nurse);
			return nurseDto;
		}
		throw new NurseNotFoundException("Nurse Not Found with id: " + employeeId);
	}

	@Override
	public NurseDto updateSSN(Integer employeeId, NurseDto dto) {
		Optional<Nurse> findById = nurseRepository.findById(employeeId);
		if (findById.isPresent()) {
			Nurse nurse = new Nurse();
			NurseDto nurseDto = new NurseDto();
			findById.get().setSsn(dto.getSsn());
			BeanUtils.copyProperties(findById.get(), nurseDto);
			BeanUtils.copyProperties(nurseDto, nurse);
			System.out.println(nurse);
			nurseRepository.save(nurse);
			return nurseDto;
		}
		throw new NurseNotFoundException("Nurse Not Found with id: " + employeeId);
	}

}
