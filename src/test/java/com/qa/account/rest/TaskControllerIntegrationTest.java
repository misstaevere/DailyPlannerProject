package com.qa.account.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.account.dto.TaskDTO;
import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // pick a random port to avoid conflict issues
@AutoConfigureMockMvc // creates an object like Postman
public class TaskControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private TaskRepo repo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectMapper mapper;

	private Task task;
	private long taskId;
	private Task savedTask;
	private Task savedTaskWithID;
	private TaskDTO taskDTO;

	private TaskDTO mapToDTO(Task task) {
		return this.modelMapper.map(task, TaskDTO.class);
	}

	@Before
	public void init() {
		this.repo.deleteAll();
		this.savedTask = new Task(LocalDate.of(2020, 8, 30), LocalTime.of(7, 30), "Make pancakes", "Kitchen");
		this.savedTaskWithID = this.repo.save(this.savedTask);
		this.taskId = this.savedTaskWithID.getTaskId();
		this.taskDTO = this.mapToDTO(savedTaskWithID);
	}

	@Test
	public void testCreate() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.request(HttpMethod.POST, "/task/create");
		builder.contentType(MediaType.APPLICATION_JSON);
		builder.content(this.mapper.writeValueAsString(savedTask));
		builder.accept(MediaType.APPLICATION_JSON);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(taskDTO));
		this.mock.perform(builder).andExpect(matchStatus).andExpect(matchContent);

	}

	@Test
	public void testReadOneSuccess() throws Exception {
		this.mock
				.perform(get("/task/read/" + this.savedTask.getTaskId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(task)))
				.andExpect(status().isOk()).andExpect(content().json(this.mapper.writeValueAsString(taskDTO)));
	}

	@Test
	public void testReadOneFailure() throws Exception {
		this.mock
				.perform(get("/task/read/999999").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(task)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testDelete() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/task/delete/" + this.taskId)).andExpect(status().isNoContent());
	}

	@Test
	public void testRead() throws Exception {
		List<TaskDTO> taskList = new ArrayList<>();
		taskList.add(this.taskDTO);

		String content = this.mock.perform(request(HttpMethod.GET, "/task/read").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(taskList), content);
	}

	@Test
	public void testUpdate() throws Exception {
		Task newTask = new Task(LocalDate.of(2022, 8, 30), LocalTime.of(3, 30), "Dance", "Bar");
		Task updatedTask = new Task(newTask.getTaskDate(), newTask.getTaskTime(), newTask.getTaskName(),
				newTask.getTaskLocation());
		updatedTask.setTaskId(taskId);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/task/update/" + this.taskId).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newTask)))
				.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(this.mapToDTO(updatedTask)), result);
	}
}
