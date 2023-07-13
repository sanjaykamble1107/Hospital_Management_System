package com.hms.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.PhysicianDto;
import com.hms.dto.ProceduresDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.dto.TrainedInDto;
import com.hms.service.inter.ITrainedInService;

@CrossOrigin(origins = "http://localhost:4200")//Allow cross-origin request from http://localhost:4200
@RestController
@RequestMapping("/trained_in")//Base path for all end points in this controller
public class TrainedInController {

	@Autowired  //Autowires the trainedService bean 
	private ITrainedInService trainedService;

	@PostMapping //POST request to/trained_in
	public ResponseEntity<ResponseMessageDto> addCertification(@RequestBody TrainedInDto trainedInDto) {
		ResponseMessageDto message = trainedService.addCertification(trainedInDto);
		return new ResponseEntity<ResponseMessageDto>(message, HttpStatus.OK);
	}

	@GetMapping("/")  // Handle GET requests to /trained_in/
	public ResponseEntity<List<Object>> getListOfProcedure() {
		 // Get the list of procedure using trainedService
		List<Object> listOfProcedure = trainedService.getListOfProcedure();
		//Returns list of procedure response with HTTP status 200 (OK)
		return new ResponseEntity<List<Object>>(listOfProcedure, HttpStatus.OK);
	}

	@GetMapping("/treatment/{physicianid}") // Handle GET requests to /treatment/{physicianid}
	public ResponseEntity<List<ProceduresDto>> getListofTreatmentByPhysicianId(@PathVariable Integer physicianid) {
		//GET list of  Treatment by physician Id  using trainedService
		List<ProceduresDto> listOfTreatmentByPhysicianId = trainedService.getListOfTreatmentByPhysicianId(physicianid);
		//Returns list of treatment by physicianId response with HTTP status 200 (OK)
		return new ResponseEntity<List<ProceduresDto>>(listOfTreatmentByPhysicianId, HttpStatus.OK);
	}

	@GetMapping("/physician/{procedureid}")  // Handle GET requests to/physician/{procedureid}
	public ResponseEntity<List<PhysicianDto>> getListofPhysicianByProcedureId(@PathVariable Integer procedureid) {
		//GET list of physician by procedureId using trainedService 
		List<PhysicianDto> listOfPhysicianByProcedureId = trainedService.getListOfPhysicianByProcedureId(procedureid);
		//Returns list of physician by procedureId  response with HTTP status 200 (OK) 
		return new ResponseEntity<List<PhysicianDto>>(listOfPhysicianByProcedureId, HttpStatus.OK);
	}

	@GetMapping("/expiredsooncerti")  // Handle GET requests to/expiredsooncerti
	public ResponseEntity<List<ProceduresDto>> getListOfProceduresExpiredInMonth() {
		//GET list of procedure Expire In Month
		List<ProceduresDto> listOfProceduresOfOneMonthExpiry = trainedService.getListOfProceduresOfOneMonthExpiry();
		//Returns list of Procedure Expires in Month response with HTTP status 200 (OK) 
		return new ResponseEntity<List<ProceduresDto>>(listOfProceduresOfOneMonthExpiry, HttpStatus.OK);
	}

	@PutMapping("/certificationexpiry/{physicianid}/{procedureid}") // Handle PUT requests to/certificationexpiry/{physicianid}/{procedureid}
	public ResponseEntity<Boolean> updateCertification(@PathVariable Integer physicianid,
			@PathVariable Integer procedureid, @RequestBody LocalDateTime expiry) {
		//Update certification by physicianId & procedureId using trainedService 
		Boolean updateCertificationExpiry = trainedService.updateCertificationExpiry(physicianid, procedureid, expiry);
		//Returns updated certification response  response with HTTP status 200 (OK) 
		return new ResponseEntity<Boolean>(updateCertificationExpiry, HttpStatus.OK);
	}
}
