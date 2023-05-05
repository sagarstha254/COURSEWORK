<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>	
<%@page import="java.sql.Connection"%>


<%!Connection con = null; %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
		try
		{	

		String Email = request.getParameter("Email");
		String User_Name = request.getParameter("User_Name");
		String Password = request.getParameter("Password");
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/company";
		String user = "root";
		String pass = "";
		con = DriverManager.getConnection(url, user, pass);
		
		String query = "Select * from register_info";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet result = ps.executeQuery();
	
		
				
		while(result.next()){
				%>
				
					<%out.println(result.getString("Email"));%>
					<%out.println(result.getString("User_Name"));%>
					<%out.println(result.getString("Password"));%>
<%-- 					<td><%out.println(encryption.decrypt(result.getString("password"),"my-secret-key-hi")); %></td>
 --%>					
				
				<%
				}
				
				
			
			
			
				}
				catch(Exception e)
				{
					out.println(e.getMessage());
				}
				finally
				{
					con.close();
			 	}
				%>
</body>
</html>