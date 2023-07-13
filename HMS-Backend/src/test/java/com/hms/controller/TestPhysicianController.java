package com.hms.controller;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.auth.JwtDummyAuthentication;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.exception.model.PhysicianNotFoundException;
import com.hms.exception.model.ValidationException;
import com.hms.service.impl.PhysicianService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestPhysicianController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PhysicianService physicianService;

	public String token() {
		String username = "admin";
		long expiresInMilliseconds = 3600000;
		return JwtDummyAuthentication.generateDummyToken(username, expiresInMilliseconds);
	}

	@Test
	public void testAddPhysician() throws Exception {
		Integer employeeid = 5;
		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setSsn(employeeid);
		physicianDto.setEmployeeId(employeeid);
		physicianDto.setName("Harsh");
		physicianDto.setPosition("Surgery");

		ResponseMessageDto message = new ResponseMessageDto();
		message.setResponse("Record Created Successfully");
		when(physicianService.addPhysician(any(PhysicianDto.class))).thenReturn(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/physician")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(physicianDto)))
				.andExpect(status().isOk());
		verify(physicianService, times(1)).addPhysician(any(PhysicianDto.class));
	}

	@Test // Negative
	public void testAddPhysicianValidationFailed() throws Exception {
		Integer employee = 5;
		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setEmployeeId(null);
		physicianDto.setName("Harsh");
		physicianDto.setPosition("Physician");
		physicianDto.setSsn(123456789);
		when(physicianService.addPhysician(physicianDto)).thenThrow(new ValidationException("Validation failed"));
		// Simulate a failure response
		mockMvc.perform(MockMvcRequestBuilders.post("/physician").header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(physicianDto)))
				.andExpect(status().isBadRequest());
		// .andExpect(jsonPath("$.response").value("Failed to add patient"));
		verify(physicianService, times(1)).addPhysician(physicianDto);
	}

	@Test
	public void testGetListOfPhysician() throws Exception {
		PhysicianDto physicianDto = new PhysicianDto();
		List<PhysicianDto> list = Arrays.asList(physicianDto);
		when(physicianService.getListOfPhysicianDto()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/physician").header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(physicianService, times(1)).getListOfPhysicianDto();
	}

	@Test  //Negative
	  public void testGetListOfPhysicianNegative() throws Exception{
			when(physicianService.getListOfPhysicianDto()).thenThrow(new PhysicianNotFoundException("No Physician Found!"));
			mockMvc.perform(MockMvcRequestBuilders.get("/physician")
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
			.andExpect(status().isNotFound());
			verify(physicianService, times(1)).getListOfPhysicianDto();  
	  }

	@Test
	public void testGetPhysicianByName() throws Exception {
		String name = "Harsh";
		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setEmployeeId(11);
		physicianDto.setPosition("Physician");
		physicianDto.setSsn(123456789);

		when(physicianService.getPhysicianByName(name)).thenReturn(physicianDto);

		mockMvc.perform(get("/physician/name/{name}", name).header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk()).andExpect(jsonPath("$.employeeId", is(11)))
				.andExpect(jsonPath("$.position", is("Physician"))).andExpect(jsonPath("$.ssn", is(123456789)));
		verify(physicianService, times(1)).getPhysicianByName(name);
	}

	@Test // Negative
	public void testGetPhysicianByNameNegative() throws Exception {
		String name = "Harry";
		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setName(name);
		physicianDto.setEmployeeId(11);
		physicianDto.setPosition("Physician");
		physicianDto.setSsn(123456789);
		when(physicianService.getPhysicianByName(name))
				.thenThrow(new PhysicianNotFoundException("No Physician Found!"));

		mockMvc.perform(get("/physician/name/{name}", name).header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(physicianService, times(1)).getPhysicianByName(name);
	}

	@Test
	public void testGetPhysicianByPosition() throws Exception {
		String position = "Analyst";
		PhysicianDto physicianDto = new PhysicianDto();
		List<PhysicianDto> list = Arrays.asList(physicianDto);

		when(physicianService.getPhysicianByPosition(position)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/physician/position/{position}", position)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())).andExpect(status().isOk());
		verify(physicianService, times(1)).getPhysicianByPosition(position);

	}

	@Test // Negative
	public void testGetPhysicianByPositionNegative() throws Exception {
		String position = "Analyst";
		PhysicianDto physicianDto = new PhysicianDto();
		// List<PhysicianDto> list = Arrays.asList(physicianDto);
		when(physicianService.getPhysicianByPosition(position))
				.thenThrow(new PhysicianNotFoundException("No Physician Found!"));
		mockMvc.perform(MockMvcRequestBuilders.get("/physician/position/{position}", position)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())).andExpect(status().isNotFound());
		verify(physicianService, times(1)).getPhysicianByPosition(position);
	}

	@Test
	public void testGetPhysicianByEmpid() throws Exception {
		Integer employeeId = 17;
		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setName("Raju");
		physicianDto.setPosition("Physician");
		physicianDto.setSsn(151515152);

		when(physicianService.getPhysicianByEmpid(employeeId)).thenReturn(physicianDto);
		mockMvc.perform(
				get("/physician/{employeeId}", employeeId).header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Raju")))
				.andExpect(jsonPath("$.position", is("Physician"))).andExpect(jsonPath("$.ssn", is(151515152)));

		verify(physicianService, times(1)).getPhysicianByEmpid(employeeId);

	}

	@Test // Negative
	public void testGetPhysicianByEmpidNegative() throws Exception {
		Integer employeeId = 17;
		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setEmployeeId(employeeId);
		physicianDto.setPosition("Cardiologist");
		physicianDto.setSsn(112);
		physicianDto.setName("Prabir");

		when(physicianService.getPhysicianByEmpid(employeeId))
				.thenThrow(new PhysicianNotFoundException("No Physician Found!"));

		mockMvc.perform(
				get("/physician/{employeeId}", employeeId).header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(physicianService, times(1)).getPhysicianByEmpid(employeeId);
	}

	@Test
	public void testUpdatePhysicianPosition() throws Exception {
		Integer employeeId = 1;
		String position = "Nurse";

		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setEmployeeId(employeeId);
		physicianDto.setPosition(position);
		physicianDto.setSsn(112);
		physicianDto.setName("Prabir");
		PhysicianDto updatedphysicianDto = new PhysicianDto();
		updatedphysicianDto.setEmployeeId(employeeId);
		updatedphysicianDto.setPosition("Doctor");
		// Mock the service method
		when(physicianService.updatePhysicianPosition(eq(employeeId), any(PhysicianDto.class)))
				.thenReturn(updatedphysicianDto);
		mockMvc.perform(put("/physician/update/position/{employeeId}", employeeId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(physicianDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.employeeId").value(employeeId))
				.andExpect(jsonPath("$.position").value("Doctor"));
		verify(physicianService, times(1)).updatePhysicianPosition(eq(employeeId), any(PhysicianDto.class));

	}

	@Test // Negative
	public void testUpdatePhysicianPositionNegative() throws Exception {
		Integer employeeId = 1;
		String position = "Nurse";
		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setEmployeeId(employeeId);
		physicianDto.setPosition(position);
		physicianDto.setSsn(112);
		physicianDto.setName("Prabir");

		PhysicianDto updatedPhysicianDto = new PhysicianDto();
		updatedPhysicianDto.setEmployeeId(employeeId);
		updatedPhysicianDto.setPosition("Doctor");

		when(physicianService.updatePhysicianPosition(eq(employeeId), any(PhysicianDto.class)))
				.thenThrow(new PhysicianNotFoundException("No Physician Found!"));
		mockMvc.perform(put("/physician/update/position/{employeeId}", employeeId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(physicianDto))).andExpect(status().isNotFound());
		verify(physicianService, times(1)).updatePhysicianPosition(eq(employeeId), any(PhysicianDto.class));
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
	public void testUpdatePhysicianName() throws Exception {
		Integer employeeId = 1;
		String name = "Harsh";

		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setEmployeeId(employeeId);
		physicianDto.setPosition(name);
		physicianDto.setSsn(112);
		physicianDto.setName("Prabir");

		PhysicianDto updatedphysicianDto = new PhysicianDto();
		updatedphysicianDto.setEmployeeId(employeeId);
		updatedphysicianDto.setName("Jitesh");
		// Mock the service method
		when(physicianService.updatePhysicianName(eq(employeeId), any(PhysicianDto.class)))
				.thenReturn(updatedphysicianDto);
		mockMvc.perform(put("/physician/update/name/{employeeId}", employeeId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(physicianDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.employeeId").value(employeeId)).andExpect(jsonPath("$.name").value("Jitesh"));
		verify(physicianService, times(1)).updatePhysicianName(eq(employeeId), any(PhysicianDto.class));
	}

	@Test // Negative
	public void testUpdatePhysicianNameNotFound() throws Exception {
		Integer employeeId = 1;
		String position = "head";
		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setEmployeeId(employeeId);
		physicianDto.setPosition(position);
		physicianDto.setSsn(112);
		physicianDto.setName("Prabir");

		PhysicianDto updatedPhysicianDto = new PhysicianDto();
		updatedPhysicianDto.setEmployeeId(employeeId);
		updatedPhysicianDto.setName("ice-cream");

		when(physicianService.updatePhysicianName(eq(employeeId), any(PhysicianDto.class)))
				.thenThrow(new PhysicianNotFoundException("No Physician Found!"));

		mockMvc.perform(put("/physician//update/name/{employeeId}", employeeId).contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()).content(asJsonString(physicianDto)))
				.andExpect(status().isNotFound());
		verify(physicianService, times(1)).updatePhysicianName(eq(employeeId), any(PhysicianDto.class));
	}

	@Test
	public void testUpdateSsnOfPhysician() throws Exception {
		Integer employeeId = 2;
		Integer ssn = 1222222;

		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setEmployeeId(employeeId);
		physicianDto.setSsn(ssn);

		PhysicianDto updatedphysicianDto = new PhysicianDto();
		updatedphysicianDto.setEmployeeId(employeeId);
		updatedphysicianDto.setSsn(123333);

		when(physicianService.updateSsnOfPhysician(eq(employeeId), any(PhysicianDto.class)))
				.thenReturn(updatedphysicianDto);
		mockMvc.perform(put("/physician/update/ssn/{employeeId}", employeeId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(physicianDto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.employeeId").value(employeeId)).andExpect(jsonPath("$.ssn").value(123333));
		verify(physicianService, times(1)).updateSsnOfPhysician(eq(employeeId), any(PhysicianDto.class));
	}

	@Test // Negative
	public void testUpdateSsnOfPhysicianNegative() throws Exception {
		Integer employeeId = 2;
		Integer ssn = 1222222;

		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setEmployeeId(employeeId);
		physicianDto.setSsn(ssn);
		physicianDto.setName("Harsh");
		physicianDto.setPosition("MD");

		PhysicianDto updatedphysicianDto = new PhysicianDto();
		updatedphysicianDto.setEmployeeId(employeeId);
		updatedphysicianDto.setSsn(123333);

		when(physicianService.updateSsnOfPhysician(eq(employeeId), any(PhysicianDto.class)))
				.thenThrow(new PhysicianNotFoundException("No Physician Found!"));

		mockMvc.perform(put("/physician/update/ssn/{employeeId}", employeeId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(physicianDto))).andExpect(status().isNotFound());
		verify(physicianService, times(1)).updateSsnOfPhysician(eq(employeeId), any(PhysicianDto.class));
	}
}
