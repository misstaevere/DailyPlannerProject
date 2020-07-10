package com.qa.account.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.account.dto.UserDTO;
import com.qa.account.persistence.domain.User;
import com.qa.account.persistence.repo.UserRepo;
import com.qa.account.rest.UserController;
import com.qa.account.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {

	@Mock
	private UserRepo repo;

	@Mock
	private UserService service;

	@InjectMocks
	private UserController controller;

	private List<User> userList;
	private User savedUser;
	private User savedUserWithID;
	private UserDTO userDTO;
	final long USER_ID = 1L;
	private ModelMapper mapper = new ModelMapper();

	private UserDTO mapToDTO(User user) {
		return this.mapper.map(user, UserDTO.class);
	}

	@Before
	public void init() {
		this.userList = new ArrayList<>();
		this.savedUser = new User("Kevin", "qwerty");
		this.userList.add(savedUser);
		this.savedUserWithID = new User(savedUser.getUsername(), savedUser.getPassword());
		this.savedUserWithID.setUserId(USER_ID);
		this.userDTO = this.mapToDTO(savedUserWithID);
	}

	@Test
	public void createTest() {
		when(this.service.create(savedUser)).thenReturn(this.userDTO);
		assertEquals(new ResponseEntity<UserDTO>(this.userDTO, HttpStatus.CREATED), this.controller.create(savedUser));
		verify(this.service, times(1)).create(this.savedUser);
	}
}
