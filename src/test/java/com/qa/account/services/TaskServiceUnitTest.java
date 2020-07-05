package com.qa.account.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;

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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.qa.account.dto.TaskDTO;
import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;
import com.qa.account.service.TaskService;

@RunWith(MockitoJUnitRunner.class) // this class will only run with mockito library, not the whole spring app
public class TaskServiceUnitTest {

	@InjectMocks // (= new TaskService(repo))
	private TaskService service;

	@Mock
	private TaskRepo repo;

	@Mock
	private ModelMapper mapper;

	final long TASK_ID = 1L;

	private TaskDTO taskDTO;

	private Task savedTaskWithID;

	private Task savedTask;

	private List<Task> taskList;

	@Before
	public void init() {
		this.taskList = new ArrayList<>();
		this.taskList.add(savedTask);
		this.savedTask = new Task(LocalDate.of(2020, 7, 10), LocalTime.of(12, 10), "Workout", "Gym");
		this.savedTaskWithID = new Task(savedTask.getTaskDate(), savedTask.getTaskTime(), savedTask.getTaskName(),
				savedTask.getTaskLocation());
		this.savedTaskWithID.setTaskId(TASK_ID);
		this.taskDTO = new ModelMapper().map(savedTaskWithID, TaskDTO.class);
	}

	@Test
	public void testCreate() {
		Mockito.when(this.repo.save(savedTask)).thenReturn(savedTaskWithID);
		Mockito.when(this.mapper.map(savedTaskWithID, TaskDTO.class)).thenReturn(taskDTO);
		assertEquals(this.taskDTO, this.service.create(savedTask));
		Mockito.verify(this.repo, Mockito.times(1)).save(this.savedTask);
	}

	@Test
	public void testRead() {
		Mockito.when(repo.findAll()).thenReturn(this.taskList);
		Mockito.when(this.mapper.map(savedTaskWithID, TaskDTO.class)).thenReturn(taskDTO);
		assertFalse("No tasks here", this.service.read().isEmpty());
		Mockito.verify(repo, times(1)).findAll();
	}

	@Test
	public void testReadId() {
		Mockito.when(this.repo.findById(this.TASK_ID)).thenReturn(Optional.of(this.savedTaskWithID));
		Mockito.when(this.mapper.map(savedTaskWithID, TaskDTO.class)).thenReturn(taskDTO);

		assertEquals(this.taskDTO, this.service.readId(this.TASK_ID));

		Mockito.verify(this.repo, times(1)).findById(this.TASK_ID);		
	}

	@Test
	public void testUpdate() {
		Mockito.when(this.repo.findById(savedTask.getTaskId())).thenReturn(Optional.of(savedTask));

		Task newTask = new Task(LocalDate.of(2020, 7, 1), LocalTime.of(12, 00), "swim", "pool"); // Task task
		Task newTaskWithId = new Task(LocalDate.of(2020, 7, 1), LocalTime.of(12, 00), "swim", "pool"); // =toUpdate
		newTaskWithId.setTaskId(savedTask.getTaskId());

		Mockito.when(this.repo.save(newTaskWithId)).thenReturn(newTaskWithId);

		assertEquals(newTaskWithId, this.service.update(newTask, savedTask.getTaskId()));

		Mockito.verify(this.repo, Mockito.times(1)).findById(savedTask.getTaskId());
		Mockito.verify(this.repo, Mockito.times(1)).save(newTaskWithId);
	}

	@Test
	public void testDelete() {

		Mockito.when(this.repo.existsById(TASK_ID)).thenReturn(true, false);

		this.service.delete(TASK_ID);

		Mockito.verify(this.repo, Mockito.times(1)).deleteById(TASK_ID);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(TASK_ID);
	}
}
