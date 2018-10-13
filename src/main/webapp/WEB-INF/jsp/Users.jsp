<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Users</title>
	</head>
	<body>
		<h1 style="color: red;">OAuth 2.0 Client exercise</h1>
		<h2 style="color: grey;">Get Users from resource server</h2>

		<div id="getUsers">
			<form:form action="http://localhost:8080/oauth/authorize" method="post" modelAttribute="emp">
				<p>
				<label>Enter User Id</label> <br/><br/>
				&nbsp;&nbsp;<input style="width: 250px;" type="text" name="response_type" value="code" /> <br/>
				&nbsp;&nbsp;<input style="width: 250px;" type="text" name="client_id" value="client123" /> <br/>
				&nbsp;&nbsp;<input style="width: 250px;" type="text" name="redirect_uri" value="http://localhost:8090/user/showUsers" /><br/>
				&nbsp;&nbsp;<input style="width: 250px;" type="text" name="scope" value="read" /> <br/><br/>
				<input type="SUBMIT" value="Get Users" />
			</form:form>
		</div>
	</body>
</html>