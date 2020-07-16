package com.qa.account.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.account.dto.UserDTO;
import com.qa.account.persistence.domain.User;
import com.qa.account.persistence.repo.UserRepo;

@Service
public class UserService {

	private UserRepo repo;
	private Mapper<User, UserDTO> mapper;

	@Autowired
	public UserService(UserRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = (User user) -> mapper.map(user, UserDTO.class);
	}

	public UserDTO create(User user) {
		return this.mapper.mapToDTO(this.repo.save(user));
	}
}
