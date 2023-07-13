package com.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.PatientDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.service.inter.IPatientService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private IPatientService service;
	
	@PostMapping
	public ResponseEntity<ResponseMessageDto> addPatient(@RequestBody @Validated PatientDto patientDto) {
		ResponseMessageDto message = service.addPatient(patientDto);
		return new ResponseEntity<ResponseMessageDto>(message, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<PatientDto>> getAllPatient() {
		List<PatientDto> patient = service.getAllPatient();
		return new ResponseEntity<List<PatientDto>>(patient, HttpStatus.OK);
	}

	@GetMapping("/insurance/{patientid}")
	public ResponseEntity<PatientDto> getInsuranceIdByPatient(@PathVariable Integer patientid) {
		PatientDto patientDto = service.getInsuranceIdByPatient(patientid);
		return new ResponseEntity<PatientDto>(patientDto, HttpStatus.OK);
	}

	@PutMapping("/address/{ssn}")
	public ResponseEntity<PatientDto> updateAddressOfPatient(@PathVariable Integer ssn, @RequestBody @Validated PatientDto patientDto) {

		PatientDto patient = service.updateAddressOfPatient(ssn, patientDto);
		return new ResponseEntity<PatientDto>(patient, HttpStatus.OK);
	}

	@PutMapping("/phone/{ssn}")
	public ResponseEntity<PatientDto> updatePhoneOfPatient(@PathVariable Integer ssn, @RequestBody PatientDto patientDto) {
		PatientDto patient = service.updatePhoneOfPatient(ssn, patientDto);
		return new ResponseEntity<PatientDto>(patient, HttpStatus.OK);
	}

	@GetMapping("/{physicianid}")
	public ResponseEntity<List<PatientDto>> getPatientsByPhysicianId(@PathVariable Integer physicianid) {
		List<PatientDto> patientsByPhysicianId = service.getPatientsByPhysicianId(physicianid);
		return new ResponseEntity<List<PatientDto>>(patientsByPhysicianId, HttpStatus.OK);
	}

	@GetMapping("/{physicianid}/{patientid}")
	public ResponseEntity<PatientDto> getPatientBySsnAndPhysicianId(@PathVariable Integer physicianid,
			@PathVariable Integer patientid) {
		PatientDto patientBySsnAndPhysicianId = service.getPatientBySsnAndPhysicianId(patientid, physicianid);
		return new ResponseEntity<PatientDto>(patientBySsnAndPhysicianId, HttpStatus.OK);
	}

	@GetMapping("/SSN/{patientid}")
	public ResponseEntity<PatientDto> getPatientBySSN(@PathVariable Integer patientid) {
		PatientDto patientDto = service.findBySSN(patientid);
		return new ResponseEntity<PatientDto>(patientDto, HttpStatus.OK);
	}
}
