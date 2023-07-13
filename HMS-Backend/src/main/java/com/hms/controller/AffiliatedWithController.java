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

import com.hms.dto.Affiliated_WithDto;
import com.hms.dto.DepartmentDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.service.inter.IAffiliatedWithService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/affiliated_with")
public class AffiliatedWithController {

	@Autowired
	private IAffiliatedWithService affiliatedWithService;

	@PostMapping("/post")
	public ResponseEntity<ResponseMessageDto> saveAffiliatedWith(@RequestBody Affiliated_WithDto affiliated_WithDto) {
		ResponseMessageDto saveAffiliated = affiliatedWithService.saveAffiliated(affiliated_WithDto);
		return new ResponseEntity<ResponseMessageDto>(saveAffiliated, HttpStatus.OK);
	}

	@GetMapping("/physicians/{deptid}")
	public ResponseEntity<List<PhysicianDto>> getPhysicianListByDepartmentId(@PathVariable Integer deptid) {
		List<PhysicianDto> physicianByDepartmentId = affiliatedWithService.getPhysicianByDepartmentId(deptid);
		return new ResponseEntity<List<PhysicianDto>>(physicianByDepartmentId, HttpStatus.OK);
	}

	@GetMapping("/department/{physicianid}")
	public ResponseEntity<List<DepartmentDto>> getDepartmentListByPhysicianId(@PathVariable Integer physicianid) {
		List<DepartmentDto> departmentList = affiliatedWithService.getDepartmentsByPhysicianId(physicianid);
		return new ResponseEntity<List<DepartmentDto>>(departmentList, HttpStatus.OK);
	}

	@GetMapping("/countphysician/{deptid}")
	public ResponseEntity<Integer> countPhysicianByDepartment(@PathVariable Integer deptid) {
		Integer countPhysicianByDepartment = affiliatedWithService.countPhysicianByDepartment(deptid);
		return new ResponseEntity<Integer>(countPhysicianByDepartment, HttpStatus.OK);
	}

	@GetMapping("/primary/{physicianid}")
	public ResponseEntity<List<Boolean>> getPrimaryAffilationByPhysicianId(@PathVariable Integer physicianid) {
		List<Boolean> primaryAffiliation = affiliatedWithService.getPrimaryAffiliation(physicianid);
		return new ResponseEntity<List<Boolean>>(primaryAffiliation, HttpStatus.OK);
	}

	@PutMapping("/primary/{physicianid}/{deptid}")
	public ResponseEntity<Boolean> updatePrimaryAffiliation(@PathVariable Integer physicianid,
			@PathVariable Integer deptid, @RequestBody Boolean primary) {
		Boolean updatePrimaryAffiliationByPhysicianIdAndDepartmentId = affiliatedWithService
				.updatePrimaryAffiliationByPhysicianIdAndDepartmentId(physicianid, deptid, primary);
		return new ResponseEntity<Boolean>(updatePrimaryAffiliationByPhysicianIdAndDepartmentId, HttpStatus.OK);
	}

}
