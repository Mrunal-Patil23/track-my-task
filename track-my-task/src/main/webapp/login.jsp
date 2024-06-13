<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TMT :: Login</title>
<style type="text/css">
  <%@include file="style.css" %>
</style>
</head>
<body>
	<center>
		<h1>Track My Task</h1>
		<hr>
	</center>
	<div align="center">
	
	<c:if test="${userAddedFlag != null}"> 
		<p style='color: green'>SUCCESS: <c:out value='${userAddedFlag}' /></p>
	</c:if>
	
	<form action="login" method="post">		

			<table id="allTable" border="1" cellpadding="5">
				<caption>
					<h4>Login</h4>
				</caption>

				<tr>
					<th>Username :</th>
					<td><input type="text" name="txtUsername" size="45"
						placeholder="Enter username" required /></td>
				</tr>
				<tr>
					<th>Password :</th>
					<td><input type="password" name="txtPassword" size="45"
						placeholder="Enter password" required /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Login" /> <input type="reset" value="Reset" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><a href="register-user.jsp">Not
							Registered User?</a></td>
				</tr>
			</table>
		</form>		
	</div>
</body>
</html>
