package com.qa.account.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskDTO {

	private long taskId;
	private LocalDate taskDate;
	private LocalTime taskTime;
	private String taskName;
	private String taskLocation;

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

	@Override
	public String toString() {
		return "TaskDTO [taskId=" + taskId + ", taskDate=" + taskDate + ", taskTime=" + taskTime + ", taskName="
				+ taskName + ", taskLocation=" + taskLocation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskDate == null) ? 0 : taskDate.hashCode());
		result = prime * result + (int) (taskId ^ (taskId >>> 32));
		result = prime * result + ((taskLocation == null) ? 0 : taskLocation.hashCode());
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		result = prime * result + ((taskTime == null) ? 0 : taskTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskDTO other = (TaskDTO) obj;
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
		return true;
	}
}
