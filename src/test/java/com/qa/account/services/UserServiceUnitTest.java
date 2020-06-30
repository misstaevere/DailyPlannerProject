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

	@Test
	public void testRead() {
		
	}

	@Test
	public void testUpdate() {
		Mockito.when(this.repo.findById(savedUser.getUserId())).thenReturn(Optional.of(savedUser));

		User newUser = new User("Lola", "000000"); // Task task
		User newUserWithId = new User("Lola", "000000"); // =toUpdate
		newUserWithId.setUserId(savedUser.getUserId());
		
		Mockito.when(this.repo.save(newUserWithId)).thenReturn(newUserWithId);

		assertEquals(newUserWithId, this.service.update(newUser, savedUser.getUserId()));

		Mockito.verify(this.repo, Mockito.times(1)).findById(savedUser.getUserId());
		Mockito.verify(this.repo, Mockito.times(1)).save(newUserWithId);
	}

	@Test
	public void testDelete() {

		Mockito.when(this.repo.existsById(USER_ID)).thenReturn(RESULT);
		
		assertEquals(RESULT, this.service.delete(USER_ID));
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(USER_ID);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(USER_ID);
	}
}
