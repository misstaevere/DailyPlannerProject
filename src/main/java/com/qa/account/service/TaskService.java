package com.qa.account.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.account.dto.TaskDTO;
import com.qa.account.exceptions.TaskNotFoundException;
import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;

@Service
public class TaskService {

	private TaskRepo repo;
	private Mapper<Task, TaskDTO> mapper;

	@Autowired
	public TaskService(TaskRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = (Task task) -> mapper.map(task, TaskDTO.class);
	}

	public TaskDTO create(Task task) {
		return this.mapper.mapToDTO(this.repo.save(task));
	}

	public List<TaskDTO> read() {
		return this.repo.findAll().stream().map(this.mapper::mapToDTO).collect(Collectors.toList());
	}

	public TaskDTO readId(Long taskId) {
		return this.mapper.mapToDTO(this.repo.findById(taskId).orElseThrow(TaskNotFoundException::new));
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
		if (!this.repo.existsById(taskId)) {
			throw new TaskNotFoundException();
		}
		this.repo.deleteById(taskId);
		return this.repo.existsById(taskId);
	}

}
