package com.qa.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.account.exceptions.UserNotFoundException;
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

	public List<User> read() {
		return this.repo.findAll();
	}

	public User read(long userId) {
		return this.repo.findById(userId).orElseThrow(() -> new UserNotFoundException());
	}

	public User update(User user, Long userId) {
		User toUpdate = this.repo.findById(userId).orElseThrow(() -> new UserNotFoundException());

		toUpdate.setUsername(user.getUsername());
		toUpdate.setPassword(user.getPassword());

		return this.repo.save(toUpdate);
	}

	public boolean delete(Long userId) {
		this.repo.deleteById(userId);
		return this.repo.existsById(userId);
	}

}
