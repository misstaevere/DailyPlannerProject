package com.qa.account.service;

import org.springframework.stereotype.Service;

import com.qa.account.persistence.domain.User;
import com.qa.account.persistence.repo.UserRepo;

@Service
public class UserService {

	private UserRepo repo;

	public UserService(UserRepo repo) {
		super();
		this.repo = repo;
	}

	public User create(User user) {
		return this.repo.save(user);
	}
}
