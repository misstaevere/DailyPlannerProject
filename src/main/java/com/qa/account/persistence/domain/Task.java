package com.qa.account.persistence.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

// ORM - obj relational mapper - converts java obj into tables (Jpa - java persistence API) uses constr to create the objects and then getters and setters to stick the values in
@Entity
public class Task {

	@Id
	@GeneratedValue
	private long taskId;

	@NotNull
	private LocalDate taskDate;

	@NotNull
	private LocalTime taskTime;

	@Column(nullable = false)
	private String taskName;

	@Column(nullable = false)
	private String taskLocation;

	@ManyToOne(targetEntity = User.class)
	private User user;

	public Task(LocalDate taskDate, LocalTime taskTime, String taskName, String taskLocation) {
		super();
		this.taskDate = taskDate;
		this.taskTime = taskTime;
		this.taskName = taskName;
		this.taskLocation = taskLocation;
	}

	public Task() { // entities must have a default constructor
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskDate == null) {
			if (other.taskDate != null)
				return false;
		} else if (!taskDate.equals(other.taskDate))
			return false;
		if (taskId != other.taskId)
			return false;
		if (taskLocation == null) {
			if (other.taskLocation != null)
				return false;
		} else if (!taskLocation.equals(other.taskLocation))
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		if (taskTime == null) {
			if (other.taskTime != null)
				return false;
		} else if (!taskTime.equals(other.taskTime))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public LocalDate getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(LocalDate taskDate) {
		this.taskDate = taskDate;
	}

	public LocalTime getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(LocalTime taskTime) {
		this.taskTime = taskTime;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskLocation() {
		return taskLocation;
	}

	public void setTaskLocation(String taskLocation) {
		this.taskLocation = taskLocation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskDate=" + taskDate + ", taskTime=" + taskTime + ", taskName=" + taskName
				+ ", taskLocation=" + taskLocation + ", user=" + user + "]";
	}

}
