<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>TMT :: Dashboard</title>

	
<style type="text/css">
  <%@include file="style.css" %>
 
 </style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">  
<%
//set session variables
//session.setAttribute("loginUserId", (int)request.getAttribute("loginUserId"));
//session.setAttribute("loginUserName",(String)request.getAttribute("loginUserName"));
int loginUserId=(int)session.getAttribute("loginUserId");
String loginUserName=(String)session.getAttribute("loginUserName");
%>
  
</head>
<body>

	<center>
		<h1>Track My Task</h1>
		<hr>
	</center>
	<div align="center">
		<p style='color: black; text-align: right;'>
			Welcome,
			<c:out value='${loginUserName}' />
			&nbsp; [ <a href="index.jsp">Logout</a> ]
		</p>

		<table id="allTable" border="0" cellpadding="5">
			<caption>
				<h3 class="pageHeader"><a href="dashboard"><i class="fa fa-home"></i></a> > Dashboard</h3>
			</caption>
			<tr>
				<td align="left"><a href="manage-task.jsp">Add Task</a> | <a
					href="dashboard">All Task</a> | <a href="dashboard">Task
						Report</a></td>
			</tr>
		</table>

	</div>
	<div align="center">
        <table id="allTable" border="1" cellpadding="5">
            <%-- <caption><h2>My Task Details</h2></caption> --%>
             <tr>
                <th style="text-align: left;" colspan="6"><b>My Task Details</b></th>
            </tr>
            <tr>
                <th>SrNo.</th>
                <th>Title</th>
                <th>Description</th>
                <th>Status</th>
                <th>Due Date</th>
                <th>Actions</th>
            </tr>
            
            <c:if test="${empty dbTaskList}">
	            <tr>
	                 <td align="center" colspan="6"><br><br>Your task list is empty!<br><br><br></td>
	            </tr>
            </c:if>
            
            <% int i=1;%>
            <c:forEach var="task" items="${dbTaskList}">
                <tr>
                    <td align="center"><%=i %></td>
                    <td><c:out value="${task.taskTitle}" /></td>
                    <td><c:out value="${task.taskDescription}" /></td>
                    <td align="center"><c:out value="${task.taskStatus}" /></td>
                    <td align="center">
                    <fmt:formatDate value="${task.dueDate}" type="date" dateStyle="short" pattern="dd-MM-yyyy"  var="formattedDate" />
                    <c:out value="${formattedDate}" /></td>
                    
                    <td align="center">
                    	<a href="editTask?id=<c:out value='${task.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="deleteTask?id=<c:out value='${task.id}' />">Delete</a>                    	
                    </td>
                </tr>
                <% i++; %>
            </c:forEach>
        </table>
    </div>	
	
</body>
</html>

