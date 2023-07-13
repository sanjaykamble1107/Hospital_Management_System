package com.hms.service.inter;

import java.util.List;

import com.hms.dto.PatientDto;
import com.hms.dto.ResponseMessageDto;

public interface IPatientService {

	public ResponseMessageDto addPatient(PatientDto patientdto);

	public List<PatientDto> getAllPatient();

	public List<PatientDto> getPatientsByPhysicianId(Integer pcp);

	public PatientDto getPatientBySsnAndPhysicianId(Integer ssn, Integer pcp);

	public PatientDto getInsuranceIdByPatient(Integer ssn);

	public PatientDto updateAddressOfPatient(Integer ssn, PatientDto patientDto);

	public PatientDto updatePhoneOfPatient(Integer ssn, PatientDto patientDto);

	public PatientDto findBySSN(Integer ssn);
}
