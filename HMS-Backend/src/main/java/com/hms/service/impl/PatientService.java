package com.hms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dto.PatientDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.entity.Patient;
import com.hms.entity.Physician;
import com.hms.exception.model.PatientNotFoundException;
import com.hms.exception.model.ValidationException;
import com.hms.repository.PatientRepository;
import com.hms.service.inter.IPatientService;

@Service
public class PatientService implements IPatientService {

	@Autowired
	private PatientRepository repository;

	private ResponseMessageDto messageDto = new ResponseMessageDto();

	@Override
	public ResponseMessageDto addPatient(PatientDto patientdto) {
		Patient patient = new Patient();
		Physician physician = new Physician();
		physician.setEmployeeId(patientdto.getPcp());
		BeanUtils.copyProperties(patientdto, patient);
		patient.setPcp(physician);
		repository.save(patient);
		messageDto.setResponse("Record Created Successfully");
		return messageDto;
	}

	@Override
	public List<PatientDto> getAllPatient() {
		List<Patient> list = repository.findAll();
		if (list.isEmpty()) {
			throw new PatientNotFoundException("No Patient Found!");
		}
		List<PatientDto> dtos = new ArrayList<>();
		for (Patient pat : list) {
			PatientDto dto = new PatientDto();
			dto.setPcp(pat.getPcp().getEmployeeId());
			BeanUtils.copyProperties(pat, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public PatientDto getInsuranceIdByPatient(Integer ssn) {
		Optional<Patient> findById = repository.findById(ssn);
		if (findById.isPresent()) {
			PatientDto dto = new PatientDto();
			BeanUtils.copyProperties(findById.get(), dto);
			dto.setPcp(findById.get().getPcp().getEmployeeId());
			return dto;
		}
		throw new PatientNotFoundException("No Patient Found!");
	}

	@Override
	public PatientDto updateAddressOfPatient(Integer ssn, PatientDto patientDto) {
		Optional<Patient> findById = repository.findById(ssn);
		if (findById.isPresent()) {
			PatientDto dto = new PatientDto();
			Patient patient = new Patient();
			findById.get().setAddress(patientDto.getAddress());
			BeanUtils.copyProperties(findById.get(), dto);
			BeanUtils.copyProperties(dto, patient);
			patient.setPcp(findById.get().getPcp());
			repository.save(patient);
			dto.setPcp(patient.getPcp().getEmployeeId());
			return dto;
		}
		throw new PatientNotFoundException("No Patient Found!");
	}

	@Override
	public PatientDto updatePhoneOfPatient(Integer ssn, PatientDto patientDto) {
		Optional<Patient> findById = repository.findById(ssn);
		if (findById.isPresent()) {
			PatientDto dto = new PatientDto();
			Patient patient = new Patient();
			findById.get().setPhone(patientDto.getPhone());
			BeanUtils.copyProperties(findById.get(), dto);
			BeanUtils.copyProperties(dto, patient);
			patient.setPcp(findById.get().getPcp());
			repository.save(patient);
			dto.setPcp(patient.getPcp().getEmployeeId());
			return dto;
		}
		throw new PatientNotFoundException("No Patient Found!");
	}

	@Override
	public List<PatientDto> getPatientsByPhysicianId(Integer pcp) {
		List<Patient> findPatientByPcp = repository.findPatientByPcpEmployeeId(pcp);
		if (findPatientByPcp.isEmpty()) {
			throw new PatientNotFoundException("No Patient Found!");
		}
		List<PatientDto> dtos = new ArrayList<>();
		for (Patient pat : findPatientByPcp) {
			PatientDto dto = new PatientDto();
			dto.setPcp(pat.getPcp().getEmployeeId());
			BeanUtils.copyProperties(pat, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public PatientDto getPatientBySsnAndPhysicianId(Integer ssn, Integer pcp) {
		Optional<Patient> findPatientBySsnAndPcp = repository.findPatientBySsnAndPcpEmployeeId(ssn, pcp);

		if (findPatientBySsnAndPcp.isPresent()) {
			PatientDto dto = new PatientDto();
			dto.setPcp(findPatientBySsnAndPcp.get().getPcp().getEmployeeId());
			BeanUtils.copyProperties(findPatientBySsnAndPcp.get(), dto);
			return dto;
		}
		throw new PatientNotFoundException("No Patient Found!");
	}

	@Override
	public PatientDto findBySSN(Integer ssn) {
		Optional<Patient> findById = repository.findById(ssn);
		PatientDto dto = new PatientDto();
		if (findById.isPresent()) {
			Patient patient = new Patient();
			BeanUtils.copyProperties(findById.get(), patient);
			BeanUtils.copyProperties(patient, dto);
			dto.setPcp(patient.getPcp().getEmployeeId());
			return dto;
		}
		throw new PatientNotFoundException("No Patient Found!");
	}

}
