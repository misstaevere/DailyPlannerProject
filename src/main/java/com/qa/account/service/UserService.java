package com.qa.account.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.account.dto.UserDTO;
import com.qa.account.persistence.domain.User;
import com.qa.account.persistence.repo.UserRepo;

@Service
public class UserService {

	private UserRepo repo;
	private ModelMapper mapper;

	public UserService(UserRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private UserDTO mapToDTO(User saved) {
		return this.mapper.map(saved, UserDTO.class);
	}

	public UserDTO create(User user) {
		User saved = this.repo.save(user);
		return this.mapToDTO(saved);
	}
}
