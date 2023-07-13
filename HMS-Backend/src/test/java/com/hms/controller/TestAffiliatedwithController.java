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
import com.hms.dto.Affiliated_WithDto;
import com.hms.dto.DepartmentDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.exception.model.AffiliatedWithNotFoundException;
import com.hms.exception.model.PhysicianNotFoundException;
import com.hms.service.impl.AffiliatedWithService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestAffiliatedwithController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AffiliatedWithService affiliatedWithService;
	
	public String token() {
		String username = "admin";
		long expiresInMilliseconds = 3600000;
		return JwtDummyAuthentication.generateDummyToken(username, expiresInMilliseconds);

	}
	
	 @Test
		public void testSaveAffiliatedWith() throws Exception{
  		   Integer physician = 5;
		    Affiliated_WithDto affiliated_WithDto = new Affiliated_WithDto();
			affiliated_WithDto.setPhysician(physician );
			ResponseMessageDto message = new ResponseMessageDto();
	        message.setResponse("Record Created Successfully");
	        when(affiliatedWithService.saveAffiliated(any(Affiliated_WithDto.class))).thenReturn(message);
			mockMvc.perform(MockMvcRequestBuilders.post("/affiliated_with/post")
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
		    .contentType(MediaType.APPLICATION_JSON)
	        .content(new ObjectMapper().writeValueAsString(affiliated_WithDto)))
	        .andExpect(status().isOk());
	        verify(affiliatedWithService, times(1)).saveAffiliated(any(Affiliated_WithDto.class));
	        }
	 
	 @Test //Negative   
		public void testSaveAffiliatedWithNotFound() throws Exception{
		   Integer physician = 5;
		    Affiliated_WithDto affiliated_WithDto = new Affiliated_WithDto();
			affiliated_WithDto.setPhysician(physician );
			ResponseMessageDto message = new ResponseMessageDto();
	       // message.setResponse("Record Created Successfully");
	        when(affiliatedWithService.saveAffiliated(any(Affiliated_WithDto.class))).
	        thenThrow(new PhysicianNotFoundException("No Physician or Department Found!"));
			mockMvc.perform(MockMvcRequestBuilders.post("/affiliated_with/post")
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
		    .contentType(MediaType.APPLICATION_JSON)
	        .content(new ObjectMapper().writeValueAsString(affiliated_WithDto)))
	        .andExpect(status().isNotFound());
	        verify(affiliatedWithService, times(1)).saveAffiliated(any(Affiliated_WithDto.class));
	        }
      
	@Test
	public void testGetPhysicianListByDepartmentId() throws Exception {
		Integer deptid = 2;
		PhysicianDto dDto = new PhysicianDto();
		List<PhysicianDto> list = Arrays.asList(dDto);
        when(affiliatedWithService.getPhysicianByDepartmentId(deptid)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/affiliated_with/physicians/{deptid}", deptid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isOk());
		verify(affiliatedWithService, times(1)).getPhysicianByDepartmentId(deptid);
		}
     
	@Test //Negative
	public void testGetPhysicianListByDepartmentIdNotFound() throws Exception {
		Integer deptid = 2;
		PhysicianDto dDto = new PhysicianDto();
		List<PhysicianDto> list = Arrays.asList(dDto);
        when(affiliatedWithService.getPhysicianByDepartmentId(deptid)).thenThrow
        (new AffiliatedWithNotFoundException("No Affiliation Found with Given Physician ID and Department ID"));
		mockMvc.perform(MockMvcRequestBuilders.get("/affiliated_with/physicians/{deptid}", deptid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isNotFound());
		verify(affiliatedWithService, times(1)).getPhysicianByDepartmentId(deptid);
		}

	@Test
	public void testGetDepartmentListByPhysicianId() throws Exception {
		Integer physicianid = 2;
		DepartmentDto dpDto = new DepartmentDto();
		List<DepartmentDto> list = Arrays.asList(dpDto);
        when(affiliatedWithService.getDepartmentsByPhysicianId(physicianid)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/affiliated_with/department/{physicianid}", physicianid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isOk());
		verify(affiliatedWithService, times(1)).getDepartmentsByPhysicianId(physicianid);
        }
	
	@Test //Negative
	public void testGetDepartmentListByPhysicianIdNotFound() throws Exception{
		Integer physicianid = 2;
		DepartmentDto dpDto = new DepartmentDto();
		List<DepartmentDto> list = Arrays.asList(dpDto);
        when(affiliatedWithService.getDepartmentsByPhysicianId(physicianid)).thenThrow
        (new AffiliatedWithNotFoundException("No Affiliation Found with Given Physician ID and Department ID"));
		mockMvc.perform(MockMvcRequestBuilders.get("/affiliated_with/department/{physicianid}", physicianid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isNotFound());
		verify(affiliatedWithService, times(1)).getDepartmentsByPhysicianId(physicianid);
		
	}
	
	 @Test
	    public void testCountPhysicianByDepartment() throws Exception {
	        Integer deptid = 1;
	        Integer expectedCount = 5;
            when(affiliatedWithService.countPhysicianByDepartment(deptid)).thenReturn(expectedCount);
            mockMvc.perform(get("/affiliated_with/countphysician/{deptid}", deptid)
            		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(String.valueOf(expectedCount)));
        }
	 
	 @Test //Negative
	    public void testCountPhysicianByDepartmentNotFound() throws Exception {
	        Integer deptid = 1;
	       Integer expectedCount = 5;
	        Affiliated_WithDto dto = new Affiliated_WithDto();
	        dto.setDepartment(deptid);
	        dto.setPhysician(4);
	        dto.setPrimaryAffiliation(true);
         when(affiliatedWithService.countPhysicianByDepartment(deptid)).thenThrow
         (new AffiliatedWithNotFoundException("No Affiliation Found with Given Physician ID and Department ID"));
         mockMvc.perform(get("/affiliated_with/countphysician/{deptid}", deptid)
         		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
         .andExpect(status().isNotFound());
     }
	 
	 @Test
	    public void testGetPrimaryAffilationByPhysicianId() throws Exception {
	         Integer physicianid = 1;
	 		 List<Boolean> primaryAffiliation = Arrays.asList(true);
	         when(affiliatedWithService.getPrimaryAffiliation(physicianid)).thenReturn(primaryAffiliation);
             mockMvc.perform(get("/affiliated_with//primary/{physicianid}", physicianid)
            		 .header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(content().json("[true]"));
             }
	 
	 @Test //Negative
	    public void testGetPrimaryAffilationByPhysicianIdNotFound() throws Exception {
	         Integer physicianid = 1;
	 		 List<Boolean> primaryAffiliation = Arrays.asList(true);
	         when(affiliatedWithService.getPrimaryAffiliation(physicianid)).thenThrow
	         (new AffiliatedWithNotFoundException("No Affiliation Found with Given Physician ID and Department ID"));
          mockMvc.perform(get("/affiliated_with//primary/{physicianid}", physicianid)
         		 .header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
          		 .andExpect(status().isNotFound())
          		 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
          }

	    @Test
	    public void testUpdatePrimaryAffiliation() throws Exception {
	        Integer physicianId = 1;
	        Integer deptId = 2;
	        Boolean primary = true;

	        when(affiliatedWithService.updatePrimaryAffiliationByPhysicianIdAndDepartmentId(eq(physicianId), eq(deptId), eq(primary)))
	                .thenReturn(true);

	        mockMvc.perform(put("/affiliated_with/primary/{physicianid}/{deptid}", physicianId, deptId)
	        		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("true"))
	                .andExpect(status().isOk());
          verify(affiliatedWithService, times(1)).updatePrimaryAffiliationByPhysicianIdAndDepartmentId(physicianId, deptId, primary);
	    }
	    
	    @Test //Negative
	    public void testUpdatePrimaryAffiliationNotFound() throws Exception {
	        Integer physicianId = 1;
	        Integer deptId = 2;
	        Boolean primary = true;

	        when(affiliatedWithService.updatePrimaryAffiliationByPhysicianIdAndDepartmentId(eq(physicianId), eq(deptId), eq(primary)))
	                .thenThrow(new AffiliatedWithNotFoundException("No Affiliation Found with Given Physician ID and Department ID"));
	        mockMvc.perform(put("/affiliated_with/primary/{physicianid}/{deptid}", physicianId, deptId)
	        		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("true"))
	                .andExpect(status().isNotFound());
          verify(affiliatedWithService, times(1)).updatePrimaryAffiliationByPhysicianIdAndDepartmentId(physicianId, deptId, primary);
	    }

	 }
  
	
	





