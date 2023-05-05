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
</head>
<body>

	<%
	try {

		String oldEmail = null;
		String UserName = null;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
		if (cookie.getName().equals("Email")) {
			oldEmail = cookie.getValue();
			if(cookie.getName().equals("UserName")) UserName = cookie.getValue();

			
			
			}
		}

			}
		
	

		String newUser = request.getParameter("newUser");


		// Establish a connection to the database
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/company";
		String user = "root";
		String pass = "";
		con = DriverManager.getConnection(url, user, pass);
		
		// Create a PreparedStatement object
		
		
		String query="UPDATE register_info SET User_Name=? WHERE Email=?";
		PreparedStatement ps = con.prepareStatement(query);		
		ps.setString(1, newUser);
		ps.setString(2, oldEmail);
		

		// Set the parameters of the prepared statement
		

		int result = ps.executeUpdate();
		if (result > 0) {
			out.println("<script>alert('Data Updated');</script>");
		}
		// Close the statement and connection
		ps.close();
		con.close();
	}

	catch (Exception e) {
		e.printStackTrace();
	}
	%>

</body>
</html>