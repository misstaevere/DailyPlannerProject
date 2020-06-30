package com.qa.account.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.account.persistence.domain.Task;
import com.qa.account.service.TaskService;

@RestController // pre-configures everything to JSON format
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

	private TaskService service;

	@Autowired // process where Spring goes and finds the bean with the correct type
				// (taskservice)
	public TaskController(TaskService service) { // creating a new taskservice throwing a taskservice bean into it
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Task> create(@RequestBody Task task) { // @RB will send a json Task in with(time,date,name...)
																	// and create a task obj
		return new ResponseEntity<Task>(this.service.create(task), HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<List<Task>> read() {
		return new ResponseEntity<List<Task>>(this.service.read(), HttpStatus.OK);
	}

	@PutMapping("/update/{taskId}")
	public ResponseEntity<Task> update(@PathVariable Long taskId, @RequestBody Task task) {
		return new ResponseEntity<Task>(this.service.update(task, taskId), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<?> delete(@PathVariable Long taskId) {
		if (this.service.delete(taskId)) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
