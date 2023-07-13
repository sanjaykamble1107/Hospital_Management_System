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
import com.hms.dto.ProceduresDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.service.inter.IProceduresService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/procedure")
public class ProceduresController {

	@Autowired
	private IProceduresService procedureservice;

	@PostMapping
	public ResponseEntity<ResponseMessageDto> addNewTreatment(@RequestBody ProceduresDto proceduresDto) {
		ResponseMessageDto message = procedureservice.addNewTreatment(proceduresDto);
		return new ResponseEntity<ResponseMessageDto>(message, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ProceduresDto>> getListOfAvailableTreatment() {
		List<ProceduresDto> listOfTreatment = procedureservice.getListOfAvailableTreatment();
		return new ResponseEntity<List<ProceduresDto>>(listOfTreatment, HttpStatus.OK);
	}

	@GetMapping("/costofprocedure/{id}")
	public ResponseEntity<ProceduresDto> getCostOfProcedureById(@PathVariable Integer id) {
		ProceduresDto proceduresById = procedureservice.getCostOfProcedureById(id);
		return new ResponseEntity<ProceduresDto>(proceduresById, HttpStatus.OK);
	}

	@GetMapping("/cost/{name}")
	public ResponseEntity<ProceduresDto> getCostOfProcedureByName(@PathVariable String name) {
		ProceduresDto proceduresById = procedureservice.getCostOfProcedureByName(name);
		return new ResponseEntity<ProceduresDto>(proceduresById, HttpStatus.OK);
	}

	@PutMapping("/cost/{id}")
	public ResponseEntity<ProceduresDto> updateCostOfProcedureById(@PathVariable Integer id,
			@RequestBody ProceduresDto procedureDto) {

		ProceduresDto procedures = procedureservice.updateCostOfProcedureById(id, procedureDto);
		return new ResponseEntity<ProceduresDto>(procedures, HttpStatus.OK);
	}

	@PutMapping("/name/{id}")
	public ResponseEntity<ProceduresDto> updateNameOfProcedureById(@PathVariable Integer id,
			@RequestBody ProceduresDto procedureDto) {

		ProceduresDto procedures = procedureservice.updateNameOfProcedureById(id, procedureDto);
		return new ResponseEntity<ProceduresDto>(procedures, HttpStatus.OK);
	}

	@GetMapping("/{code}")
	public ResponseEntity<ProceduresDto> getByCode(@PathVariable Integer code) {
		ProceduresDto findByCode = procedureservice.findByCode(code);
		return new ResponseEntity<ProceduresDto>(findByCode, HttpStatus.OK);
	}

}
