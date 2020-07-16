package com.qa.account.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.account.dto.TaskDTO;
import com.qa.account.persistence.domain.Task;
import com.qa.account.service.TaskService;

@RunWith(MockitoJUnitRunner.class) // this class will only run with mockito library, not the whole spring app
public class TaskControllerUnitTest {

	@InjectMocks
	private TaskController controller;

	@Mock
	private TaskService service;

	private List<Task> taskList;
	private Task savedTask;
	private Task savedTaskWithID;
	private TaskDTO taskDTO;
	final long TASK_ID = 1L;
	private ModelMapper mapper = new ModelMapper();

	private TaskDTO mapToDTO(Task task) {
		return this.mapper.map(task, TaskDTO.class);
	}

	@Before
	public void init() {
		this.taskList = new ArrayList<>();
		this.savedTask = new Task(LocalDate.of(2020, 7, 10), LocalTime.of(12, 10), "Workout", "Gym");
		this.taskList.add(savedTask);
		this.savedTaskWithID = new Task(savedTask.getTaskDate(), savedTask.getTaskTime(), savedTask.getTaskName(),
				savedTask.getTaskLocation());
		this.savedTaskWithID.setTaskId(TASK_ID);
		this.taskDTO = this.mapToDTO(savedTaskWithID);
	}

	@Test
	public void createTest() {
		when(this.service.create(savedTask)).thenReturn(this.taskDTO);
		assertEquals(new ResponseEntity<TaskDTO>(this.taskDTO, HttpStatus.CREATED), this.controller.create(savedTask));
		verify(this.service, times(1)).create(this.savedTask);
	}

	@Test
	public void deleteTest() {
		this.controller.delete(TASK_ID);

		verify(this.service, times(1)).delete(TASK_ID);
	}

	@Test
	public void readIdTest() {
		when(this.service.readId(this.TASK_ID)).thenReturn(this.taskDTO);
		assertEquals(new ResponseEntity<TaskDTO>(this.taskDTO, HttpStatus.OK), this.controller.readOne(this.TASK_ID));
		verify(this.service, times(1)).readId(this.TASK_ID);
	}

	@Test
	public void readTest() {
		when(service.read()).thenReturn(this.taskList.stream().map(this::mapToDTO).collect(Collectors.toList()));
		assertFalse("No tasks found", this.controller.read().getBody().isEmpty());
		verify(service, times(1)).read();
	}

	@Test
	public void updateTest() {

		Task newTask = new Task(LocalDate.of(2020, 7, 1), LocalTime.of(12, 00), "swim", "pool");
		Task updatedTask = new Task(LocalDate.of(2020, 7, 1), LocalTime.of(12, 00), "swim", "pool");
		updatedTask.setTaskId(this.TASK_ID);
		when(this.service.update(newTask, this.TASK_ID)).thenReturn(this.mapToDTO(updatedTask));
		assertEquals(new ResponseEntity<TaskDTO>(this.mapToDTO(updatedTask), HttpStatus.ACCEPTED),
				this.controller.update(this.TASK_ID, newTask));
		verify(this.service, times(1)).update(newTask, this.TASK_ID);
	}
}
