package com.hms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.entity.Physician;
import com.hms.exception.model.PhysicianNotFoundException;
import com.hms.repository.PhysicianRepository;
import com.hms.service.inter.IPhysicianService;

@Service
public class PhysicianService implements IPhysicianService {

	@Autowired
	private PhysicianRepository physicianRepository;

	private ResponseMessageDto messageDto = new ResponseMessageDto();

	@Override
	public ResponseMessageDto addPhysician(PhysicianDto physicianDto) {
		Physician physician = new Physician();
		BeanUtils.copyProperties(physicianDto, physician);
		physicianRepository.save(physician);
		messageDto.setResponse("Record Created Successfully");
		return messageDto;
	}

	@Override
	public PhysicianDto getPhysicianByName(String name) {

		Optional<Physician> byName = physicianRepository.findPhysicianByName(name);
		if (byName.isPresent()) {
			PhysicianDto physicianDto = new PhysicianDto();
			BeanUtils.copyProperties(byName.get(), physicianDto);
			return physicianDto;
		}
		throw new PhysicianNotFoundException("No Physician Found");
	}

	@Override
	public List<PhysicianDto> getPhysicianByPosition(String position) {
		List<Physician> findPhysicianByPosition = physicianRepository.findPhysicianByPosition(position);
		if (findPhysicianByPosition.isEmpty()) {
			throw new PhysicianNotFoundException("No Physician Found");
		}
		List<PhysicianDto> dtos = new ArrayList<>();
		for (Physician phy : findPhysicianByPosition) {
			PhysicianDto physicianDto = new PhysicianDto();
			BeanUtils.copyProperties(phy, physicianDto);
			dtos.add(physicianDto);
		}
		return dtos;
	}

	@Override
	public PhysicianDto getPhysicianByEmpid(Integer employeeId) {

		Optional<Physician> findById = physicianRepository.findById(employeeId);
		if (findById.isPresent()) {

			PhysicianDto physicianDto = new PhysicianDto();
			BeanUtils.copyProperties(findById.get(), physicianDto);
			return physicianDto;
		}
		throw new PhysicianNotFoundException("No Physician Found");
	}

	@Override
	public PhysicianDto updatePhysicianPosition(Integer employeeId, PhysicianDto physicianDto) {
		Optional<Physician> findById = physicianRepository.findById(employeeId);
		if (findById.isPresent()) {
			Physician physician = new Physician();
			PhysicianDto dto = new PhysicianDto();
			findById.get().setPosition(physicianDto.getPosition());
			BeanUtils.copyProperties(findById.get(), dto);
			BeanUtils.copyProperties(dto, physician);
			physicianRepository.save(physician);
			return dto;
		}
		throw new PhysicianNotFoundException("No Physician Found");
	}

	@Override
	public PhysicianDto updatePhysicianName(Integer employeeId, PhysicianDto physicianDto) {
		Optional<Physician> findById = physicianRepository.findById(employeeId);
		if (findById.isPresent()) {
			Physician physician = new Physician();
			PhysicianDto dto = new PhysicianDto();
			findById.get().setName(physicianDto.getName());
			BeanUtils.copyProperties(findById.get(), dto);
			BeanUtils.copyProperties(dto, physician);
			physicianRepository.save(physician);
			return dto;
		}
		throw new PhysicianNotFoundException("No Physician Found");
	}

	@Override
	public PhysicianDto updateSsnOfPhysician(Integer employeeId, PhysicianDto physicianDto) {
		Optional<Physician> findById = physicianRepository.findById(employeeId);
		if (findById.isPresent()) {
			Physician physician = new Physician();
			PhysicianDto dto = new PhysicianDto();
			findById.get().setSsn(physicianDto.getSsn());
			BeanUtils.copyProperties(findById.get(), dto);
			BeanUtils.copyProperties(dto, physician);
			physicianRepository.save(physician);
			return dto;
		}
		throw new PhysicianNotFoundException("No Physician Found");
	}

	@Override // Extra EndPoint
	public List<PhysicianDto> getListOfPhysicianDto() {

		List<Physician> listOfPhysician = physicianRepository.findAll();
		List<PhysicianDto> physicianDto = new ArrayList<>();
		for (Physician phy : listOfPhysician) {
			PhysicianDto phyDto = new PhysicianDto();
			BeanUtils.copyProperties(phy, phyDto);
			physicianDto.add(phyDto);
		}
		return physicianDto;
	}

}
