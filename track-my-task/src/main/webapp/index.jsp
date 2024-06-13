<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TMT</title>
<style type="text/css">
  <%@include file="style.css" %>
</style>
</head>
<body>
<%
//session.invalidate();
//set session variables null on logout
//session=null;
//session.setAttribute("loginUserId", 0);
///session.setAttribute("loginUserName",null);

%>
	<center>
		<h1>Track My Task</h1>
		<hr>
	</center>
	<div align="center">
		<table id="allTable" border="0" cellpadding="5">
			<tr>
				<td><a href="login.jsp">Login</a></td>
			</tr>
			<tr>
				<td><a href="register-user.jsp">Register New User</a></td>
			</tr>
		</table>
	</div>
</body>
</html>
