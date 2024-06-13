package com.tmt.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tmt.db.DbConnection;
import com.tmt.model.Task;

public class TaskDao {

	DbConnection conn = new DbConnection();

//	public boolean saveTask(Task newTask) throws SQLException, ClassNotFoundException {
//		String sql = "INSERT INTO tbl_addtask(name, duedate, status) VALUES (?, ?, ?)";
//		PreparedStatement stmt = conn.getConn().prepareStatement(sql);
//		stmt.setString(1, newTask.getName());
//		stmt.setString(2, newTask.getDescription());
//		
//		rowInserted = stmt.executeUpdate() > 0;
//		stmt.close();		
//		return rowInserted;
//
//	}

	public List<Task> getAllTaskByUserId(int id) throws SQLException, ClassNotFoundException {
		System.out.println("In getAllTaskByUserId()");
		List<Task> taskList = new ArrayList<>();
		String sql = "SELECT * FROM tbl_task_details WHERE status=1 AND createdBy=?";
		// System.out.println("SQL " +sql);
		PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int taskId = rs.getInt("id");
			String taskTitle = rs.getString("taskTitle");
			String taskDescription = rs.getString("taskDescription");
			String taskStatus = rs.getString("taskStatus");
			Timestamp dueDate = rs.getTimestamp("dueDate");
			Timestamp createdAt = rs.getTimestamp("createdAt");
			Timestamp modifiedAt = rs.getTimestamp("modifiedAt");
			int createdBy = rs.getInt("createdBy");
			int status = rs.getInt("status");

			Task newTask = new Task(taskId, taskTitle, taskDescription, taskStatus, dueDate, createdAt, modifiedAt,
					createdBy, status);
			taskList.add(newTask);
		}

		rs.close();
		stmt.close();
		return taskList;
	}

	public void saveTask(Task newTask) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO tbl_task_details(taskTitle, taskDescription, taskStatus, dueDate, createdBy, status) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
		stmt.setString(1, newTask.getTaskTitle());
		stmt.setString(2, newTask.getTaskDescription());
		stmt.setString(3, newTask.getTaskStatus());
		stmt.setTimestamp(4, newTask.getDueDate());
		stmt.setInt(5, newTask.getCreatedBy());
		stmt.setInt(6, 1);

		boolean rowInserted = stmt.executeUpdate() > 0;
		stmt.close();

	}

	public Task getTaskById(int id) throws SQLException, ClassNotFoundException {
		Task task = null;
		String sql = "SELECT * FROM tbl_task_details WHERE id = ? AND status=1 ";
		PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			int taskId = rs.getInt("id");
			String taskTitle = rs.getString("taskTitle");
			String taskDescription = rs.getString("taskDescription");
			String taskStatus = rs.getString("taskStatus");
			Timestamp dueDate = rs.getTimestamp("dueDate");

			task = new Task(taskTitle, taskDescription, taskStatus, dueDate);
			task.setId(taskId);
		}
		rs.close();
		stmt.close();
		return task;
	}

//	public boolean deleteTask(Task task) throws SQLException, ClassNotFoundException {
//		String sql = "DELETE FROM tbl_task_details WHERE id = ? ";
//
//		PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
//		stmt.setInt(1, task.getId());
//
//		boolean rowDeleted = stmt.executeUpdate() > 0;
//		stmt.close();
//		return rowDeleted;
//	}

	public void updateTask(Task task) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE tbl_task_details SET taskTitle=?, taskDescription=?, taskStatus=?, dueDate=?, modifiedAt=? WHERE id= ? AND status=1";
		PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
		stmt.setString(1, task.getTaskTitle());
		stmt.setString(2, task.getTaskDescription());
		stmt.setString(3, task.getTaskStatus());
		stmt.setTimestamp(4, task.getDueDate());
		stmt.setTimestamp(5, task.getModifiedAt());
		stmt.setInt(6, task.getId());

		try {
			boolean rowUpdated = stmt.executeUpdate() > 0;
			if (rowUpdated) {
				System.out.println("TaskDao.updateTask(): Task details updated successfully.");
			} else {
				System.out.println("TaskDao.updateTask(): Task update failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		stmt.close();
	}

	public void deleteTaskById(int taskId) throws SQLException, ClassNotFoundException {
		// String sql = "DELETE FROM tbl_task_details WHERE id = ? ";
		// Dont dohard delete instead of that just update status column as deleted eg
		// status=0 means record deleted

		int status = 0;// means mark as deleted
		String sql = "UPDATE tbl_task_details SET status=?, modifiedAt=? WHERE id=?";
		PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
		stmt.setInt(1, status);
		stmt.setTimestamp(2, (new Timestamp(new java.util.Date().getTime())));
		stmt.setInt(3, taskId);

		boolean rowDeleted = stmt.executeUpdate() > 0;
		if (rowDeleted) {
			System.out.println("Record deleted successfully.");
		} else {
			System.out.println("Failed to delete task. taskId=" + taskId);
		}
	}
}
