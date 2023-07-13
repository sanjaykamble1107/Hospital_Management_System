package com.hms.controller;
import static org.hamcrest.CoreMatchers.is;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.auth.JwtDummyAuthentication;
import com.hms.dto.DepartmentDto;
import com.hms.dto.PhysicianDto;
import com.hms.dto.ResponseMessageDto;
import com.hms.exception.model.DepartmentNotFoundException;
import com.hms.exception.model.ValidationException;
import com.hms.service.impl.DepartmentService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = "ADMIN")
public class TestDepartmentController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;
	
	public String token() {
		String username = "admin";
		long expiresInMilliseconds = 3600000;
		return JwtDummyAuthentication.generateDummyToken(username, expiresInMilliseconds);

	}

	@Test
	public void testAddDepartment() throws Exception {
		Integer departmentId = 5;
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentId(departmentId);
		ResponseMessageDto message = new ResponseMessageDto();
		message.setResponse("Record Created Successfully");
		when(departmentService.addDepartment(any(DepartmentDto.class))).thenReturn(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/department")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(departmentDto)))
				.andExpect(status().isOk());
		verify(departmentService, times(1)).addDepartment(any(DepartmentDto.class));
	}
	
	@Test //Negative
	public void testAddDepartmentNotFounde() throws Exception{
		Integer departmentId = 5;
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentId(null);
		when(departmentService.addDepartment(departmentDto)).thenThrow(new ValidationException("Validation failed"));
		// Simulate a failure response
		mockMvc.perform(MockMvcRequestBuilders.post("/department")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(departmentDto)));
		// .andExpect(jsonPath("$.response").value("Failed to add patient"));
		verify(departmentService, times(1)).addDepartment(departmentDto);
	}

	@Test
	public void testGetDepartments() throws Exception {
		DepartmentDto depDto = new DepartmentDto();
		List<DepartmentDto> list = Arrays.asList(depDto);
		when(departmentService.getDepartments()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/department//")
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(departmentService, times(1)).getDepartments();
	}
	
	@Test // Negative
	public void testGetDepartmentsNotFound() throws Exception{
		
		when(departmentService.getDepartments()).thenThrow(new  DepartmentNotFoundException("No Department Found"));
		mockMvc.perform(MockMvcRequestBuilders.get("/department/")
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isNotFound());
		verify(departmentService, times(1)).getDepartments();	
		}
	
	@Test
	public void testGetDepartmentById() throws Exception {
		Integer departmentId = 17;
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setName("Raju");
		departmentDto.setHead(12);
		when(departmentService.getDepartmentById(departmentId)).thenReturn(departmentDto);
		mockMvc.perform(get("/department/{departmentId}", departmentId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Raju"))).andExpect(jsonPath("$.head", is(12)));
		verify(departmentService, times(1)).getDepartmentById(departmentId);
	}
	
	@Test //Negative
	public void testGetDepartmentByIdNotFound() throws Exception{
		Integer departmentId = 17;
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setName("Raju");
		departmentDto.setHead(12);
		when(departmentService.getDepartmentById(departmentId)).thenThrow(new  DepartmentNotFoundException("No Department Found"));
		mockMvc.perform(get("/department/{departmentId}", departmentId)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
		.andExpect(status().isNotFound());
				//.andExpect(jsonPath("$.name", is("Raju"))).andExpect(jsonPath("$.head", is(12)));
		verify(departmentService, times(1)).getDepartmentById(departmentId);
		
	}

	@Test
	public void testGetHeadOfDepartment() throws Exception {
		Integer departmentId = 17;
		PhysicianDto deptDto = new PhysicianDto();
		when(departmentService.getHeadOfDepartment(departmentId)).thenReturn(deptDto);
		mockMvc.perform(get("/department/dept/head/{departmentId}", departmentId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(departmentService, times(1)).getHeadOfDepartment(departmentId);
	}
	
	@Test //Negative
	public void testGetHeadOfDepartmentNotFound() throws Exception {
		Integer departmentId = 17;
		PhysicianDto deptDto = new PhysicianDto();
		when(departmentService.getHeadOfDepartment(departmentId)).thenThrow(new  DepartmentNotFoundException("No Department Found"));
		mockMvc.perform(get("/department/dept/head/{departmentId}", departmentId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(departmentService, times(1)).getHeadOfDepartment(departmentId);
		
	}

	@Test
	public void testGetDepartmentsByHeadId() throws Exception {
		Integer head = 2;
		DepartmentDto dDto = new DepartmentDto();
		List<DepartmentDto> list = Arrays.asList(dDto);
		when(departmentService.getDepartmentsByHeadId(head)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/department/head/{head}", head)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(departmentService, times(1)).getDepartmentsByHeadId(head);
	}
	
	@Test //Negative 
	public void testGetDepartmentsByHeadIdNotFound() throws Exception {
		Integer head = 2;
		DepartmentDto dDto = new DepartmentDto();
		List<DepartmentDto> list = Arrays.asList(dDto);
		when(departmentService.getDepartmentsByHeadId(head)).thenThrow(new  DepartmentNotFoundException("No Department Found"));
		mockMvc.perform(MockMvcRequestBuilders.get("/department/head/{head}", head)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(departmentService, times(1)).getDepartmentsByHeadId(head);
	}

	@Test
	public void testGetListOfCertificationByDeptId() throws Exception {
		Integer deptid = 2;
		DepartmentDto dpDto = new DepartmentDto();
		List<Object> list = Arrays.asList(dpDto);
		when(departmentService.getListOfCertitificatioByDeptId(deptid)).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/department/headcertification/{deptid}", deptid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk());
		verify(departmentService, times(1)).getListOfCertitificatioByDeptId(deptid);
	}
	
	@Test //Negative
	public void testGetListOfCertificationByDeptIdNotFound() throws Exception {
		Integer deptid = 2;
		DepartmentDto dpDto = new DepartmentDto();
		List<Object> list = Arrays.asList(dpDto);
		when(departmentService.getListOfCertitificatioByDeptId(deptid)).thenThrow(new  DepartmentNotFoundException("No Department Found"));
		mockMvc.perform(MockMvcRequestBuilders.get("/department/headcertification/{deptid}", deptid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
		verify(departmentService, times(1)).getListOfCertitificatioByDeptId(deptid);
	}

	@Test
	public void testPysicianIsHeadOfDepartment() throws Exception {
		Integer physicianid = 1;
		Boolean pysicianIsHeadOfDepartment = true;
		when(departmentService.physicianIsHeadOfDepartment(physicianid)).thenReturn(pysicianIsHeadOfDepartment);
		mockMvc.perform(get("/department/check/{physicianid}", physicianid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(String.valueOf(pysicianIsHeadOfDepartment)));
	}
	
	@Test //Negative  
	public void testPysicianIsHeadOfDepartNotFound() throws Exception {
		Integer physicianid = 1;
		Boolean pysicianIsHeadOfDepartment = true;
		when(departmentService.physicianIsHeadOfDepartment(physicianid)).thenThrow(new  DepartmentNotFoundException("No Department Found"));
		mockMvc.perform(get("/department/check/{physicianid}", physicianid)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token()))
				.andExpect(status().isNotFound());
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(content().string(String.valueOf(pysicianIsHeadOfDepartment)));
	}
	
	@Test
	public void testUpdateDepartmentHeadId() throws Exception {
		Integer departmentId = 2;
		Integer head = 12;
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentId(departmentId);
		departmentDto.setHead(head);
		
		DepartmentDto updateddepartmentDto = new DepartmentDto();
		updateddepartmentDto.setDepartmentId(departmentId);
	//	updateddepartmentDto.setName("jjj");
		updateddepartmentDto.setHead(123333);
		when(departmentService.updateDepartmentHeadId(eq(departmentId), any(DepartmentDto.class)))
				.thenReturn(updateddepartmentDto);
		mockMvc.perform(put("/department/update/headid/{deptid}", departmentId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(departmentDto)))
				.andExpect(status().isOk());
				//.andExpect(jsonPath("$.departmentId").value(2));
				//.andExpect(jsonPath("$.head").value(123333));
		verify(departmentService, times(1)).updateDepartmentHeadId(eq(departmentId), any(DepartmentDto.class));
	}
	@Test //Negative
	public void testUpdateDepartmentHeadIdNotFound() throws Exception {
		Integer departmentId = 2;
		Integer head = 12;
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentId(departmentId);
		departmentDto.setHead(head);
		departmentDto.setName("Harsh");
		
		DepartmentDto updateddepartmentDto = new DepartmentDto();
		updateddepartmentDto.setDepartmentId(departmentId);
		updateddepartmentDto.setHead(123333);
		
		when(departmentService.updateDepartmentHeadId(eq(departmentId), any(DepartmentDto.class)))
				.thenThrow(new  DepartmentNotFoundException("No Department Found"));
		mockMvc.perform(put("/department/update/headid/{deptid}", departmentId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(departmentDto)))
				.andExpect(status().isNotFound());
				//.andExpect(jsonPath("$.departmentId").value(2));
				//.andExpect(jsonPath("$.head").value(123333));
		verify(departmentService, times(1)).updateDepartmentHeadId(eq(departmentId), any(DepartmentDto.class));
	}
	
	@Test
	public void testUpdateNameDepartment() throws Exception {
		Integer departmentId = 2;
		String name = "HR";
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentId(departmentId);
		departmentDto.setName(name);
		
		DepartmentDto updateddepartmentDto = new DepartmentDto();
		updateddepartmentDto.setDepartmentId(departmentId);
		updateddepartmentDto.setName("jjj");
		when(departmentService.updateNameDepartment(eq(departmentId), any(DepartmentDto.class)))
				.thenReturn(updateddepartmentDto);
		mockMvc.perform(put("/department/update/deptname/{deptid}", departmentId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(departmentDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.departmentId").value(2))
				.andExpect(jsonPath("$.name").value("jjj"));
		verify(departmentService, times(1)).updateNameDepartment(eq(departmentId), any(DepartmentDto.class));
	}
		
	@Test //Negative
	public void testUpdateNameDepartmentNotFound() throws Exception {
		Integer departmentId = 2;
		String name = "HR";
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentId(departmentId);
		departmentDto.setName(name);
		departmentDto.setHead(4);
		
		DepartmentDto updateddepartmentDto = new DepartmentDto();
		updateddepartmentDto.setDepartmentId(departmentId);
		updateddepartmentDto.setName("jjj");
		updateddepartmentDto.setHead(4);
		when(departmentService.updateNameDepartment(eq(departmentId), any(DepartmentDto.class)))
		.thenThrow(new  DepartmentNotFoundException("No Department Found"));
		mockMvc.perform(put("/department/update/deptname/{deptid}", departmentId)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(departmentDto)))
				.andExpect(status().isNotFound());
		verify(departmentService, times(1)).updateNameDepartment(eq(departmentId), any(DepartmentDto.class));
		
		
	}
		public static String asJsonString(Object object) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(object);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

	}
		}


	
	


