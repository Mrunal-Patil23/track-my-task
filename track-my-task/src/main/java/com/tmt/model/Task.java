package com.tmt.model;

import java.sql.Timestamp;

public class Task {

	int id;
	String taskTitle;
	String taskDescription;
	String taskStatus;// [Inprogress, Completed, Pending, OnHold]
	Timestamp dueDate;
	Timestamp createdAt;
	Timestamp modifiedAt;
	int createdBy;
	int status;

	public Task(int taskId, String taskTitle, String taskDescription, String taskStatus, Timestamp dueDate,
			Timestamp createdAt, Timestamp modifiedAt, int createdBy, int status) {
		this.id = taskId;
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.taskStatus = taskStatus;
		this.dueDate = dueDate;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.createdBy = createdBy;
		this.status = status;
	}

	public Task(String taskTitle, String taskDescription, String taskStatus, Timestamp dueDate) {
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.taskStatus = taskStatus;
		this.dueDate = dueDate;
	}

	public Task(int id) {
		this.id = id;
	}

	public Task() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
