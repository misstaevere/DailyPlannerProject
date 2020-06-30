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

import com.qa.account.persistence.domain.User;
import com.qa.account.service.UserService;

@RestController // pre-configures everything to JSON format
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	private UserService service;

	@Autowired // process where Spring goes and finds the bean with the correct type
				// (taskservice)
	public UserController(UserService service) { // creating a new taskservice throwing a taskservice bean into it
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<User> create(@RequestBody User user) { // @RB will send a json Task in with(time,date,name...)
																	// and create a task obj
		return new ResponseEntity<User>(this.service.create(user), HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<List<User>> read() {
		return new ResponseEntity<List<User>>(this.service.read(), HttpStatus.OK);
	}
	
	@GetMapping("/read/{userId}")
	public ResponseEntity<User> readOne(@PathVariable Long userId) {
		return ResponseEntity.ok(this.service.read(userId));
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody User user) {
		return new ResponseEntity<User>(this.service.update(user, userId), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> delete(@PathVariable Long userId) {
		if (this.service.delete(userId)) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
