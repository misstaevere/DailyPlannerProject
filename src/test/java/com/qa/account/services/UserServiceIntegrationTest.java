package com.qa.account.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.account.dto.UserDTO;
import com.qa.account.persistence.domain.User;
import com.qa.account.persistence.repo.UserRepo;
import com.qa.account.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {
	
	@Autowired
	private UserService service;

	@Autowired
	private UserRepo repo;

	private User savedUser;

	private User savedUserWithID;

	@Autowired
	private ModelMapper mapper;

	private UserDTO mapToDTO(User user) {
		return this.mapper.map(user, UserDTO.class);
	}

	@Before
	public void init() {
		this.savedUser = new User("Phil", "Secret");

		this.repo.deleteAll();
		this.savedUserWithID = this.repo.save(this.savedUser);
	}

	@Test
	public void testCreate() {
		assertEquals(this.mapToDTO(this.savedUserWithID), this.service.create(savedUser));
	}

}
