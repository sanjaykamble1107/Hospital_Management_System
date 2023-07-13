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

import com.hms.dto.AppointmentDto;
import com.hms.dto.NurseDto;
import com.hms.dto.PatientDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.dto.RoomDto;
import com.hms.service.inter.IAppointmentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private IAppointmentService appointmentService;

	@GetMapping
	public ResponseEntity<List<AppointmentDto>> getListOfAppointment() {
		List<AppointmentDto> listOfAppointment = appointmentService.getListOfAppointment();
		return new ResponseEntity<List<AppointmentDto>>(listOfAppointment, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ResponseMessageDto> addAppointment(@RequestBody AppointmentDto appointmentDto) {
		ResponseMessageDto message = appointmentService.saveAppointment(appointmentDto);
		return new ResponseEntity<ResponseMessageDto>(message, HttpStatus.OK);
	}

	@GetMapping("/{startdate}")
	public ResponseEntity<List<AppointmentDto>> getListOfAppointmentByStartDate(@PathVariable LocalDateTime startdate) {
		List<AppointmentDto> getListOfAppointmentByStartDate = appointmentService
				.getListOfAppointmentByStartDate(startdate);
		return new ResponseEntity<List<AppointmentDto>>(getListOfAppointmentByStartDate, HttpStatus.OK);
	}

	@GetMapping("/patient/{appointmentid}")
	public ResponseEntity<PatientDto> getPatientDetailsByAppointmentID(@PathVariable Integer appointmentid) {
		PatientDto getPatientDetailsByAppointmentID = appointmentService
				.getPatientDetailsByAppointmentID(appointmentid);
		return new ResponseEntity<PatientDto>(getPatientDetailsByAppointmentID, HttpStatus.OK);
	}

	@GetMapping("/physician/{appointmentid}")
	public ResponseEntity<PhysicianDto> getPhysicianDetailsByAppointmentID(@PathVariable Integer appointmentid) {
		PhysicianDto getPhysicianDetailsByAppointmentID = appointmentService
				.getPhysicianDetailsByAppointmentID(appointmentid);
		return new ResponseEntity<PhysicianDto>(getPhysicianDetailsByAppointmentID, HttpStatus.OK);
	}

	@GetMapping("/nurse/{appointmentid}")
	public ResponseEntity<NurseDto> getNurseDetailsByAppointmentID(@PathVariable Integer appointmentid) {
		NurseDto getNurseDetailsByAppointmentID = appointmentService.getNurseDetailsByAppointmentID(appointmentid);
		return new ResponseEntity<NurseDto>(getNurseDetailsByAppointmentID, HttpStatus.OK);
	}

	@GetMapping("/physician/patient/{patientid}")
	public ResponseEntity<List<PhysicianDto>> getPhysicianByPatient(@PathVariable Integer patientid) {
		List<PhysicianDto> getPhysicianByPatient = appointmentService.getPhysicianByPatient(patientid);
		return new ResponseEntity<List<PhysicianDto>>(getPhysicianByPatient, HttpStatus.OK);
	}

	@GetMapping("/physician/{patientid}/{date}")
	public ResponseEntity<PhysicianDto> getPhysicianByPatientIDOnDate(@PathVariable Integer patientid,
			@PathVariable LocalDateTime date) {
		PhysicianDto getPhysicianByPatientIDOnDate = appointmentService.getPhysicianByPatientIDOnDate(patientid, date);
		return new ResponseEntity<PhysicianDto>(getPhysicianByPatientIDOnDate, HttpStatus.OK);
	}

	@GetMapping("/nurse/patient/{patientid}")
	public ResponseEntity<List<NurseDto>> getNurseDetailsByPatientId(@PathVariable Integer patientid) {
		List<NurseDto> getNurseDetailsByPatientId = appointmentService.getNurseDetailsByPatientId(patientid);
		return new ResponseEntity<List<NurseDto>>(getNurseDetailsByPatientId, HttpStatus.OK);
	}

	@GetMapping("nurse/{patientid}/{date}")
	public ResponseEntity<NurseDto> getNurseByPatientIDOnDate(@PathVariable Integer patientid,
			@PathVariable LocalDateTime date) {
		NurseDto getNurseByPatientIDOnDate = appointmentService.getNurseByPatientIDOnDate(patientid, date);
		return new ResponseEntity<NurseDto>(getNurseByPatientIDOnDate, HttpStatus.OK);
	}

	@GetMapping("date/{patientid}")
	public ResponseEntity<List<LocalDateTime>> getDateDetailsByPatientId(@PathVariable Integer patientid) {
		List<LocalDateTime> getDateDetailsByPatientId = appointmentService.getDateDetailsByPatientId(patientid);
		return new ResponseEntity<List<LocalDateTime>>(getDateDetailsByPatientId, HttpStatus.OK);
	}

	@GetMapping("/patient/physician/{physicianid}")
	public ResponseEntity<List<PatientDto>> getPatientDetailsByPhysicianId(@PathVariable Integer physicianid) {
		List<PatientDto> patientDetailsByPhysicianId = appointmentService.getPatientDetailsByPhysicianId(physicianid);
		return new ResponseEntity<List<PatientDto>>(patientDetailsByPhysicianId, HttpStatus.OK);
	}

	@GetMapping("/patient/date/{physicianid}/{date}")
	public ResponseEntity<List<PatientDto>> getPatientDetailsBYPhysicianIdOnDate(@PathVariable Integer physicianid,
			@PathVariable LocalDateTime date) {
		List<PatientDto> getPatientDetailsBYPhysicianIdOnDate = appointmentService
				.getPatientDetailsBYPhysicianIdOnDate(physicianid, date);
		return new ResponseEntity<List<PatientDto>>(getPatientDetailsBYPhysicianIdOnDate, HttpStatus.OK);
	}

	@GetMapping("/patient/{physicianid}/{patientid}")
	public ResponseEntity<PatientDto> getPatientByPhysicianIdAndPatientId(@PathVariable Integer physicianid,
			@PathVariable Integer patientid) {
		PatientDto getPatientByPhysicianIdAndPatientId = appointmentService
				.getPatientByPhysicianIdAndPatientId(physicianid, patientid);
		return new ResponseEntity<PatientDto>(getPatientByPhysicianIdAndPatientId, HttpStatus.OK);
	}

	@GetMapping("/patient/nurse/{nurseid}")
	public ResponseEntity<List<PatientDto>> getPatientDetailsByNurseId(@PathVariable Integer nurseid) {
		List<PatientDto> getPatientDetailsByNurseId = appointmentService.getPatientDetailsByNurseId(nurseid);
		return new ResponseEntity<List<PatientDto>>(getPatientDetailsByNurseId, HttpStatus.OK);
	}

	@GetMapping("/patient/nurse/{nurseid}/{patientid}")
	public ResponseEntity<PatientDto> getPatientByNurseIdByPatientId(@PathVariable Integer nurseid,
			@PathVariable Integer patientid) {
		PatientDto getPatientByNurseIdByPatientId = appointmentService.getPatientByNurseIdByPatientId(nurseid,
				patientid);
		return new ResponseEntity<PatientDto>(getPatientByNurseIdByPatientId, HttpStatus.OK);
	}

	@GetMapping("/patient/nurse/date/{nurseid}/{date}")
	public ResponseEntity<List<PatientDto>> getPatientByNurseOnDate(@PathVariable Integer nurseid,
			@PathVariable LocalDateTime date) {
		List<PatientDto> getPatientByNurseOnDate = appointmentService.getPatientByNurseOnDate(nurseid, date);
		return new ResponseEntity<List<PatientDto>>(getPatientByNurseOnDate, HttpStatus.OK);
	}

	@GetMapping("room/{patientid}/{date}")
	public ResponseEntity<RoomDto> getRoomDetailsByPatientIdAndDate(@PathVariable Integer patientid,
			@PathVariable LocalDateTime date) {
		RoomDto getRoomDetailsByPatientIdAndDate = appointmentService.getRoomDetailsByPatientIdAndDate(patientid, date);
		return new ResponseEntity<RoomDto>(getRoomDetailsByPatientIdAndDate, HttpStatus.OK);
	}

	@GetMapping("/room/physician/{physicianid}/{date}")
	public ResponseEntity<List<RoomDto>> getRoomDetailsBYPhysicianAndDate(@PathVariable Integer physicianid,
			@PathVariable LocalDateTime date) {
		List<RoomDto> getRoomDetailsBYPhysicianAndDate = appointmentService
				.getRoomDetailsBYPhysicianAndDate(physicianid, date);
		return new ResponseEntity<List<RoomDto>>(getRoomDetailsBYPhysicianAndDate, HttpStatus.OK);
	}

	@GetMapping("/room/nurse/{nurseid}/{date}")
	public ResponseEntity<List<RoomDto>> getRoomDetailsBYNurseAndDate(@PathVariable Integer nurseid,
			@PathVariable LocalDateTime date) {
		List<RoomDto> getRoomDetailsBYNurseAndDate = appointmentService.getRoomDetailsBYNurseAndDate(nurseid, date);
		return new ResponseEntity<List<RoomDto>>(getRoomDetailsBYNurseAndDate, HttpStatus.OK);
	}

	@PutMapping("/room/{appointmentid}")
	public ResponseEntity<RoomDto> updateRoomByAppointmentId(@PathVariable Integer appointmentid,
			@RequestBody AppointmentDto appointmentDto) {
		RoomDto updateRoomByAppointmentId = appointmentService.updateRoomByAppointmentId(appointmentid, appointmentDto);
		return new ResponseEntity<RoomDto>(updateRoomByAppointmentId, HttpStatus.OK);
	}

	@GetMapping("/appointmentId/{id}")
	public ResponseEntity<AppointmentDto> findByAppointmentId(@PathVariable Integer id) {
		AppointmentDto findByAppointmentId = appointmentService.findByAppointmentId(id);
		return new ResponseEntity<AppointmentDto>(findByAppointmentId, HttpStatus.OK);
	}
	
}
