package com.hms;

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
import com.hms.dto.PatientDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.dummy.JwtDummyAuthentication;
import com.hms.service.impl.PatientService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestPatientController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientService patientService;
	
	public String token() {
		String username = "admin";
		long expiresInMilliseconds = 3600000;
		return JwtDummyAuthentication.generateDummyToken(username, expiresInMilliseconds);

	}

	@Test
	public void testAddPatient() throws Exception {
		Integer ssn = 5;
		PatientDto patientDto = new PatientDto();
		patientDto.setSsn(ssn);
		ResponseMessageDto message = new ResponseMessageDto();
		message.setResponse("Record Created Successfully");
		when(patientService.addPatient(any(PatientDto.class))).thenReturn(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/patient").header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(patientDto))).andExpect(status().isOk());
		verify(patientService, times(1)).addPatient(any(PatientDto.class));
	}

	@Test
	public void testGetAllPatient() throws Exception {

		PatientDto patientDto = new PatientDto();

		List<PatientDto> list = Arrays.asList(patientDto);
		when(patientService.getAllPatient()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/patient").header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isOk());
		verify(patientService, times(1)).getAllPatient();
	}


	
	@Test
	public void testGetInsuranceIdByPatient() throws Exception {
	    Integer ssn = 1;
	    PatientDto patientDto = new PatientDto();
	    patientDto.setSsn(ssn);
	    when(patientService.getInsuranceIdByPatient(ssn)).thenReturn(patientDto);

	    mockMvc.perform(get("/patient/insurance/{patientid}", ssn)
	            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token())) // Corrected header placement
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.ssn").value(1));

	    verify(patientService, times(1)).getInsuranceIdByPatient(ssn);
	}


	@Test
	public void testGetPatientsByPhysicianId() throws Exception {
		Integer pcp = 1;
		PatientDto patientDto = new PatientDto();
		patientDto.setPcp(pcp);
		List<PatientDto> list = Arrays.asList(patientDto);
		when(patientService.getPatientsByPhysicianId(pcp)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/patient/{physicianid}", pcp)
		 .header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isOk());
		verify(patientService, times(1)).getPatientsByPhysicianId(pcp);
	}
	@Test
	public void testGetPatientBySSN() throws Exception{
		  Integer ssn = 12345;
	      PatientDto patientDto = new PatientDto();
	      patientDto.setSsn(ssn);
	      when(patientService.findBySSN(ssn)).thenReturn(patientDto);
			mockMvc.perform(MockMvcRequestBuilders.get("/patient/SSN/{patientid}", ssn)
					 .header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
			.andExpect(status().isOk());
			verify(patientService, times(1)).findBySSN(ssn);
	}

	@Test
	public void testGetPatientBySsnAndPhysicianId() throws Exception {

		Integer ssn = 1;
		Integer pcp = 2;
		PatientDto patientDto = new PatientDto();
		patientDto.setSsn(ssn);
		patientDto.setPcp(pcp);
		when(patientService.getPatientBySsnAndPhysicianId(ssn, pcp)).thenReturn(patientDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/patient/{physicianid}/{patientid}", pcp, ssn)
				 .header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(patientService, times(1)).getPatientBySsnAndPhysicianId(ssn, pcp);
	}

	@Test
	public void testUpdateAddressOfPatient() throws Exception {

		Integer ssn = 100000001;
		String address = "Pune";

		PatientDto patientDto = new PatientDto();
		patientDto.setSsn(ssn);
		patientDto.setPhone("675-8712");
		patientDto.setAddress(address);
		patientDto.setInsuranceID(68476213);
		patientDto.setPcp(1);

		PatientDto updatedPatientDto = new PatientDto();
		updatedPatientDto.setSsn(ssn);
		updatedPatientDto.setAddress("Hinzewadi");

		// Mock the service method
		when(patientService.updateAddressOfPatient(eq(ssn), any(PatientDto.class))).thenReturn(updatedPatientDto);

		mockMvc.perform(put("/patient/address/{ssn}", ssn)
				 .header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(patientDto))).andExpect(status().isOk()).andExpect(jsonPath("$.ssn").value(ssn))
				.andExpect(jsonPath("$.address").value("Hinzewadi"));
		verify(patientService, times(1)).updateAddressOfPatient(eq(ssn), any(PatientDto.class));
	}

	@Test
	public void testUpdatePhoneOfPatient() throws Exception {
		Integer ssn = 100000001;
		String newPhone = "555-8956";

		PatientDto patientDto = new PatientDto();
		patientDto.setSsn(ssn);
		patientDto.setPhone(newPhone);
		patientDto.setAddress("nashilabaad");
		patientDto.setInsuranceID(68476213);
		patientDto.setPcp(1);
		PatientDto updatedPatientDto = new PatientDto();
		updatedPatientDto.setSsn(ssn);
		updatedPatientDto.setPhone("555-8056");
		// Mock the service method
		when(patientService.updatePhoneOfPatient(eq(ssn), any(PatientDto.class))).thenReturn(updatedPatientDto);

		mockMvc.perform(put("/patient/phone/{ssn}", ssn)
				 .header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(patientDto))).andExpect(status().isOk()).andExpect(jsonPath("$.ssn").value(ssn))
				.andExpect(jsonPath("$.phone").value("555-8056"));

		verify(patientService, times(1)).updatePhoneOfPatient(eq(ssn), any(PatientDto.class));
	}

	// Helper method to convert objects to JSON string
	private String asJsonString(Object obj) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
}
