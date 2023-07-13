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
import com.hms.dto.ProceduresDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.dummy.JwtDummyAuthentication;
import com.hms.service.impl.ProceduresService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestProceduresController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProceduresService proceduresService;
	
	public String token() {
		String username = "admin";
		long expiresInMilliseconds = 3600000;
		return JwtDummyAuthentication.generateDummyToken(username, expiresInMilliseconds);

	}
	
	@Test
	public void testAddNewTreatment() throws Exception{
		Integer code =1;
         
		ProceduresDto proceduresDto = new ProceduresDto();
		proceduresDto.setCode(1); 

	     ResponseMessageDto message = new ResponseMessageDto();
	     message.setResponse("Record Created Successfully");
	     when(proceduresService.addNewTreatment(any(ProceduresDto.class))).thenReturn(message);
	     mockMvc.perform(MockMvcRequestBuilders.post("/procedure")
	    		 .header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
	     .contentType(MediaType.APPLICATION_JSON)
	     .content(new ObjectMapper().writeValueAsString(proceduresDto)))
	     .andExpect(status().isOk());
	     verify(proceduresService, times(1)).addNewTreatment(any(ProceduresDto.class));
	}

	@Test
	public void testGetListOfAvailableTreatment() throws Exception {
		ProceduresDto proceduresDto = new ProceduresDto();
		List<ProceduresDto> list = Arrays.asList(proceduresDto);
		when(proceduresService.getListOfAvailableTreatment()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/procedure")
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isOk());
		verify(proceduresService, times(1)).getListOfAvailableTreatment();
	}

	@Test
	public void testGetCostOfProcedureById() throws Exception {
		Integer code = 1;
		ProceduresDto proceduresDto = new ProceduresDto();
		proceduresDto.setCode(code);
		when(proceduresService.getCostOfProcedureById(code)).thenReturn(proceduresDto);

		mockMvc.perform(get("/procedure/costofprocedure/{id}", code)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value(1));
		verify(proceduresService, times(1)).getCostOfProcedureById(code);
	}

	@Test
	public void testGetCostOfProcedureByName() throws Exception {
		String name = "Reverse Rhinopodoplasty";
		ProceduresDto proceduresDto = new ProceduresDto();
		proceduresDto.setCode(1);
		proceduresDto.setName(name);
		proceduresDto.setCost(1500);
		when(proceduresService.getCostOfProcedureByName(name)).thenReturn(proceduresDto);
		mockMvc.perform(get("/procedure/cost/{name}", name)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code", is(1))).andExpect(jsonPath("$.name", is("Reverse Rhinopodoplasty")))
				.andExpect(jsonPath("$.cost", is(1500.0)));
		verify(proceduresService, times(1)).getCostOfProcedureByName(name);
	}

	@Test
	public void testUpdateCostOfProcedureById() throws Exception {
		
	        Integer code = 2;
	        Integer cost = 12000;

	        ProceduresDto procedureDto = new ProceduresDto();
	        procedureDto.setCode(code);
	        procedureDto.setCost(cost);
	        
	        ProceduresDto updatedprocedureDto = new ProceduresDto();
	        updatedprocedureDto.setCode(code);
	        updatedprocedureDto.setCost(13000);
	    
	        
	        when(proceduresService.updateCostOfProcedureById(eq(code), any(ProceduresDto.class)))
	                .thenReturn(updatedprocedureDto);
	        mockMvc.perform(put("/procedure/cost/{id}", code)
	        		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
	        		.contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(procedureDto)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.code").value(2))
	                .andExpect(jsonPath("$.cost").value(13000));
	        verify(proceduresService, times(1)).updateCostOfProcedureById(eq(code), any(ProceduresDto.class));

	    }
	
	@Test
	public void testUpdateNameOfProcedureById() throws Exception{
		    Integer code = 2;
	        String name = "Harsh";

	        ProceduresDto procedureDto = new ProceduresDto();
	        procedureDto.setCode(code);
	        procedureDto.setName(name);
	        
	        ProceduresDto updatedprocedureDto = new ProceduresDto();
	        updatedprocedureDto.setCode(3);
	        updatedprocedureDto.setName("Hitesh");
	    
	        
	        when(proceduresService.updateNameOfProcedureById(eq(code), any(ProceduresDto.class)))
	                .thenReturn(updatedprocedureDto);
	        mockMvc.perform(put("/procedure/name/{id}", code)
	        		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
	        		.contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(procedureDto)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.code").value(3))
	                .andExpect(jsonPath("$.name").value("Hitesh"));
	        verify(proceduresService, times(1)).updateNameOfProcedureById(eq(code), any(ProceduresDto.class));
		
	}

	private String asJsonString(ProceduresDto procedureDto) {
		ObjectMapper objectMapper = new ObjectMapper();
        try {
			return objectMapper.writeValueAsString(objectMapper);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
