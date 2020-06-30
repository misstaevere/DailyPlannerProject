package com.qa.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.account.exceptions.TaskNotFoundException;
import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;

@Service
public class TaskService {

	private TaskRepo repo;

	public TaskService(TaskRepo repo) {
		super();
		this.repo = repo;

	}

	public Task create(Task task) {
		return this.repo.save(task);
	}

	public List<Task> read() {
		return this.repo.findAll();
	}

	public Task read(long taskId) {
		return this.repo.findById(taskId).orElseThrow(() -> new TaskNotFoundException());
	}

	public Task update(Task task, Long taskId) {
		Task toUpdate = this.repo.findById(taskId).orElseThrow(() -> new TaskNotFoundException());

		toUpdate.setTaskDate(task.getTaskDate());
		toUpdate.setTaskTime(task.getTaskTime());
		toUpdate.setTaskName(task.getTaskName());
		toUpdate.setTaskLocation(task.getTaskLocation());

		return this.repo.save(toUpdate);
	}

	public boolean delete(Long taskId) {
		this.repo.deleteById(taskId);
		return this.repo.existsById(taskId);
	}

}
