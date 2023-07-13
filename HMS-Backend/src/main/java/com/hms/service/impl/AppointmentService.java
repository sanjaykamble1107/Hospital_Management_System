package com.hms.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dto.AppointmentDto;
import com.hms.dto.NurseDto;
import com.hms.dto.PatientDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.dto.RoomDto;
import com.hms.entity.Appointment;
import com.hms.entity.Nurse;
import com.hms.entity.Patient;
import com.hms.entity.Physician;
import com.hms.entity.Room;
import com.hms.exception.model.AppointmentNotFoundException;
import com.hms.exception.model.ValidationException;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.RoomRepository;
import com.hms.service.inter.IAppointmentService;

@Service
public class AppointmentService implements IAppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private RoomRepository roomRepository;

	private ResponseMessageDto messageDto = new ResponseMessageDto();

	@Override
	public List<AppointmentDto> getListOfAppointment() {
		List<Appointment> findAll = appointmentRepository.findAll();

		List<AppointmentDto> appointmentDtos = new ArrayList<>();
		for (Appointment appointment : findAll) {
			AppointmentDto dto = new AppointmentDto();
			dto.setPatient(appointment.getPatient().getSsn());
			dto.setPhysician(appointment.getPhysician().getEmployeeId());
			dto.setExaminationRoom(appointment.getRoom().getRoomNumber());
			if (appointment.getPrepNurse() != null) {
				dto.setPrepNurse(appointment.getPrepNurse().getEmployeeId());
			}
			BeanUtils.copyProperties(appointment, dto);
			appointmentDtos.add(dto);
		}
		return appointmentDtos;
	}

	@Override
	public ResponseMessageDto saveAppointment(AppointmentDto appointmentDto) {
		Appointment appointment = new Appointment();
		Patient patient = new Patient();
		Physician physician = new Physician();
		Nurse nurse = new Nurse();
		Room room = new Room();
		patient.setSsn(appointmentDto.getPatient());
		physician.setEmployeeId(appointmentDto.getPhysician());
		nurse.setEmployeeId(appointmentDto.getPrepNurse());
		BeanUtils.copyProperties(appointmentDto, appointment);
		appointment.setPatient(patient);
		appointment.setPhysician(physician);
		appointment.setPrepNurse(nurse);
		room.setRoomNumber(appointmentDto.getExaminationRoom());
		appointment.setRoom(room);
		appointmentRepository.save(appointment);
		messageDto.setResponse("Record Created Successfully");
		return messageDto;
	}

	@Override
	public List<AppointmentDto> getListOfAppointmentByStartDate(LocalDateTime startDate) {
		List<Appointment> findAppointmentByStartDateTime = appointmentRepository
				.findAppointmentByStartDateTime(startDate);
		if (findAppointmentByStartDateTime.isEmpty()) {
			throw new AppointmentNotFoundException("No Appointment Found with Date: " + startDate);
		}

		List<AppointmentDto> dtos = new ArrayList<>();
		for (Appointment app : findAppointmentByStartDateTime) {
			AppointmentDto appDto = new AppointmentDto();
			appDto.setPatient(app.getPatient().getSsn());
			appDto.setPhysician(app.getPhysician().getEmployeeId());
			appDto.setPrepNurse(app.getPrepNurse().getEmployeeId());
			BeanUtils.copyProperties(app, appDto);
			dtos.add(appDto);
		}
		return dtos;
	}

	@Override
	public PatientDto getPatientDetailsByAppointmentID(Integer appointmentId) {
		Optional<Appointment> findPatientByAppointmentId = appointmentRepository
				.findPatientByAppointmentId(appointmentId);
		PatientDto patientDto = new PatientDto();
		if (findPatientByAppointmentId.isPresent()) {
			patientDto.setSsn(findPatientByAppointmentId.get().getPatient().getSsn());
			patientDto.setName(findPatientByAppointmentId.get().getPatient().getName());
			patientDto.setAddress(findPatientByAppointmentId.get().getPatient().getAddress());
			patientDto.setPhone(findPatientByAppointmentId.get().getPatient().getPhone());
			patientDto.setInsuranceID(findPatientByAppointmentId.get().getPatient().getInsuranceID());
			patientDto.setPcp(findPatientByAppointmentId.get().getPhysician().getEmployeeId());
			return patientDto;
		}
		throw new AppointmentNotFoundException("No Appointment Found with Appointment ID: " + appointmentId);
	}

	@Override
	public PhysicianDto getPhysicianDetailsByAppointmentID(Integer appointmentId) {
		Optional<Appointment> findPhysicianByAppointmentId = appointmentRepository
				.findPhysicianByAppointmentId(appointmentId);
		PhysicianDto physicianDto = new PhysicianDto();
		if (findPhysicianByAppointmentId.isPresent()) {
			physicianDto.setEmployeeId(findPhysicianByAppointmentId.get().getPhysician().getEmployeeId());
			physicianDto.setName(findPhysicianByAppointmentId.get().getPhysician().getName());
			physicianDto.setPosition(findPhysicianByAppointmentId.get().getPhysician().getPosition());
			physicianDto.setSsn(findPhysicianByAppointmentId.get().getPhysician().getSsn());
			return physicianDto;
		}
		throw new AppointmentNotFoundException("No Appointment Found with Appointment ID: " + appointmentId);
	}

	@Override
	public NurseDto getNurseDetailsByAppointmentID(Integer appointmentId) {
		Optional<Appointment> findNurseByAppointmentId = appointmentRepository.findNurseByAppointmentId(appointmentId);
		NurseDto nurseDto = new NurseDto();
		if (findNurseByAppointmentId.isPresent()) {
			nurseDto.setEmployeeId(findNurseByAppointmentId.get().getPrepNurse().getEmployeeId());
			nurseDto.setName(findNurseByAppointmentId.get().getPrepNurse().getName());
			nurseDto.setPosition(findNurseByAppointmentId.get().getPrepNurse().getPosition());
			nurseDto.setRegistered(findNurseByAppointmentId.get().getPrepNurse().getRegistered());
			nurseDto.setSsn(findNurseByAppointmentId.get().getPrepNurse().getSsn());
			return nurseDto;
		}
		throw new AppointmentNotFoundException("No Appointment Found with Appointment ID: " + appointmentId);
	}

	@Override
	public List<PhysicianDto> getPhysicianByPatient(Integer patient) {
		List<Appointment> findPhysicianByPatientSsn = appointmentRepository.findPhysicianByPatientSsn(patient);
		List<PhysicianDto> dtos = new ArrayList<>();
		for (Appointment app : findPhysicianByPatientSsn) {
			PhysicianDto physicianDto = new PhysicianDto();
			physicianDto.setEmployeeId(app.getPhysician().getEmployeeId());
			physicianDto.setName(app.getPhysician().getName());
			physicianDto.setPosition(app.getPhysician().getPosition());
			physicianDto.setSsn(app.getPhysician().getSsn());
			dtos.add(physicianDto);
		}
		return dtos;
	}

	@Override
	public PhysicianDto getPhysicianByPatientIDOnDate(Integer patientId, LocalDateTime startDateTime) {
		Optional<Appointment> findPhysicianByPatientSsnAndStartDateTime = appointmentRepository
				.findPhysicianByPatientSsnAndStartDateTime(patientId, startDateTime);
		PhysicianDto physicianDto = new PhysicianDto();
		if (findPhysicianByPatientSsnAndStartDateTime.isPresent()) {
			physicianDto.setEmployeeId(findPhysicianByPatientSsnAndStartDateTime.get().getPhysician().getEmployeeId());
			physicianDto.setName(findPhysicianByPatientSsnAndStartDateTime.get().getPhysician().getName());
			physicianDto.setPosition(findPhysicianByPatientSsnAndStartDateTime.get().getPhysician().getPosition());
			physicianDto.setSsn(findPhysicianByPatientSsnAndStartDateTime.get().getPhysician().getSsn());
			return physicianDto;
		}
		throw new AppointmentNotFoundException("No Physician Found");
	}

	@Override
	public List<NurseDto> getNurseDetailsByPatientId(Integer patientId) {
		List<Appointment> findNurseByPatientSsn = appointmentRepository.findNurseByPatientSsn(patientId);
		List<NurseDto> dtos = new ArrayList<>();
		for (Appointment app : findNurseByPatientSsn) {
			NurseDto nurseDto = new NurseDto();
			nurseDto.setEmployeeId(app.getPrepNurse().getEmployeeId());
			nurseDto.setName(app.getPrepNurse().getName());
			nurseDto.setPosition(app.getPrepNurse().getPosition());
			nurseDto.setRegistered(app.getPrepNurse().getRegistered());
			nurseDto.setSsn(app.getPrepNurse().getSsn());
			dtos.add(nurseDto);
		}
		return dtos;
	}

	@Override
	public NurseDto getNurseByPatientIDOnDate(Integer patientId, LocalDateTime startDateTime) {
		Optional<Appointment> findNurseByPatientSsnAndStartDateTime = appointmentRepository
				.findNurseByPatientSsnAndStartDateTime(patientId, startDateTime);
		NurseDto nurseDto = new NurseDto();
		if (findNurseByPatientSsnAndStartDateTime.isPresent()) {
			nurseDto.setEmployeeId(findNurseByPatientSsnAndStartDateTime.get().getPrepNurse().getEmployeeId());
			nurseDto.setName(findNurseByPatientSsnAndStartDateTime.get().getPrepNurse().getName());
			nurseDto.setPosition(findNurseByPatientSsnAndStartDateTime.get().getPrepNurse().getPosition());
			nurseDto.setRegistered(findNurseByPatientSsnAndStartDateTime.get().getPrepNurse().getRegistered());
			nurseDto.setSsn(findNurseByPatientSsnAndStartDateTime.get().getPrepNurse().getSsn());
			return nurseDto;
		}
		throw new AppointmentNotFoundException("No Nurse Found!");
	}

	@Override
	public List<LocalDateTime> getDateDetailsByPatientId(Integer patientId) {
		List<Appointment> findStartDateTimeByPatientSsn = appointmentRepository
				.findStartDateTimeByPatientSsn(patientId);
		if (findStartDateTimeByPatientSsn.isEmpty()) {
			throw new AppointmentNotFoundException("No Appointment Found!");
		}
		List<LocalDateTime> listOfDate = new ArrayList<>();
		for (Appointment app : findStartDateTimeByPatientSsn) {
			listOfDate.add(app.getStartDateTime());
		}
		return listOfDate;
	}

	@Override
	public List<PatientDto> getPatientDetailsByPhysicianId(Integer physicianId) {
		List<Appointment> findPatientSsnByPhysicianEmployeeId = appointmentRepository
				.findPatientByPhysicianEmployeeId(physicianId);
		if (findPatientSsnByPhysicianEmployeeId.isEmpty()) {
			throw new AppointmentNotFoundException("No Patient Found!");
		}
		List<PatientDto> dtos = new ArrayList<>();
		for (Appointment app : findPatientSsnByPhysicianEmployeeId) {
			PatientDto patientDto = new PatientDto();
			patientDto.setSsn(app.getPatient().getSsn());
			patientDto.setName(app.getPatient().getName());
			patientDto.setAddress(app.getPatient().getAddress());
			patientDto.setPhone(app.getPatient().getPhone());
			patientDto.setInsuranceID(app.getPatient().getInsuranceID());
			patientDto.setSsn(app.getPatient().getSsn());
			dtos.add(patientDto);
		}
		return dtos;
	}

	@Override
	public List<PatientDto> getPatientDetailsBYPhysicianIdOnDate(Integer physicianId, LocalDateTime startDateTime) {
		List<Appointment> findPatientByPhysicianEmployeeIdAndStartDateTime = appointmentRepository
				.findPatientByPhysicianEmployeeIdAndStartDateTime(physicianId, startDateTime);
		if (findPatientByPhysicianEmployeeIdAndStartDateTime.isEmpty()) {
			throw new AppointmentNotFoundException("No Patient Found!");
		}
		List<PatientDto> dtos = new ArrayList<>();
		for (Appointment app : findPatientByPhysicianEmployeeIdAndStartDateTime) {
			PatientDto patientDto = new PatientDto();
			patientDto.setSsn(app.getPatient().getSsn());
			patientDto.setName(app.getPatient().getName());
			patientDto.setAddress(app.getPatient().getAddress());
			patientDto.setPhone(app.getPatient().getPhone());
			patientDto.setInsuranceID(app.getPatient().getInsuranceID());
			patientDto.setSsn(app.getPatient().getSsn());
			dtos.add(patientDto);
		}
		return dtos;
	}

	@Override
	public PatientDto getPatientByPhysicianIdAndPatientId(Integer physicianId, Integer patientId) {
		Optional<Appointment> findPatientByPhysicianEmployeeIdAndPatientSsn = appointmentRepository
				.findPatientByPhysicianEmployeeIdAndPatientSsn(physicianId, patientId);
		PatientDto patientDto = new PatientDto();
		if (findPatientByPhysicianEmployeeIdAndPatientSsn.isPresent()) {
			patientDto.setSsn(findPatientByPhysicianEmployeeIdAndPatientSsn.get().getPatient().getSsn());
			patientDto.setName(findPatientByPhysicianEmployeeIdAndPatientSsn.get().getPatient().getName());
			patientDto.setAddress(findPatientByPhysicianEmployeeIdAndPatientSsn.get().getPatient().getAddress());
			patientDto.setPhone(findPatientByPhysicianEmployeeIdAndPatientSsn.get().getPatient().getPhone());
			patientDto
					.setInsuranceID(findPatientByPhysicianEmployeeIdAndPatientSsn.get().getPatient().getInsuranceID());
			patientDto.setPcp(findPatientByPhysicianEmployeeIdAndPatientSsn.get().getPhysician().getEmployeeId());
			return patientDto;
		}
		throw new AppointmentNotFoundException("No Patient Found With Physician ID: " + physicianId);
	}

	@Override
	public List<PatientDto> getPatientDetailsByNurseId(Integer nurseId) {
		List<Appointment> findPatientByPrepNurseEmployeeId = appointmentRepository
				.findPatientByPrepNurseEmployeeId(nurseId);
		if (findPatientByPrepNurseEmployeeId.isEmpty()) {
			throw new AppointmentNotFoundException("No Patient Found!");
		}
		List<PatientDto> dtos = new ArrayList<>();
		for (Appointment app : findPatientByPrepNurseEmployeeId) {
			PatientDto patientDto = new PatientDto();
			patientDto.setSsn(app.getPatient().getSsn());
			patientDto.setName(app.getPatient().getName());
			patientDto.setAddress(app.getPatient().getAddress());
			patientDto.setPhone(app.getPatient().getPhone());
			patientDto.setInsuranceID(app.getPatient().getInsuranceID());
			patientDto.setSsn(app.getPatient().getSsn());
			dtos.add(patientDto);
		}
		return dtos;
	}

	@Override
	public PatientDto getPatientByNurseIdByPatientId(Integer nurseId, Integer patientId) {
		Optional<Appointment> findPatientByPrepNurseEmployeeIdAndPatientSsn = appointmentRepository
				.findPatientByPrepNurseEmployeeIdAndPatientSsn(nurseId, patientId);
		PatientDto patientDto = new PatientDto();
		if (findPatientByPrepNurseEmployeeIdAndPatientSsn.isPresent()) {
			patientDto.setSsn(findPatientByPrepNurseEmployeeIdAndPatientSsn.get().getPatient().getSsn());
			patientDto.setName(findPatientByPrepNurseEmployeeIdAndPatientSsn.get().getPatient().getName());
			patientDto.setAddress(findPatientByPrepNurseEmployeeIdAndPatientSsn.get().getPatient().getAddress());
			patientDto.setPhone(findPatientByPrepNurseEmployeeIdAndPatientSsn.get().getPatient().getPhone());
			patientDto
					.setInsuranceID(findPatientByPrepNurseEmployeeIdAndPatientSsn.get().getPatient().getInsuranceID());
			patientDto.setPcp(findPatientByPrepNurseEmployeeIdAndPatientSsn.get().getPhysician().getEmployeeId());
			return patientDto;
		}
		throw new AppointmentNotFoundException("No Patient Found!");
	}

	@Override
	public List<PatientDto> getPatientByNurseOnDate(Integer nurseId, LocalDateTime startDateTime) {
		List<Appointment> findPatientByPrepNurseEmployeeIdAndStartDateTime = appointmentRepository
				.findPatientByPrepNurseEmployeeIdAndStartDateTime(nurseId, startDateTime);
		if (findPatientByPrepNurseEmployeeIdAndStartDateTime.isEmpty()) {
			throw new AppointmentNotFoundException("No Patient Found!");
		}
		List<PatientDto> dtos = new ArrayList<>();
		for (Appointment app : findPatientByPrepNurseEmployeeIdAndStartDateTime) {
			PatientDto patientDto = new PatientDto();
			patientDto.setSsn(app.getPatient().getSsn());
			patientDto.setName(app.getPatient().getName());
			patientDto.setAddress(app.getPatient().getAddress());
			patientDto.setPhone(app.getPatient().getPhone());
			patientDto.setInsuranceID(app.getPatient().getInsuranceID());
			patientDto.setSsn(app.getPatient().getSsn());
			dtos.add(patientDto);
		}
		return dtos;
	}

	@Override
	public RoomDto getRoomDetailsByPatientIdAndDate(Integer patientId, LocalDateTime startDateTime) {
		Optional<Appointment> findRoomByPatientSsnANdStartDateTime = appointmentRepository
				.findRoomByPatientSsnAndStartDateTime(patientId, startDateTime);
		RoomDto roomDto = new RoomDto();
		if (findRoomByPatientSsnANdStartDateTime.isPresent()) {
			roomDto.setRoomNumber(findRoomByPatientSsnANdStartDateTime.get().getRoom().getRoomNumber());
			roomDto.setRoomType(findRoomByPatientSsnANdStartDateTime.get().getRoom().getRoomType());
			roomDto.setBlockFloor(findRoomByPatientSsnANdStartDateTime.get().getRoom().getBlock().getBlockComposite()
					.getBlockFloor());
			roomDto.setBlockCode(
					findRoomByPatientSsnANdStartDateTime.get().getRoom().getBlock().getBlockComposite().getBlockCode());
			roomDto.setUnavailable(findRoomByPatientSsnANdStartDateTime.get().getRoom().getUnavailable());
			return roomDto;
		}
		throw new AppointmentNotFoundException("No Room Found");
	}

	@Override
	public List<RoomDto> getRoomDetailsBYPhysicianAndDate(Integer physicianId, LocalDateTime startDateTime) {
		List<Appointment> findRoomByPhysicianEmployeeIdAndStartDateTime = appointmentRepository
				.findRoomByPhysicianEmployeeIdAndStartDateTime(physicianId, startDateTime);
		if (findRoomByPhysicianEmployeeIdAndStartDateTime.isEmpty()) {
			throw new AppointmentNotFoundException("No Room Found!");
		}
		List<RoomDto> dtos = new ArrayList<>();
		for (Appointment app : findRoomByPhysicianEmployeeIdAndStartDateTime) {
			RoomDto roomDto = new RoomDto();
			roomDto.setRoomNumber(app.getRoom().getRoomNumber());
			roomDto.setRoomType(app.getRoom().getRoomType());
			roomDto.setBlockFloor(app.getRoom().getBlock().getBlockComposite().getBlockFloor());
			roomDto.setBlockCode(app.getRoom().getBlock().getBlockComposite().getBlockCode());
			roomDto.setUnavailable(app.getRoom().getUnavailable());
			dtos.add(roomDto);

		}
		return dtos;
	}

	@Override
	public List<RoomDto> getRoomDetailsBYNurseAndDate(Integer nurseId, LocalDateTime startDateTime) {
		List<Appointment> findRoomByPrepNurseEmployeeIdAndStartDateTime = appointmentRepository
				.findRoomByPrepNurseEmployeeIdAndStartDateTime(nurseId, startDateTime);
		if (findRoomByPrepNurseEmployeeIdAndStartDateTime.isEmpty()) {
			throw new AppointmentNotFoundException("No Room Found!");
		}
		List<RoomDto> dtos = new ArrayList<>();
		for (Appointment app : findRoomByPrepNurseEmployeeIdAndStartDateTime) {
			RoomDto roomDto = new RoomDto();
			roomDto.setRoomNumber(app.getRoom().getRoomNumber());
			roomDto.setRoomType(app.getRoom().getRoomType());
			roomDto.setBlockFloor(app.getRoom().getBlock().getBlockComposite().getBlockFloor());
			roomDto.setBlockCode(app.getRoom().getBlock().getBlockComposite().getBlockCode());
			roomDto.setUnavailable(app.getRoom().getUnavailable());
			dtos.add(roomDto);
		}
		return dtos;
	}

	@Override
	public RoomDto updateRoomByAppointmentId(Integer appointmentId, AppointmentDto appointmentDto) {

		Optional<Appointment> findRoomByAppointmentId = appointmentRepository.findRoomByAppointmentId(appointmentId);

		RoomDto dto = new RoomDto();
		if (findRoomByAppointmentId.isPresent()) {
			Appointment appointment = new Appointment();
			Optional<Room> findById = roomRepository.findById(appointmentDto.getExaminationRoom());

			BeanUtils.copyProperties(findRoomByAppointmentId.get(), appointment);
			Room room = new Room();
			BeanUtils.copyProperties(appointmentDto, appointment);
			room.setRoomNumber(appointmentDto.getExaminationRoom());
			appointment.setRoom(room);
			BeanUtils.copyProperties(findById.get(), dto);
			dto.setBlockCode(findById.get().getBlock().getBlockComposite().getBlockCode());
			dto.setBlockFloor(findById.get().getBlock().getBlockComposite().getBlockFloor());
			appointmentRepository.save(appointment);
			return dto;
		}
		throw new AppointmentNotFoundException("No Appointment Found with Appointment ID: " + appointmentId);
	}

	@Override
	public AppointmentDto findByAppointmentId(Integer appointmentId) {
		Optional<Appointment> findById = appointmentRepository.findById(appointmentId);

		if (findById.isPresent()) {
			AppointmentDto appointmentDto = new AppointmentDto();
			BeanUtils.copyProperties(findById.get(), appointmentDto);
			appointmentDto.setExaminationRoom(findById.get().getRoom().getRoomNumber());
			appointmentDto.setPatient(findById.get().getPatient().getSsn());
			appointmentDto.setPhysician(findById.get().getPhysician().getEmployeeId());
			if(findById.get().getPrepNurse() == null) {
				appointmentDto.setPrepNurse(null);
			}else {				
				appointmentDto.setPrepNurse(findById.get().getPrepNurse().getEmployeeId());				
			}
			return appointmentDto;
		}
		throw new AppointmentNotFoundException("No Appointment Found with Appointment ID: " + appointmentId);
	}
}
