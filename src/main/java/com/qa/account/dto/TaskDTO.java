package com.qa.account.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskDTO {

	private long taskId;
	private LocalDate taskDate;
	private LocalTime taskTime;
	private String taskName;
	private String taskLocation;

	public TaskDTO(LocalDate taskDate, LocalTime taskTime, String taskName, String taskLocation) {
		super();
		this.taskDate = taskDate;
		this.taskTime = taskTime;
		this.taskName = taskName;
		this.taskLocation = taskLocation;
	}

	public TaskDTO() {
		super();
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
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
}
