package com.qa.account.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
}
