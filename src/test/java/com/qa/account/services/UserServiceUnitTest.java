package com.qa.account.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.qa.account.dto.UserDTO;
import com.qa.account.persistence.domain.User;
import com.qa.account.persistence.repo.UserRepo;
import com.qa.account.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {

	@InjectMocks // (= new TaskService(repo))
	private UserService service;

	@Mock
	private UserRepo repo;

	@Mock
	private ModelMapper mapper;

	final long USER_ID = 1L;

	private UserDTO userDTO;

	private User testUserWithID;

	private User savedUser;

	@Before
	public void init() {
		this.savedUser = new User("Tommy", "qwerty");
		this.testUserWithID = new User(savedUser.getUsername(), savedUser.getPassword());
		this.testUserWithID.setUserId(USER_ID);
		this.userDTO = new ModelMapper().map(testUserWithID, UserDTO.class);
	}

	@Test
	public void testCreate() {
		Mockito.when(this.repo.save(savedUser)).thenReturn(testUserWithID);
		Mockito.when(this.mapper.map(testUserWithID, UserDTO.class)).thenReturn(userDTO);
		assertEquals(this.userDTO, this.service.create(savedUser));
		Mockito.verify(this.repo, Mockito.times(1)).save(this.savedUser);
	}
}
