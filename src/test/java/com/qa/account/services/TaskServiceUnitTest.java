package com.qa.account.services;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;
import com.qa.account.service.TaskService;

@RunWith(MockitoJUnitRunner.class) // this class will only run with mockito library, not the whole spring app
public class TaskServiceUnitTest {

	private final Task TASK = new Task("eat cake", "kitchen");

	private Task savedTask;

	@Mock
	private TaskRepo repo;

	@InjectMocks // (= new TaskService(repo))
	private TaskService service;
	
	final long TASK_ID = 1L;
	
	final boolean RESULT = false;

	@Before
	public void init() {
		this.savedTask = new Task(TASK.getTaskName(), TASK.getTaskLocation());
		this.savedTask.setTaskId(1L);
	}

	@Test
	public void testCreate() {
		Mockito.when(this.repo.save(TASK)).thenReturn(savedTask);
		assertEquals(savedTask, service.create(TASK));
		Mockito.verify(this.repo, Mockito.times(1)).save(TASK);
	}

	@Test
	public void testRead() {
		
	}

	@Test
	public void testUpdate() {
		Mockito.when(this.repo.findById(savedTask.getTaskId())).thenReturn(Optional.of(savedTask));

		Task newTask = new Task("swim", "pool"); // Task task
		Task newTaskWithId = new Task("swim", "pool"); // =toUpdate
		newTaskWithId.setTaskId(savedTask.getTaskId());
		
		Mockito.when(this.repo.save(newTaskWithId)).thenReturn(newTaskWithId);

		assertEquals(newTaskWithId, this.service.update(newTask, savedTask.getTaskId()));

		Mockito.verify(this.repo, Mockito.times(1)).findById(savedTask.getTaskId());
		Mockito.verify(this.repo, Mockito.times(1)).save(newTaskWithId);
	}

	@Test
	public void testDelete() {

		Mockito.when(this.repo.existsById(TASK_ID)).thenReturn(RESULT);
		
		assertEquals(RESULT, this.service.delete(TASK_ID));
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(TASK_ID);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(TASK_ID);
	}
}
