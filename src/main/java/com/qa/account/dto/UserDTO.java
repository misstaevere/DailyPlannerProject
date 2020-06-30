package com.qa.account.dto;

import java.util.List;

public class UserDTO {

	private long userId;
	private String username;
	private String password;
	private List<TaskDTO> tasks;

	public UserDTO(String username, String password, List<TaskDTO> tasks) {
		super();
		this.username = username;
		this.password = password;
		this.tasks = tasks;
	}

	public UserDTO() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TaskDTO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}
}
