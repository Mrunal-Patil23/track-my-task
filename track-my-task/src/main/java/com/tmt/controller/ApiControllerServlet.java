package com.tmt.controller;

import java.io.IOException;
//import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tmt.dao.TaskDao;
import com.tmt.dao.UserDao;
import com.tmt.model.Task;
import com.tmt.model.User;

public class ApiControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 8069383975271653437L;
	UserDao userDao = new UserDao();
	TaskDao taskDao = new TaskDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/login":
				checkUserLogin(request, response);
				break;
			case "/dashboard":
				showDashboard(request, response);
				break;
			case "/addNewUser":
				addNewUser(request, response);
				break;
			case "/registerNewUserPage":
				showRegisterNewUserPage(request, response);
				break;
			case "/loginPage":
				showLoginPage(request, response);
				break;
			case "/addTask":
				addTask(request, response);
				break;
			case "/editTask":
				showEditTaskForm(request, response);
				break;
			case "/updateTask":
				updateTask(request, response);
				break;
			case "/deleteTask":
				deleteTask(request, response);
				break;
			default:
				showErrorPage(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateTask(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ClassNotFoundException, SQLException, IOException {
		// int loginUserId = (int) request.getSession().getAttribute("loginUserId");

		int taskId = Integer.parseInt(request.getParameter("txtTaskId"));
		String txtTaskTitle = request.getParameter("txtTaskTitle");
		String txtTaskDescription = request.getParameter("txtTaskDescription");
		String txtTaskStatus = request.getParameter("sltTaskStatus");
		String txtDueDate = request.getParameter("txtDueDate");
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = (Date) formatter.parse(txtDueDate);
		Timestamp dueDateInTimestamp = new Timestamp(date.getTime());

		// TODO: convert date & validate it(shouldnot be past date)
//		if (txtDueDate.equals(txtConfirmPassword)) {
//			// System.out.println("Password matched");
//		} else {
//			response.sendRedirect("registerNewUserPage");
//		}

		Task existingTask = new Task();
		existingTask.setId(taskId);
		existingTask.setTaskTitle(txtTaskTitle);
		existingTask.setTaskDescription(txtTaskDescription);
		existingTask.setTaskStatus(txtTaskStatus);
		existingTask.setDueDate(dueDateInTimestamp);
		existingTask.setModifiedAt(new Timestamp(new java.util.Date().getTime()));
		// newTask.setCreatedBy(loginUserId); //dont need to update this
		taskDao.updateTask(existingTask);
		response.sendRedirect("dashboard");
	}

	private void deleteTask(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {

		int taskId = Integer.parseInt(request.getParameter("id"));
		taskDao.deleteTaskById(taskId);
		response.sendRedirect("dashboard");
	}

	private void showEditTaskForm(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		Task existingTask = taskDao.getTaskById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("manage-task.jsp");
		request.setAttribute("dbTask", existingTask);

		String dateStr = new String("");
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		dateStr = sdf.format(existingTask.getDueDate());

		request.setAttribute("dbTaskDueDateConverted", dateStr);

		dispatcher.forward(request, response);
	}

	private void addTask(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException, ParseException {

		// getting login user id from session
		int loginUserId = (int) request.getSession().getAttribute("loginUserId");

		String txtTaskTitle = request.getParameter("txtTaskTitle");
		String txtTaskDescription = request.getParameter("txtTaskDescription");
		String txtTaskStatus = request.getParameter("sltTaskStatus");
		String txtDueDate = request.getParameter("txtDueDate");
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = (Date) formatter.parse(txtDueDate);
		Timestamp dueDateInTimestamp = new Timestamp(date.getTime());

		// TODO: convert date & validate it(shouldnot be past date)
//		if (txtDueDate.equals(txtConfirmPassword)) {
//			// System.out.println("Password matched");
//		} else {
//			response.sendRedirect("registerNewUserPage");
//		}

		Task newTask = new Task(txtTaskTitle, txtTaskDescription, txtTaskStatus, dueDateInTimestamp);
		newTask.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
		newTask.setCreatedBy(loginUserId);

		taskDao.saveTask(newTask);
		response.sendRedirect("dashboard");
		response.sendRedirect("loginPage");
	}

	private void showRegisterNewUserPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String passwordError = null;
		RequestDispatcher dispatcher = request.getRequestDispatcher("register-user.jsp");
		passwordError = "Password does not match";
		request.setAttribute("passwordError", passwordError);
		dispatcher.forward(request, response);
	}

	private void showLoginPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String userAddedFlag = null;
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		userAddedFlag = "You have successfully registered into TMT webapp.";
		request.setAttribute("userAddedFlag", userAddedFlag);
		dispatcher.forward(request, response);
	}

	// validate user from db
	private void checkUserLogin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String username = request.getParameter("txtUsername");
		String password = request.getParameter("txtPassword");

		try {
			User user = userDao.checkUserLogin(username, password);
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUserId", user.getId());
				session.setAttribute("loginUserName", user.getName());

				response.sendRedirect("dashboard");
			} else {
				System.out.println("Login failed");
				response.sendRedirect("errrororo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// show dashboard page with login user & his task list
	private void showDashboard(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {

		// getting login user id from session
		int loginUserId = (int) request.getSession().getAttribute("loginUserId");
		System.out.println("session Id:" + loginUserId);

		// int id = Integer.parseInt(request.getParameter("userId"));
		User dbUser = userDao.getUser(loginUserId);
		request.setAttribute("name", dbUser.getName());
		request.setAttribute("loginUserName", dbUser.getName());
		request.setAttribute("loginUserId", dbUser.getId());

		// fetch all task for this dbUser
		List<Task> dbTaskList = taskDao.getAllTaskByUserId(loginUserId);
		request.setAttribute("dbTaskList", dbTaskList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
		dispatcher.forward(request, response);
	}

	private void addNewUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String txtName = request.getParameter("txtName");
		String txtUsername = request.getParameter("txtUsername");
		String txtPassword = request.getParameter("txtPassword");
		String txtConfirmPassword = request.getParameter("txtConfirmPassword");

		if (txtPassword.equals(txtConfirmPassword)) {
			// System.out.println("Password matched");
			User newUser = new User();
			newUser.setName(txtName);
			newUser.setUsername(txtUsername);
			newUser.setPassword(txtPassword);
			userDao.saveUser(newUser);
			// System.out.println("New user inserted in db");
			response.sendRedirect("loginPage");
		} else {
			response.sendRedirect("registerNewUserPage");
		}

	}

	private void showErrorPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
		dispatcher.forward(request, response);
	}

}
