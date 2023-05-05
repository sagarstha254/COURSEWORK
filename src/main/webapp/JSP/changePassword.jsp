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
<title>changing passwordd</title>
</head>
<body>

	<%
	try {

		String oldEmail = null;
		String Password = null;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
		if (cookie.getName().equals("Email"))
			oldEmail = cookie.getValue();
		if (cookie.getName().equals("Password"))
			Password = cookie.getValue();

			}
		}

		out.println(Password);
		out.println(oldEmail);

		String oldPass = request.getParameter("oldPass");

		String newPass = request.getParameter("newPass");

		// Establish a connection to the database
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/company";
		String user = "root";
		String pass = "";
		con = DriverManager.getConnection(url, user, pass);

		// Create a PreparedStatement object

		if (Password.equals(oldPass)) {
			String query = "UPDATE register_info SET Password=? WHERE Email=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, newPass);
			ps.setString(2, oldEmail);

			// Set the parameters of the prepared statement

			int result = ps.executeUpdate();
			if (result > 0) {
		out.println("<script>alert('Data Updated');</script>");
			}
			// Close the statement and connection
			ps.close();
			con.close();
		} else {

			out.println("<script>alert('Password Error');</script>");

		}
	}

	catch (Exception e) {
		e.printStackTrace();
	}
	%>

</body>
</html>