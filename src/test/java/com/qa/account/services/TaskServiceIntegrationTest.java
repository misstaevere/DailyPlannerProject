package com.qa.account.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.account.dto.TaskDTO;
import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;
import com.qa.account.service.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceIntegrationTest {

	@Autowired
	private TaskService service;

	@Autowired
	private TaskRepo repo;

	private Task savedTask;

	private Task savedTaskWithID;

	@Autowired
	private ModelMapper mapper;

	private TaskDTO mapToDTO(Task task) {
		return this.mapper.map(task, TaskDTO.class);
	}

	@Before
	public void init() {
		this.savedTask = new Task(LocalDate.of(2020, 11, 11), LocalTime.of(4, 30), "Move house", "Clapton");

		this.repo.deleteAll();
		this.savedTaskWithID = this.repo.save(this.savedTask);
	}

	@Test
	public void testCreate() {
		assertEquals(this.mapToDTO(this.savedTaskWithID), this.service.create(savedTask));
	}

	@Test
	public void testDelete() {
		assertThat(this.service.delete(this.savedTaskWithID.getTaskId())).isFalse();
	}

	@Test
	public void testReadId() {
		assertThat(this.service.readId(this.savedTaskWithID.getTaskId()))
				.isEqualTo(this.mapToDTO(this.savedTaskWithID));
	}

	@Test
	public void testRead() {
		assertThat(this.service.read())
				.isEqualTo(Stream.of(this.mapToDTO(savedTaskWithID)).collect(Collectors.toList()));
	}

	@Test
	public void testUpdate() {
		Task newTask = new Task(LocalDate.of(2020, 12, 12), LocalTime.of(12, 12), "Sleep", "Bed");
		Task updatedTask = new Task(newTask.getTaskDate(), newTask.getTaskTime(), newTask.getTaskName(),
				newTask.getTaskLocation());
		updatedTask.setTaskId(this.savedTaskWithID.getTaskId());

		assertThat(this.service.update(newTask, this.savedTaskWithID.getTaskId()))
				.isEqualTo(this.mapToDTO(updatedTask));
	}

}
