package com.hms;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
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
import com.hms.dto.PhysicianDto;
import com.hms.dto.ProceduresDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.dto.TrainedInDto;
import com.hms.dummy.JwtDummyAuthentication;
import com.hms.exception.model.ValidationException;
import com.hms.service.impl.DepartmentService;
import com.hms.service.impl.TrainedInService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestTrainedInController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrainedInService trainedInService;
	
	public String token() {
		String username = "admin";
		long expiresInMilliseconds = 3600000;
		return JwtDummyAuthentication.generateDummyToken(username, expiresInMilliseconds);
	}

	 @Test
		public void testAddCertification() throws Exception{
		 Integer physician = 5;
		 TrainedInDto trainedInDto = new TrainedInDto();
			trainedInDto.setPhysician(physician );
			ResponseMessageDto message = new ResponseMessageDto();
	        message.setResponse("Record Created Successfully");
	        when(trainedInService.addCertification(any(TrainedInDto.class))).thenReturn(message);
			mockMvc.perform(MockMvcRequestBuilders.post("/trained_in")
		    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
		    .contentType(MediaType.APPLICATION_JSON)
	        .content(new ObjectMapper().writeValueAsString(trainedInDto)))
	        .andExpect(status().isOk());
	        verify(trainedInService, times(1)).addCertification(any(TrainedInDto.class));
	        }

    @Test //Negative
         public void testAddCertificationNegative() throws Exception{
    	 Integer physician = 5;
		 TrainedInDto trainedInDto = new TrainedInDto();
		 trainedInDto.setPhysician(null);
		 when(trainedInService.addCertification(trainedInDto)).thenThrow(new ValidationException("Validation failed"));
			// Simulate a failure response
			mockMvc.perform(MockMvcRequestBuilders.post("/trained_in").contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(trainedInDto)));
			// .andExpect(jsonPath("$.response").value("Failed to add patient"));
			verify(trainedInService, times(1)).addCertification(trainedInDto);	
    }
	@Test
	public void testGetListOfProcedure() throws Exception {
		ProceduresDto dto = new ProceduresDto();
		List<Object> mockList = Arrays.asList(dto);
		when(trainedInService.getListOfProcedure()).thenReturn(mockList);
		mockMvc.perform(get("/trained_in/").content(asJsonString(dto))
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isOk());
		verify(trainedInService, times(1)).getListOfProcedure();
	}
	
    @Test //Negative
    public void testGetListOfProcedureNegative() throws Exception{
    	
    }
    
	@Test
	public void testGetListofTreatmentByPhysicianId() throws Exception {
		Integer physicianid = 1;
		ProceduresDto dto = new ProceduresDto();
		List<ProceduresDto> mockList = Arrays.asList(dto);
		when(trainedInService.getListOfTreatmentByPhysicianId(physicianid)).thenReturn(mockList);
		mockMvc.perform(get("/trained_in/treatment/{physicianid}", physicianid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.content(asJsonString(dto)))
				.andExpect(status().isOk());
		verify(trainedInService, times(1)).getListOfTreatmentByPhysicianId(physicianid);
	}
	
	@Test //Negative
	public void testGetListofTreatmentByPhysicianIdNegative() throws Exception {
		
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
	public void testGetListofPhysicianByProcedureId() throws Exception {
		Integer procedureid = 1;
		PhysicianDto dto = new PhysicianDto();
		List<PhysicianDto> mockList = Arrays.asList(dto);
		when(trainedInService.getListOfPhysicianByProcedureId(procedureid)).thenReturn(mockList);
		mockMvc.perform(get("/trained_in/physician/{procedureid}", procedureid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.content(asJsonString(dto)))
				.andExpect(status().isOk());
		verify(trainedInService, times(1)).getListOfPhysicianByProcedureId(procedureid);
	}
	
	@Test //Negative
	public void testGetListofPhysicianByProcedureIdNegative() throws Exception {
		
		
	}

	@Test
	public void testGetListOfProceduresExpiredInMonth() throws Exception {
		ProceduresDto dto = new ProceduresDto();
		List<ProceduresDto> mockList = Arrays.asList(dto);
		when(trainedInService.getListOfProceduresOfOneMonthExpiry()).thenReturn(mockList);
		mockMvc.perform(get("/trained_in/expiredsooncerti")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.content(asJsonString(dto))).andExpect(status().isOk());
		verify(trainedInService, times(1)).getListOfProceduresOfOneMonthExpiry();
	}
	
	@Test //Negative
	public void testGetListOfProceduresExpiredInMonthNegative() throws Exception {
		
	}

	@Test
	public void testUpdateCertification() throws Exception {
		Integer physicianid = 1;
		Integer procedureid = 2;
		LocalDateTime expiry = LocalDateTime.now();
		when(trainedInService.updateCertificationExpiry(eq(physicianid), eq(procedureid), eq(expiry))).thenReturn(true);
		mockMvc.perform(put("/trained_in/certificationexpiry/{physicianid}/{procedureid}", physicianid, procedureid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON).content("\"" + expiry.toString() + "\""))
				.andExpect(status().isOk());
		verify(trainedInService, times(1)).updateCertificationExpiry(physicianid, procedureid, expiry);
	}
	
	@Test //Negative
	public void testUpdateCertificationNegative() throws Exception {
		
	}
}
