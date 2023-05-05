<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%!Connection con = null;%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="../JS/countersign.js"></script>

</head>
<body>
	<%
	//allow access only if session exists
	String Email = (String) session.getAttribute("Email");
	String email = null;
	String UserName = null;


	String sessionID = null;

	Cookie[] cookies = request.getCookies();

	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("Email")) email = cookie.getValue();
			if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
			if(cookie.getName().equals("UserName")) UserName = cookie.getValue();
		}
	}
	%>
	<h3>Username :<%=UserName%>
</h3>

	<form action="./changeEmail.jsp" method="post">
		<p>
			Change UserName: <input type="text" name="newUser" required>
		</p>
		<input type="submit" value="Change UserName">


	</form>
	<form action="./changePassword.jsp" method="post">
		<p>
			Enter old Password: <input type="text" name="oldPass" required>
		
			Enter new Password: <input type="text" name="newPass" required>
		</p>
		<input type="submit" value="Change Password">


	</form>

	<form action="../LogoutServlet" method="post">
		<input type="submit" value="Logout">
	</form>

</body>
</html>