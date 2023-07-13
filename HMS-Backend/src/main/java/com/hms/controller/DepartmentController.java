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

import com.hms.dto.DepartmentDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.service.impl.DepartmentService;

@CrossOrigin(origins = "http://localhost:4200") //Allow cross-origin request from http://localhost:4200
@RestController
@RequestMapping("/department")   //Base path for all end points in this controller
public class DepartmentController {

	@Autowired //Autowires the departmentService bean 
	private DepartmentService departmentService;

	@PostMapping  //Handle POST request to /department 
	public ResponseEntity<ResponseMessageDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
		ResponseMessageDto message = departmentService.addDepartment(departmentDto);
		return new ResponseEntity<ResponseMessageDto>(message, HttpStatus.OK);
	}

	@GetMapping("/") // Handle GET requests to /department/
	public ResponseEntity<List<DepartmentDto>> getDepartments() {
		 // Get the list of DepartmentDto objects by department using department service
		List<DepartmentDto> listOfDepartment = departmentService.getDepartments();
		//Returns list of departments response with with HTTP status 200 (OK)
		return new ResponseEntity<List<DepartmentDto>>(listOfDepartment, HttpStatus.OK);

	}

	@GetMapping("/{departmentId}") //Handle GET requests to /department/{departmentId}
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Integer departmentId) {
		//GET the department object by Id using department service
		DepartmentDto departmentById = departmentService.getDepartmentById(departmentId);
        //Return department response with HTTP status 200 (OK)
		return new ResponseEntity<DepartmentDto>(departmentById, HttpStatus.OK);

	}

	@GetMapping("/dept/head/{departmentId}")//Handle GET requests to/dept/head/{departmentId}
	public ResponseEntity<PhysicianDto> getHeadOfDepartment(@PathVariable Integer departmentId) {
		//GET the departmenthead object by departmentId  using departmentService
		PhysicianDto headByDepartmentId = departmentService.getHeadOfDepartment(departmentId);
		//Return Head of department with HTTP  status 200 (OK)
		return new ResponseEntity<PhysicianDto>(headByDepartmentId, HttpStatus.OK);

	}

	@GetMapping("/head/{head}")//Handle GET requests to /head/{head}
	public ResponseEntity<List<DepartmentDto>> getDepartmentsByHeadId(@PathVariable Integer head) {
		//GET the Departments Object by HeadId using department service 
		List<DepartmentDto> listOfDepartmentsByHead = departmentService.getDepartmentsByHeadId(head);
		//Return list of Departments  with HTTP  status 200 (OK)
		return new ResponseEntity<List<DepartmentDto>>(listOfDepartmentsByHead, HttpStatus.OK);

	}

	@GetMapping("/check/{physicianid}")//Handle GET requests to /check/{physicianid}
	public ResponseEntity<Boolean> pysicianIsHeadOfDepartment(@PathVariable Integer physicianid) {
		//GET the physician is head of department or not  using departmentService 
		Boolean pysicianIsHeadOfDepartment = departmentService.physicianIsHeadOfDepartment(physicianid);
		//Return physician is head Of department with HTTP  status 200 (OK)
		return new ResponseEntity<Boolean>(pysicianIsHeadOfDepartment, HttpStatus.OK);

	}

	@PutMapping("/update/headid/{deptid}")//Handle PUT requests to /update/headid/{deptid}
	public ResponseEntity<DepartmentDto> updateDepartmentHeadId(@PathVariable Integer deptid,
			@RequestBody DepartmentDto head) {
		//Update department head id by departmentid using departmentservice 
		DepartmentDto updateDepartmentHeadId = departmentService.updateDepartmentHeadId(deptid, head);
		//Return the response with the updated departmentheadid and HTTP status 200 (OK)
		return new ResponseEntity<DepartmentDto>(updateDepartmentHeadId, HttpStatus.OK);

	}

	@PutMapping("/update/deptname/{deptid}")//Handle PUT requests to /update/deptname/{deptid}
	public ResponseEntity<DepartmentDto> updateNameDepartment(@PathVariable Integer deptid, @RequestBody DepartmentDto name) {
		//Update name of department by departmentid using departmentservice
		DepartmentDto updateNameDepartment = departmentService.updateNameDepartment(deptid, name);
		//Return the response with the updated Department Name and HTTP status 200 (OK)
		return new ResponseEntity<DepartmentDto>(updateNameDepartment, HttpStatus.OK);

	}

	@GetMapping("/headcertification/{deptid}")//Handle GET requests to headcertification/{deptid}
	public ResponseEntity<List<Object>> getListOfCertificationByDeptId(@PathVariable Integer deptid) {
		//GET list of certification by departmentId using department service 
		List<Object> listOfCertitificatioByDeptId = departmentService.getListOfCertitificatioByDeptId(deptid);
		//Return the response with the list of certifications and HTTP status 200 (OK)
		return new ResponseEntity<List<Object>>(listOfCertitificatioByDeptId, HttpStatus.OK);
	}

}
