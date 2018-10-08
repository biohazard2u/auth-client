<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Show Users</title>
	</head>
	
	<body>
		<h1 style="color: red;">OAuth 2.0 Client exercise</h1>
		<h3 style="color: red;">Show All Users</h3>
		<ul>
			<c:forEach var="listValue" items="">
				<li></li>
			</c:forEach>
		</ul>
	</body>
</html>