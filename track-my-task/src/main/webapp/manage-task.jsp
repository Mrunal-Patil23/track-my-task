<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TMT :: Manage Task</title>

	
<style type="text/css">
  <%@include file="style.css" %>
 
 </style>  
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%
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
		<p style='color: black; text-align: right;'>Welcome, <c:out value='${loginUserName}' /> &nbsp; [ <a href="index.jsp">Logout</a> ]</p>
	 
	<form action="login" method="post">		

			<table id="allTable" border="0" cellpadding="5">
				<caption align="left">
					<h3 class="pageHeader"><a href="dashboard"><i class="fa fa-home"></i></a> > Manage Task</h3>
				</caption>				 
				<tr>
					<td align="left">
						<a href="manage-task.jsp">Add Task</a> | 
						<a href="dashboard">All Task</a> |
						<a href="dashboard">Task Report</a>
					</td>
				</tr>
			</table>
		</form>		
	</div>
    
    <div align="center">
		<c:if test="${dbTask != null}">
			<form action="updateTask" method="post">
        </c:if>
        <c:if test="${dbTask == null}">
			<form action="addTask" method="post">
        </c:if>
        <table id="frmAddEditTask" border="1" cellpadding="5">
            <caption>
            	<h3>
            		<c:if test="${dbTask != null}">
            			Edit Task
            		</c:if>
            		<c:if test="${dbTask == null}">
            			Add New Task
            		</c:if>
            	</h3>
            </caption>
        		<c:if test="${dbTask != null}">
        			<input type="hidden" name="txtTaskId" value="<c:out value='${dbTask.id}' />" />
        		</c:if>            
            <tr>
                <th>Task Title: </th>
                <td>
                	<input type="text" name="txtTaskTitle" size="45"
                			value="<c:out value='${dbTask.taskTitle}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Task Description: </th>
                <td>
                	<%-- <input type="text" name="txtTaskDescription" size="45"
                			value="<c:out value='${dbTask.taskDescription}' />"
                		/> --%>
                	<textarea rows="5" cols="43" type="text" name="txtTaskDescription" size="45"
                			value="<c:out value='${dbTask.taskDescription}' />"
                		><c:out value='${dbTask.taskDescription}' />
                		</textarea>	
                </td>
            </tr>
            <tr>
                <th>Task Status: </th>
                <td>
                	<select name="sltTaskStatus" required>
						<option value="" <c:if test="${dbTask.taskStatus eq ''}">selected="selected"</c:if>>-- Select --</option>
                		<option value="Completed" <c:if test="${dbTask.taskStatus eq 'Completed'}">selected="selected"</c:if>>Completed</option>
                		<option value="Inprogress" <c:if test="${dbTask.taskStatus eq 'Inprogress'}">selected="selected"</c:if>>Inprogress</option>
                		<option value="Pending" <c:if test="${dbTask.taskStatus eq 'Pending'}">selected="selected"</c:if>>Pending</option>
                		<option value="On-Hold" <c:if test="${dbTask.taskStatus eq 'On-Hold'}">selected="selected"</c:if>>On-Hold</option>
                	</select>    	
                </td>
            </tr>
            <tr>
                <th>Due Date: </th>
                <td>
                	<input type="date" name="txtDueDate" size="45"
                			value="<c:out value='${dbTaskDueDateConverted}' />"
                		/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Submit" /> <input type="reset" value="Reset" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
	
</body>
</html>

