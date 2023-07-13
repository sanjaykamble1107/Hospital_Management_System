package com.hms.service.inter;

import java.time.LocalDateTime;

import java.util.List;

import com.hms.dto.AppointmentDto;
import com.hms.dto.NurseDto;
import com.hms.dto.PatientDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.dto.RoomDto;

public interface IAppointmentService {

	public List<AppointmentDto> getListOfAppointment();

	public ResponseMessageDto saveAppointment(AppointmentDto appointmentDto);

	public List<AppointmentDto> getListOfAppointmentByStartDate(LocalDateTime startDate);

	public PatientDto getPatientDetailsByAppointmentID(Integer appointmentId);

	public PhysicianDto getPhysicianDetailsByAppointmentID(Integer appointmentId);
	
	public NurseDto getNurseDetailsByAppointmentID(Integer appointmentId);
	
	public List<PhysicianDto> getPhysicianByPatient(Integer patient);
	
	public PhysicianDto getPhysicianByPatientIDOnDate(Integer patientId, LocalDateTime startDateTime);
	
	public List<NurseDto> getNurseDetailsByPatientId(Integer patientId);
	
	public NurseDto getNurseByPatientIDOnDate(Integer patientId, LocalDateTime startDateTime);

	public List<LocalDateTime> getDateDetailsByPatientId(Integer Patient);
	
	public List<PatientDto> getPatientDetailsByPhysicianId(Integer physicianId);
	
	public List<PatientDto> getPatientDetailsBYPhysicianIdOnDate(Integer physicianId, LocalDateTime startDateTime);
	
	public PatientDto getPatientByPhysicianIdAndPatientId(Integer physicianId, Integer patientId);
	
	public List<PatientDto> getPatientDetailsByNurseId(Integer nurseId);
	
	public PatientDto getPatientByNurseIdByPatientId (Integer nurseId, Integer patientId);
	
	public List<PatientDto> getPatientByNurseOnDate(Integer nurseId, LocalDateTime startDateTime);
	
	public RoomDto getRoomDetailsByPatientIdAndDate(Integer patientId, LocalDateTime startDateTime);
	
	public List<RoomDto> getRoomDetailsBYPhysicianAndDate(Integer physicianId, LocalDateTime startDateTime);
	
	public List<RoomDto> getRoomDetailsBYNurseAndDate(Integer nurseId, LocalDateTime startDateTime);
	
	public RoomDto updateRoomByAppointmentId(Integer appointmentId,  AppointmentDto appointmentDto);
	
	public AppointmentDto findByAppointmentId(Integer appointmentId);
	

}
