package com.hms.controller;

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

import com.hms.dto.NurseDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.service.inter.INurseService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/nurse")
public class NurseController {

	@Autowired
	private INurseService nurseService;

	@GetMapping
	public ResponseEntity<List<NurseDto>> getListOfNurse() {
		List<NurseDto> listOfNurse = nurseService.getListOfNurse();
		return new ResponseEntity<List<NurseDto>>(listOfNurse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ResponseMessageDto> addNurse(@RequestBody NurseDto nurseDto) {
		ResponseMessageDto message = nurseService.addNurse(nurseDto);
		return new ResponseEntity<ResponseMessageDto>(message, HttpStatus.OK);
	}

	@GetMapping("/{empid}")
	public ResponseEntity<NurseDto> getNurseById(@PathVariable Integer empid) {
		NurseDto nurseDetailsById = nurseService.getNurseDetailsById(empid);
		return new ResponseEntity<NurseDto>(nurseDetailsById, HttpStatus.OK);
	}

	@GetMapping("/position/{empid}")
	public ResponseEntity<String> getPositionOfNurse(@PathVariable Integer empid) {
		String positionOfNurse = nurseService.getPositionOfNurse(empid);
		return new ResponseEntity<String>(positionOfNurse, HttpStatus.OK);
	}

	@GetMapping("/registered/{empid}")
	public ResponseEntity<Boolean> isNurseRegistered(@PathVariable Integer empid) {
		Boolean nurseRegistered = nurseService.isNurseRegistered(empid);
		return new ResponseEntity<Boolean>(nurseRegistered, HttpStatus.OK);
	}

	@PutMapping("/registered/{empid}")
	public ResponseEntity<NurseDto> updateRegisteredNurse(@PathVariable Integer empid, @RequestBody NurseDto dto) {
		NurseDto updateRegistered = nurseService.updateRegistered(empid, dto);
		return new ResponseEntity<NurseDto>(updateRegistered, HttpStatus.OK);
	}

	@PutMapping("/ssn/{empid}")
	public ResponseEntity<NurseDto> updateSsnNurse(@PathVariable Integer empid, @RequestBody NurseDto dto) {
		NurseDto update = nurseService.updateSSN(empid, dto);
		return new ResponseEntity<NurseDto>(update, HttpStatus.OK);
	}

}
