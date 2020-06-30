package com.qa.account.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.account.dto.TaskDTO;
import com.qa.account.exceptions.TaskNotFoundException;
import com.qa.account.persistence.domain.Task;
import com.qa.account.persistence.repo.TaskRepo;

@Service
public class TaskService {

	private TaskRepo repo;
	private ModelMapper mapper;

	public TaskService(TaskRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private TaskDTO mapToDTO(Task task) {
		return this.mapper.map(task, TaskDTO.class);
	}

	public TaskDTO create(Task task) {
//		return this.repo.save(task);
		Task saved = this.repo.save(task);
		return this.mapToDTO(saved);
	}

	public List<TaskDTO> read() {
		List<TaskDTO> dtos = new ArrayList<>();
		for (Task task : this.repo.findAll()) {
			dtos.add(this.mapToDTO(task));
		}
		return dtos;
//		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList()); // same as above
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
