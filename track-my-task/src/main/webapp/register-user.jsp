<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TMT :: Register New User</title>
<style type="text/css">
  <%@include file="style.css" %>
</style>
</head>
<body>
	<center>
		<h1>Track My Task</h1>
		<hr>
	</center>
	<!-- <div align="center">
		<table id="allTable" border="0" cellpadding="5">
			<tr>
				<td><a href="login.jsp">Login</a></td>
			</tr>			
		</table>
	</div> -->
	<div align="center">

		<form action="addNewUser" method="post">
			<table id="allTable" border="1" cellpadding="5">
				<caption>
					<h4>New User Registration</h4>
				</caption>	
				
				
				<c:if test="${passwordError != null}">            			
            		<tr>
					<td colspan="2" align="left" style='color: red'>ERROR: <c:out value='${passwordError}' /></td>
				</tr>
            	</c:if>
            					 
				<tr>
					<th>Name :</th>
					<td><input type="text" name="txtName" size="45" placeholder="Enter name"/ required></td>
				</tr>
				<tr>
					<th>Username :</th>
					<td><input type="text" name="txtUsername" size="45" placeholder="Enter username" required/></td>
				</tr>
				<tr>
					<th>Password :</th>
					<td><input type="password" name="txtPassword" size="45" placeholder="Enter password" required/></td>
				</tr>
				<tr>
					<th>Confirm Passowrd :</th>
					<td><input type="password" name="txtConfirmPassword" size="45" placeholder="Re-enter password" required/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Submit" /> <input type="reset"
						value="Reset" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><a href="login.jsp">Already User? Login</a></td>
				</tr>
				
			</table>

		</form>
	</div>
</body>
</html>
