package com.SpringBootSecurity.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.SpringBootSecurity.Controller.StudentContoroller;
import com.SpringBootSecurity.Entity.Student;
import com.SpringBootSecurity.JPARepo.jpaRepository;
import com.SpringBootSecurity.Service.StudentService;
import com.SpringBootSecurity.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(StudentContoroller.class)
public class StudentContorollerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;  // to covert jeson to objet
	
	@MockBean
	StudentService stuService;
	
	@MockBean
	jpaRepository jpaRepositery;
	
	//StudentContoroller studentContor;
	
	@MockBean
	UserService userService;
	
	@Test
	public void testgetAllStudent() throws Exception {
		
		List<Student> listStu= new ArrayList<>();
		listStu.add(new Student (1,"Tina", "Kale","JS","F"));
		listStu.add(new Student (2,"Hana", "Hail","Java","F"));
		listStu.add(new Student (3,"Henok", "Kiflu","PHP","M"));
		
		
		
		Mockito.when(stuService.getAllStudent()).thenReturn(listStu);
		
		String url = "/api/v1/student";
		
		MvcResult mvcResult = mockMvc.perform(get(url).with(user(("test")).password("12345"))).andExpect(status().isOk()).andReturn();
		String ActualJsoneResult=mvcResult.getResponse().getContentAsString();
		System.out.println(ActualJsoneResult);
		
		String excpectJsonRespond= objectMapper.writeValueAsString(listStu);
		
		assertThat(ActualJsoneResult).isEqualToIgnoringCase(excpectJsonRespond);
		
	}
	//@Test
	public void testsaveStudent() throws JsonProcessingException, Exception {
		Student stud =new Student (1,"Tina", "Kale","JS","F");
		Student stud1 =new Student (1,"Tina", "Kale","JS","F");

		Mockito.when(stuService.saveStudent(stud)).thenReturn(stud);
		String url = "/api/v1/student";
		
		MvcResult mvcResult= mockMvc.perform(
				post(url)
				.contentType("application/json")
				.with(user(("test")).password("12345"))
				.content(objectMapper.writeValueAsString(stud1))
				).andExpect(status().isCreated()).andReturn();
						
		String ActualJsoneResult=mvcResult.getResponse().getContentAsString();
		String excpectJsonRespond= objectMapper.writeValueAsString(stud1);
		assertThat(ActualJsoneResult).isEqualToIgnoringCase(excpectJsonRespond);
		
	}
	
}
