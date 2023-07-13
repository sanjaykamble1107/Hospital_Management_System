package com.hms.controller;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.auth.JwtDummyAuthentication;
import com.hms.dto.AppointmentDto;
import com.hms.dto.NurseDto;
import com.hms.dto.PatientDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.dto.RoomDto;
import com.hms.entity.Appointment;
import com.hms.entity.Room;
import com.hms.exception.model.AppointmentNotFoundException;
import com.hms.service.impl.AppointmentService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class TestAppointmentController {

	private static final ResponseMessageDto messageDto = null;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AppointmentService appointmentService;
	
	public String token() {
		String username = "admin";
		long expiresInMilliseconds = 3600000;
		return JwtDummyAuthentication.generateDummyToken(username, expiresInMilliseconds);

	}

	@Test
	public void testAddAppointment() throws Exception {
		Integer appointmentid = 5;
		AppointmentDto appointmentDto = new AppointmentDto();
		appointmentDto.setAppointmentId(appointmentid);
		ResponseMessageDto message = new ResponseMessageDto();
		message.setResponse("Record Created Successfully");
		when(appointmentService.saveAppointment(any(AppointmentDto.class))).thenReturn(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/appointment/")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(appointmentDto))).andExpect(status().isOk());
		verify(appointmentService, times(1)).saveAppointment(any(AppointmentDto.class));
	}
	
	@Test//Negative
	public void testAddAppointmentValidationFailed() throws Exception {
		Integer appointmentid = 5;
		

		AppointmentDto appointmentDto = new AppointmentDto();
		appointmentDto.setAppointmentId(appointmentid);
		appointmentDto.setExaminationRoom(appointmentid);
		appointmentDto.setPatient(100000001);
		appointmentDto.setPhysician(1);
		appointmentDto.setPrepNurse(101);
		
		when(appointmentService.saveAppointment(any(AppointmentDto.class))).thenThrow(new AppointmentNotFoundException("No Appointment Found"));
		mockMvc.perform(MockMvcRequestBuilders.post("/appointment/")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(appointmentDto))).andExpect(status().isNotFound());
		verify(appointmentService, times(1)).saveAppointment(any(AppointmentDto.class));
	}

	@Test
	public void testGetListOfAppointment() throws Exception {
		AppointmentDto appDto = new AppointmentDto();
		List<AppointmentDto> list = Arrays.asList(appDto);
		when(appointmentService.getListOfAppointment()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getListOfAppointment();
	}
	
	@Test //Negative
	public void testGetListOfAppointmentNotFound() throws Exception {
		AppointmentDto appDto = new AppointmentDto();
		List<AppointmentDto> list = Arrays.asList(appDto);
		when(appointmentService.getListOfAppointment()).thenThrow(new AppointmentNotFoundException("No Appointment Found!"));
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getListOfAppointment();
	}

	@Test
	public void testGetListOfAppointmentByStartDate() throws Exception {
		LocalDateTime startDate = LocalDateTime.of(2023, 7, 5, 10, 0);
		List<AppointmentDto> expectedAppointmentList = Arrays.asList(new AppointmentDto());
		when(appointmentService.getListOfAppointmentByStartDate(startDate)).thenReturn(expectedAppointmentList);
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/{startdate}", startDate)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getListOfAppointmentByStartDate(startDate);
	}
	
	@Test //Negative
	public void testGetListOfAppointmentByStartDateNotFound() throws Exception {
		LocalDateTime startDate = LocalDateTime.of(2023, 7, 5, 10, 0);
		List<AppointmentDto> expectedAppointmentList = Arrays.asList(new AppointmentDto());
		when(appointmentService.getListOfAppointmentByStartDate(startDate)).thenThrow(new AppointmentNotFoundException("No Appointment Found!"));
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/{startdate}", startDate)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getListOfAppointmentByStartDate(startDate);
	}

	@Test
	public void testGetPatientDetailsByAppointmentID() throws Exception {
		Integer appointmentid = 17;
		PatientDto patientDto = new PatientDto();
		when(appointmentService.getPatientDetailsByAppointmentID(appointmentid)).thenReturn(patientDto);
		mockMvc.perform(get("/appointment/patient/{appointmentid}", appointmentid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getPatientDetailsByAppointmentID(appointmentid);
	}
	
	@Test //Negative
	public void testGetPatientDetailsByAppointmentIDNotFound() throws Exception {
		Integer appointmentid = 17;
		PatientDto patientDto = new PatientDto();
		when(appointmentService.getPatientDetailsByAppointmentID(appointmentid)).thenThrow
		(new AppointmentNotFoundException("No Appointment Found with Appointment ID: " + appointmentid));
		mockMvc.perform(get("/appointment/patient/{appointmentid}", appointmentid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getPatientDetailsByAppointmentID(appointmentid);
	}

	@Test
	public void testGetPhysicianDetailsByAppointmentID() throws Exception {
		Integer appointmentid = 17;
		PhysicianDto physicianDto = new PhysicianDto();
		when(appointmentService.getPhysicianDetailsByAppointmentID(appointmentid)).thenReturn(physicianDto);
		mockMvc.perform(get("/appointment/physician/{appointmentid}", appointmentid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getPhysicianDetailsByAppointmentID(appointmentid);

	}
	
	@Test //Negative
	public void testGetPhysicianDetailsByAppointmentIDNotFound() throws Exception {
		Integer appointmentid = 17;
		PhysicianDto physicianDto = new PhysicianDto();
		when(appointmentService.getPhysicianDetailsByAppointmentID(appointmentid)).thenThrow
		(new AppointmentNotFoundException("No Appointment Found with Appointment ID: " + appointmentid));
		mockMvc.perform(get("/appointment/physician/{appointmentid}", appointmentid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getPhysicianDetailsByAppointmentID(appointmentid);

	}

	@Test
	public void testGetNurseDetailsByAppointmentID() throws Exception {
		Integer appointmentid = 17;
		NurseDto nurseDto = new NurseDto();
		when(appointmentService.getNurseDetailsByAppointmentID(appointmentid)).thenReturn(nurseDto);
		mockMvc.perform(get("/appointment/nurse/{appointmentid}", appointmentid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getNurseDetailsByAppointmentID(appointmentid);
	}
	
	@Test//Negative
	public void testGetNurseDetailsByAppointmentIDNotFound() throws Exception {
		Integer appointmentid = 17;
		NurseDto nurseDto = new NurseDto();
		when(appointmentService.getNurseDetailsByAppointmentID(appointmentid)).thenThrow
		(new AppointmentNotFoundException("No Appointment Found with Appointment ID: " + appointmentid));
		mockMvc.perform(get("/appointment/nurse/{appointmentid}", appointmentid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getNurseDetailsByAppointmentID(appointmentid);
	}

	@Test
	public void testGetPhysicianByPatient() throws Exception {
		Integer patientid = 17;
		PhysicianDto pDto = new PhysicianDto();
		List<PhysicianDto> list = Arrays.asList(pDto);
		when(appointmentService.getPhysicianByPatient(patientid)).thenReturn(list);
		mockMvc.perform(get("/appointment/physician/patient/{patientid}", patientid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getPhysicianByPatient(patientid);
	}

	@Test //Negative
	public void testGetPhysicianByPatientNotFound() throws Exception {
		Integer patientid = 17;
		PhysicianDto pDto = new PhysicianDto();
		List<PhysicianDto> list = Arrays.asList(pDto);
		when(appointmentService.getPhysicianByPatient(patientid)).thenThrow
		(new AppointmentNotFoundException("No Physician Found "));
		mockMvc.perform(get("/appointment/physician/patient/{patientid}", patientid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getPhysicianByPatient(patientid);
	}

	@Test
	public void testGetNurseDetailsByPatientId() throws Exception {
		Integer patientid = 17;
		NurseDto nDto = new NurseDto();
		List<NurseDto> list = Arrays.asList(nDto);
		when(appointmentService.getNurseDetailsByPatientId(patientid)).thenReturn(list);
		mockMvc.perform(get("/appointment/nurse/patient/{patientid}", patientid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getNurseDetailsByPatientId(patientid);
	}
	
	@Test //Negative
	public void testGetNurseDetailsByPatientIdNotFound() throws Exception {
		Integer patientid = 17;
		NurseDto nDto = new NurseDto();
		List<NurseDto> list = Arrays.asList(nDto);
		when(appointmentService.getNurseDetailsByPatientId(patientid)).thenThrow
		(new AppointmentNotFoundException("No Nurse Found "));
		mockMvc.perform(get("/appointment/nurse/patient/{patientid}", patientid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getNurseDetailsByPatientId(patientid);
	}


	@Test
	public void testGetPatientByPhysicianIdAndPatientId() throws Exception {
		Integer patientid = 17;
		Integer physicianid = 17;
		PatientDto pDto = new PatientDto();
		when(appointmentService.getPatientByPhysicianIdAndPatientId(physicianid, patientid)).thenReturn(pDto);
		mockMvc.perform(get("/appointment/patient/{physicianid}/{patientid}", physicianid, patientid)
				
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getPatientByPhysicianIdAndPatientId(physicianid, patientid);
	}
	
	@Test //Negative
	public void testGetPatientByPhysicianIdAndPatientIdNotFound() throws Exception {
		Integer patientid = 17;
		Integer physicianid = 17;
		PatientDto pDto = new PatientDto();
		when(appointmentService.getPatientByPhysicianIdAndPatientId(physicianid, patientid)).thenThrow
		(new AppointmentNotFoundException("No Patient Found "));
		mockMvc.perform(get("/appointment/patient/{physicianid}/{patientid}", physicianid, patientid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getPatientByPhysicianIdAndPatientId(physicianid, patientid);
	}

	@Test
	public void testGetPatientDetailsByNurseId() throws Exception {
		Integer nurseid = 17;
		PatientDto pDto = new PatientDto();
		List<PatientDto> list = Arrays.asList(pDto);
		when(appointmentService.getPatientDetailsByNurseId(nurseid)).thenReturn(list);
		mockMvc.perform(get("/appointment/patient/nurse/{nurseid}", nurseid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getPatientDetailsByNurseId(nurseid);
	}
	
	@Test //Negative
	public void testGetPatientDetailsByNurseIdNotFound() throws Exception {
		Integer nurseid = 17;
		PatientDto pDto = new PatientDto();
		List<PatientDto> list = Arrays.asList(pDto);
		when(appointmentService.getPatientDetailsByNurseId(nurseid)).thenThrow
		(new AppointmentNotFoundException("No Patient Found "));
		mockMvc.perform(get("/appointment/patient/nurse/{nurseid}", nurseid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getPatientDetailsByNurseId(nurseid);
	}

	@Test
	public void testGetPatientByNurseIdByPatientId() throws Exception {
		Integer patientid = 17;
		Integer nurseid = 17;
		PatientDto ptDto = new PatientDto();
		when(appointmentService.getPatientByNurseIdByPatientId(nurseid, patientid)).thenReturn(ptDto);
		mockMvc.perform(get("/appointment/patient/nurse/{nurseid}/{patientid}", nurseid, patientid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getPatientByNurseIdByPatientId(nurseid, patientid);
	}

	@Test //Negative
	public void testGetPatientByNurseIdByPatientIdNotFound() throws Exception {
		Integer patientid = 17;
		Integer nurseid = 17;
		PatientDto ptDto = new PatientDto();
		when(appointmentService.getPatientByNurseIdByPatientId(nurseid, patientid)).thenThrow
		(new AppointmentNotFoundException("No Patient Found "));
		mockMvc.perform(get("/appointment/patient/nurse/{nurseid}/{patientid}", nurseid, patientid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getPatientByNurseIdByPatientId(nurseid, patientid);
	}
	@Test
	public void testGetPhysicianByPatientIDOnDate() throws Exception {
		Integer patientid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		PhysicianDto phyDto = new PhysicianDto();
		when(appointmentService.getPhysicianByPatientIDOnDate(patientid, date)).thenReturn(phyDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/physician/{patientid}/{date}", patientid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getPhysicianByPatientIDOnDate(patientid, date);
	}
	
	@Test //Negative
	public void testGetPhysicianByPatientIDOnDateNotFound() throws Exception {
		Integer patientid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		PhysicianDto phyDto = new PhysicianDto();
		when(appointmentService.getPhysicianByPatientIDOnDate(patientid, date)).thenThrow
		(new AppointmentNotFoundException("No Physician Found "));
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/physician/{patientid}/{date}", patientid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getPhysicianByPatientIDOnDate(patientid, date);
	}

	@Test
	public void testGetNurseByPatientIDOnDate() throws Exception {
		Integer patientid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		NurseDto nurseDto = new NurseDto();
		when(appointmentService.getNurseByPatientIDOnDate(patientid, date)).thenReturn(nurseDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/nurse/{patientid}/{date}", patientid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getNurseByPatientIDOnDate(patientid, date);
	}
    
	@Test//Negative
	public void testGetNurseByPatientIDOnDateNotFound() throws Exception {
		Integer patientid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		NurseDto nurseDto = new NurseDto();
		when(appointmentService.getNurseByPatientIDOnDate(patientid, date)).thenThrow
		(new AppointmentNotFoundException("No Nurse Found "));
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/nurse/{patientid}/{date}", patientid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getNurseByPatientIDOnDate(patientid, date);
	}

	@Test
	public void testGetDateDetailsByPatientId() throws Exception {
		Integer patientid = 17;
		List<LocalDateTime> list = new ArrayList<>();
		when(appointmentService.getDateDetailsByPatientId(patientid)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/date/{patientid}", patientid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getDateDetailsByPatientId(patientid);
	}
    
	@Test //Negative
	public void testGetDateDetailsByPatientIdNotFound() throws Exception {
		Integer patientid = 17;
		List<LocalDateTime> list = new ArrayList<>();
		when(appointmentService.getDateDetailsByPatientId(patientid)).thenThrow
		(new AppointmentNotFoundException("No Appointment Found!"));
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/date/{patientid}", patientid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getDateDetailsByPatientId(patientid);
	}

	@Test
	public void testGetPatientDetailsByPhysicianId() throws Exception {
		Integer physicianid = 17;
		PatientDto ptDto = new PatientDto();
		List<PatientDto> list = Arrays.asList(ptDto);
		when(appointmentService.getPatientDetailsByPhysicianId(physicianid)).thenReturn(list);
		mockMvc.perform(get("/appointment/patient/physician/{physicianid}", physicianid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getPatientDetailsByPhysicianId(physicianid);
	}
    
	@Test //Negative
	public void testGetPatientDetailsByPhysicianIdNotFound() throws Exception {
		Integer physicianid = 17;
		PatientDto ptDto = new PatientDto();
		List<PatientDto> list = Arrays.asList(ptDto);
		when(appointmentService.getPatientDetailsByPhysicianId(physicianid)).thenThrow
		(new AppointmentNotFoundException("No Patient Found!"));
		mockMvc.perform(get("/appointment/patient/physician/{physicianid}", physicianid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getPatientDetailsByPhysicianId(physicianid);
	}

	@Test
	public void testGetPatientDetailsBYPhysicianIdOnDate() throws Exception {
		Integer physicianid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		PatientDto patientDto = new PatientDto();
		List<PatientDto> list = Arrays.asList(patientDto);
		when(appointmentService.getPatientDetailsBYPhysicianIdOnDate(physicianid, date)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/patient/date/{physicianid}/{date}", physicianid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getPatientDetailsBYPhysicianIdOnDate(physicianid, date);
	}
	
	@Test //Negative
	public void testGetPatientDetailsBYPhysicianIdOnDateNotFound() throws Exception {
		Integer physicianid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		PatientDto patientDto = new PatientDto();
		List<PatientDto> list = Arrays.asList(patientDto);
		when(appointmentService.getPatientDetailsBYPhysicianIdOnDate(physicianid, date)).thenThrow
		(new AppointmentNotFoundException("No Patient Found!"));
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/patient/date/{physicianid}/{date}", physicianid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getPatientDetailsBYPhysicianIdOnDate(physicianid, date);
	}

	@Test
	public void testGetPatientByNurseOnDate() throws Exception {
		Integer nurseid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		PatientDto patientDto = new PatientDto();
		List<PatientDto> list = Arrays.asList(patientDto);
		when(appointmentService.getPatientByNurseOnDate(nurseid, date)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/patient/nurse/date/{nurseid}/{date}", nurseid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getPatientByNurseOnDate(nurseid, date);
	}
	
	@Test //Negative
	public void testGetPatientByNurseOnDateNotFound() throws Exception {
		Integer nurseid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		PatientDto patientDto = new PatientDto();
		List<PatientDto> list = Arrays.asList(patientDto);
		when(appointmentService.getPatientByNurseOnDate(nurseid, date)).thenThrow
		(new AppointmentNotFoundException("No Patient Found!"));
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/patient/nurse/date/{nurseid}/{date}", nurseid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getPatientByNurseOnDate(nurseid, date);
	}

	@Test
	public void testGetRoomDetailsByPatientIdAndDate() throws Exception {
		Integer patientid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		RoomDto roomDto = new RoomDto();
		when(appointmentService.getRoomDetailsByPatientIdAndDate(patientid, date)).thenReturn(roomDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/room/{patientid}/{date}", patientid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getRoomDetailsByPatientIdAndDate(patientid, date);
	}
	
	@Test//Negative
	public void testGetRoomDetailsByPatientIdAndDateNotFoubd() throws Exception {
		Integer patientid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		RoomDto roomDto = new RoomDto();
		when(appointmentService.getRoomDetailsByPatientIdAndDate(patientid, date)).thenThrow
		(new AppointmentNotFoundException("No Room Found "));
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/room/{patientid}/{date}", patientid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getRoomDetailsByPatientIdAndDate(patientid, date);
	}

	@Test
	public void testGetRoomDetailsBYPhysicianAndDate() throws Exception {
		Integer physicianid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		RoomDto roomDto = new RoomDto();
		List<RoomDto> list = Arrays.asList(roomDto);
		when(appointmentService.getRoomDetailsBYPhysicianAndDate(physicianid, date)).thenReturn(list);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/appointment/room/physician/{physicianid}/{date}", physicianid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getRoomDetailsBYPhysicianAndDate(physicianid, date);
	}
	
	@Test //Negative
	public void testGetRoomDetailsBYPhysicianAndDateNotFound() throws Exception {
		Integer physicianid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		RoomDto roomDto = new RoomDto();
		List<RoomDto> list = Arrays.asList(roomDto);
		when(appointmentService.getRoomDetailsBYPhysicianAndDate(physicianid, date)).thenThrow
		(new AppointmentNotFoundException("No Room Found "));
		mockMvc.perform(
				MockMvcRequestBuilders.get("/appointment/room/physician/{physicianid}/{date}", physicianid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getRoomDetailsBYPhysicianAndDate(physicianid, date);
	}

	@Test
	public void testGetRoomDetailsBYNurseAndDate() throws Exception {
		Integer nurseid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		RoomDto roomDto = new RoomDto();
		List<RoomDto> list = Arrays.asList(roomDto);
		when(appointmentService.getRoomDetailsBYNurseAndDate(nurseid, date)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/room/nurse/{nurseid}/{date}", nurseid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).getRoomDetailsBYNurseAndDate(nurseid, date);
	}
	
	@Test //Negative
	public void testGetRoomDetailsBYNurseAndDateNotFound() throws Exception {
		Integer nurseid = 17;
		LocalDateTime date = LocalDateTime.of(2023, 7, 5, 10, 0);
		RoomDto roomDto = new RoomDto();
		List<RoomDto> list = Arrays.asList(roomDto);
		when(appointmentService.getRoomDetailsBYNurseAndDate(nurseid, date)).thenThrow
		(new AppointmentNotFoundException("No Room Found "));
		mockMvc.perform(MockMvcRequestBuilders.get("/appointment/room/nurse/{nurseid}/{date}", nurseid, date)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(appointmentService, times(1)).getRoomDetailsBYNurseAndDate(nurseid, date);
	}


	public static String asJsonString(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testUpdateRoomByAppointmentId() throws Exception {
		Integer appointmentId = 1;
		Integer examinationRoom = 111;

		RoomDto room = new RoomDto();
		room.setRoomNumber(examinationRoom);
		Room room2 = new Room();
		BeanUtils.copyProperties(room, room2);
		Appointment appointment = new Appointment();
		appointment.setRoom(room2);

		mockMvc.perform(put("/appointment/room/{appointmentid}", appointmentId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(room)))
				.andExpect(status().isOk());
		verify(appointmentService, times(1)).updateRoomByAppointmentId(eq(appointmentId), any(AppointmentDto.class));
	}
	
}
