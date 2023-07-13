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
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.service.impl.PhysicianService;

@CrossOrigin(origins = "http://localhost:4200") //Allow cross-origin request from http://localhost:4200
@RestController
@RequestMapping("/physician") //Base path for all end points in this controller
public class PhysicianController {

	@Autowired //Autowires the physicianService bean 
	private PhysicianService physicianService;

	@PostMapping //Handle POST request to /physician 
	public ResponseEntity<ResponseMessageDto> addPhysician(@RequestBody @Validated PhysicianDto physicianDto) {
		ResponseMessageDto messageDto = physicianService.addPhysician(physicianDto);
		return new ResponseEntity<ResponseMessageDto>(messageDto, HttpStatus.OK);
	}

	@GetMapping // Extra EndPoint   //Handle GET request to /physician 
	public ResponseEntity<List<PhysicianDto>> getListOfPhysicianDto() {
		 // Get the list of physician objects using  physicianService 
		List<PhysicianDto> employeeDto = physicianService.getListOfPhysicianDto();
		//Returns list of physician response with HTTP status 200 (OK)
		return new ResponseEntity<List<PhysicianDto>>(employeeDto, HttpStatus.OK);
	}

	@GetMapping("/name/{name}") //Handle GET request to /name/{name}
	public ResponseEntity<PhysicianDto> getPhysicianByName(@PathVariable String name) {
		//Get physician details by name using physicianservice
		PhysicianDto employeeDto = physicianService.getPhysicianByName(name);
		//Returns physician response with HTTP status 200 (OK)
		return new ResponseEntity<PhysicianDto>(employeeDto, HttpStatus.OK);
	}

	@GetMapping("/position/{position}") //Handle GET request to /position/{position}
	public ResponseEntity<List<PhysicianDto>> getPhysicianByPosition(@PathVariable String position) {
		//GET list physician details by position using  physicianservice
		List<PhysicianDto> physicianDto = physicianService.getPhysicianByPosition(position);
		//Return physician response with HTTP status 200 (OK)
		return new ResponseEntity<List<PhysicianDto>>(physicianDto, HttpStatus.OK);
	}

	@GetMapping("/{employeeId}") //Handle GET request to /{employeeId}
	public ResponseEntity<PhysicianDto> getPhysicianByEmpid(@PathVariable Integer employeeId) {
		//GET Physician details by EmployeeId using physicianservice
		PhysicianDto physicianByEmpid = physicianService.getPhysicianByEmpid(employeeId);
		//Return physician response with HTTP status 200 (OK)
		return new ResponseEntity<PhysicianDto>(physicianByEmpid, HttpStatus.OK);
	}

	@PutMapping("/update/name/{employeeId}")//Handle PUT request to /update/name/{employeeId}
	public ResponseEntity<PhysicianDto> updatePhysicianName(@PathVariable Integer employeeId,
			@RequestBody @Validated PhysicianDto physicianDto) {
		//Update Physician name by EmployeeId using physicianservice
		PhysicianDto updatePhysicianName = physicianService.updatePhysicianName(employeeId, physicianDto);
		//Return updated physician name response with HTTP status 200(OK)
		return new ResponseEntity<PhysicianDto>(updatePhysicianName, HttpStatus.OK);
	}

	@PutMapping("/update/position/{employeeId}")//Handle PUT request to /update/position/{employeeId}
	public ResponseEntity<PhysicianDto> updatePhysicianPosition(@PathVariable Integer employeeId,
			@RequestBody @Validated PhysicianDto physicianDto) {
		//Update physician position by employeeId using physicianservice
		PhysicianDto updatePhysicianPosition = physicianService.updatePhysicianPosition(employeeId, physicianDto);
		//Return updated physician position response with HTTP status 200(OK)
		return new ResponseEntity<PhysicianDto>(updatePhysicianPosition, HttpStatus.OK);
	}

	@PutMapping("/update/ssn/{employeeId}")//Handle PUT request to /update/ssn/{employeeId}
	public ResponseEntity<PhysicianDto> updateSsnOfPhysician(@PathVariable Integer employeeId,
			@RequestBody PhysicianDto physicianDto) {
		//Update Ssn of physician by employeeId using physicianservice
		PhysicianDto updateSsnOfPhysician = physicianService.updateSsnOfPhysician(employeeId, physicianDto);
		//Return updated physician ssn response with HTTP status 200(OK)
		return new ResponseEntity<PhysicianDto>(updateSsnOfPhysician, HttpStatus.OK);
	}

}
