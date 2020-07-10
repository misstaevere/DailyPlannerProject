package com.qa.account.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.qa.account.dto.TaskDTO;
import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;
import com.qa.account.service.TaskService;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceUnitTest {

	@InjectMocks
	private TaskService service;

	@Mock
	private TaskRepo repo;

	@Mock
	private ModelMapper mapper;

	private List<Task> taskList;
	private Task savedTask;
	private Task savedTaskWithID;
	private TaskDTO taskDTO;
	final long TASK_ID = 1L;

	@Before
	public void init() {
		this.taskList = new ArrayList<>();
		this.taskList.add(savedTask);
		this.savedTask = new Task(LocalDate.of(2020, 10, 13), LocalTime.of(10, 10), "My birthday", "Bali");
		this.savedTaskWithID = new Task(savedTask.getTaskDate(), savedTask.getTaskTime(), savedTask.getTaskName(),
				savedTask.getTaskLocation());
		this.savedTaskWithID.setTaskId(TASK_ID);
		this.taskDTO = new ModelMapper().map(savedTaskWithID, TaskDTO.class);
	}

	@Test
	public void createTest() {
		when(this.repo.save(savedTask)).thenReturn(savedTaskWithID);
		when(this.mapper.map(savedTaskWithID, TaskDTO.class)).thenReturn(taskDTO);
		assertEquals(this.taskDTO, this.service.create(savedTask));
		verify(this.repo, times(1)).save(this.savedTask);
	}

	@Test
	public void deleteTest() {
		when(this.repo.existsById(TASK_ID)).thenReturn(true, false);
		this.service.delete(TASK_ID);

		verify(this.repo, times(1)).deleteById(TASK_ID);
		verify(this.repo, times(2)).existsById(TASK_ID);
	}

	@Test
	public void readIdTest() {
		when(this.repo.findById(this.TASK_ID)).thenReturn(Optional.of(this.savedTaskWithID));
		when(this.mapper.map(savedTaskWithID, TaskDTO.class)).thenReturn(taskDTO);

		assertEquals(this.taskDTO, this.service.readId(this.TASK_ID));

		verify(this.repo, times(1)).findById(this.TASK_ID);
	}

	@Test
	public void readTest() {

		when(repo.findAll()).thenReturn(this.taskList);
		when(this.mapper.map(savedTaskWithID, TaskDTO.class)).thenReturn(taskDTO);

		assertFalse("No tasks were found", this.service.read().isEmpty());

		verify(repo, times(1)).findAll();
	}

	@Test
	public void updateTest() {

		Task newTask = new Task(LocalDate.of(2020, 12, 25), LocalTime.of(1, 10), "Christmas", "Australia");
		Task updatedTask = new Task(newTask.getTaskDate(), newTask.getTaskTime(), newTask.getTaskName(),
				newTask.getTaskLocation());
		updatedTask.setTaskId(1L);
		TaskDTO newDTO = new ModelMapper().map(updatedTask, TaskDTO.class);
		when(this.repo.findById(this.TASK_ID)).thenReturn(Optional.of(savedTask));
		when(this.mapper.map(updatedTask, TaskDTO.class)).thenReturn(newDTO);
		when(this.repo.save(updatedTask)).thenReturn(updatedTask);
		assertEquals(updatedTask, this.service.update(newTask, this.TASK_ID));
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedTask);
	}
}
