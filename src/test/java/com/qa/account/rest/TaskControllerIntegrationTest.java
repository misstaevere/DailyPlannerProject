package com.qa.account.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.account.persistence.domain.Task;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // pick a random port to avoid conflict issues
@AutoConfigureMockMvc // creates an object like Postman
public class TaskControllerIntegrationTest {
	
	@Autowired // will insert the obj in the class
	private MockMvc mockMVC; // 3rd anatation will create this obj
	
	private Task task;
	
	private Task savedTask;
	
	@Autowired
	private ObjectMapper mapper; // used to convert java classes to and from json
	
	@Before
	public void init() {
		this.task = new Task("sing", "shower");
		this.savedTask = new Task(task.getTaskName(), task.getTaskLocation());
		this.savedTask.setTaskId(1L);
	}
	
	@Test
	public void testCreate() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/task/create");
		builder.contentType(MediaType.APPLICATION_JSON); // accepts Json and sends Json back
		builder.accept(MediaType.APPLICATION_JSON);
		builder.content(this.mapper.writeValueAsString(task));
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(savedTask));
		
		this.mockMVC.perform(builder).andExpect(matchStatus).andExpect(matchContent);
	}
	
//	@Test // same as above
//	public void testCreateBuilder() throws Exception {
//		this.mockMVC.perform(post("/task/create")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(this.mapper.writeValueAsString(task))).andExpect(status().isCreated())
//		.andExpect(MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(savedTask)));
//	}
	
	@Test
	public void testRead() {
		
	}
	
	@Test
	public void testUpdate() {
		
	}
	
	@Test
	public void testDelete() {
		
	}
}
