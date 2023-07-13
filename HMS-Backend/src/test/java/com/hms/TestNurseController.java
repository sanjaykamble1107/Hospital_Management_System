package com.hms;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
import com.hms.dto.NurseDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.dummy.JwtDummyAuthentication;
import com.hms.service.impl.NurseService;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestNurseController {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NurseService nurseService;

	public String token() {
		String username = "admin";
		long expiresInMilliseconds = 3600000;
		return JwtDummyAuthentication.generateDummyToken(username, expiresInMilliseconds);

	}
	
	@Test
	public void testAddNurse() throws Exception {
		Integer employeeId = 5;
		NurseDto nurseDto = new NurseDto();
		nurseDto.setEmployeeId(employeeId);
		ResponseMessageDto message = new ResponseMessageDto();
		message.setResponse("Record Created Successfully");
		when(nurseService.addNurse(any(NurseDto.class))).thenReturn(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/nurse")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(nurseDto))).andExpect(status().isOk());
		verify(nurseService, times(1)).addNurse(any(NurseDto.class));
	}

	@Test
	public void testgetListOfNurse() throws Exception {
		NurseDto nurseDto = new NurseDto();
		List<NurseDto> list = Arrays.asList(nurseDto);
		when(nurseService.getListOfNurse()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/nurse")
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isOk());
		verify(nurseService, times(1)).getListOfNurse();
	}

	@Test
	public void testgetNurseById() throws Exception {
		Integer employeeId = 17;
		NurseDto nurseDto = new NurseDto();
		nurseDto.setName("Raju");
		nurseDto.setPosition("Physician");
		nurseDto.setSsn(151515152);

		when(nurseService.getNurseDetailsById(employeeId)).thenReturn(nurseDto);

		mockMvc.perform(get("/nurse/{employeeId}", employeeId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Raju"))).andExpect(jsonPath("$.position", is("Physician")))
				.andExpect(jsonPath("$.ssn", is(151515152)));

		verify(nurseService, times(1)).getNurseDetailsById(employeeId);
	}

	@Test
	public void testgetPositionOfNurse() throws Exception {
		Integer employeeId = 17;
		// String position="doctor";
		NurseDto nurseDto = new NurseDto();
		nurseDto.setPosition("doctor");
		when(nurseService.getPositionOfNurse(employeeId)).thenReturn("doctor");
		mockMvc.perform(MockMvcRequestBuilders.get("/nurse/position/{empid}", employeeId)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isOk());
		verify(nurseService, times(1)).getPositionOfNurse(employeeId);
	}

	@Test
	public void testisNurseRegistered() throws Exception {
		Integer employeeId = 18;
		NurseDto nurseDto = new NurseDto();
		nurseDto.setRegistered(true);
		when(nurseService.isNurseRegistered(employeeId)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.get("/nurse/registered/{empid}", employeeId)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isOk());
		verify(nurseService, times(1)).isNurseRegistered(employeeId);
	}

	@Test
	public void testUpdateRegisteredNurse() throws Exception {
		Integer employeeId = 2;
		Boolean registered = true;
		NurseDto nurseDto = new NurseDto();
		nurseDto.setEmployeeId(employeeId);
		nurseDto.setRegistered(registered);

		NurseDto updatednurseDto = new NurseDto();
		updatednurseDto.setEmployeeId(3);
		updatednurseDto.setRegistered(false);
		when(nurseService.updateRegistered(eq(employeeId), any(NurseDto.class))).thenReturn(updatednurseDto);
		mockMvc.perform(put("/nurse/registered/{empid}", employeeId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(nurseDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.employeeId").value(3)).andExpect(jsonPath("$.registered").value(false));
		verify(nurseService, times(1)).updateRegistered(eq(employeeId), any(NurseDto.class));
	}

	@Test
	public void testUpdateSsnNurse() throws Exception {
		Integer employeeId = 2;
		Integer ssn = 10111;
		NurseDto nurseDto = new NurseDto();
		nurseDto.setEmployeeId(employeeId);
		nurseDto.setSsn(ssn);

		NurseDto updatednurseDto = new NurseDto();
		updatednurseDto.setEmployeeId(3);
		updatednurseDto.setSsn(20222);
		when(nurseService.updateSSN(eq(employeeId), any(NurseDto.class))).thenReturn(updatednurseDto);
		mockMvc.perform(put("/nurse/ssn/{empid}", employeeId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(nurseDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.employeeId").value(3)).andExpect(jsonPath("$.ssn").value(20222));
		verify(nurseService, times(1)).updateSSN(eq(employeeId), any(NurseDto.class));
	}

	private byte[] asJsonString(NurseDto nurseDto) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.writeValueAsBytes(objectMapper);

	}

}
