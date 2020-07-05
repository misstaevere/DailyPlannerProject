package com.qa.account.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.account.persistence.domain.User;
import com.qa.account.persistence.repo.UserRepo;
import com.qa.account.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {

	private final User USER = new User("Tommy", "qwerty");

	private User savedUser;

	@Mock
	private UserRepo repo;

	@InjectMocks // (= new UserService(repo))
	private UserService service;
	
	final long USER_ID = 1L;
	
	final boolean RESULT = false;

	@Before
	public void init() {
		this.savedUser = new User(USER.getUsername(), USER.getPassword());
		this.savedUser.setUserId(1L);
	}

	@Test
	public void testCreate() {
		Mockito.when(this.repo.save(USER)).thenReturn(savedUser);
		assertEquals(savedUser, service.create(USER));
		Mockito.verify(this.repo, Mockito.times(1)).save(USER);
	}
}
