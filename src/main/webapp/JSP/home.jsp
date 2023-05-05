 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		//allow access only if session exists
		String Email = (String) session.getAttribute("Email");
		String email = null;
		String sessionID = null;
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null)
		{
			for(Cookie cookie: cookies)
			{
				if(cookie.getName().equals("Email")) email = cookie.getValue();
				if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
			}
		}
	%>
	<h3>Hi <%=email %>, Login Successful. Your Session ID = <%=sessionID %></h3>
	
	User=<%=email %><br>
	
<a href="./profile.jsp">Profile</a>	
	<form action = "../LogoutServlet" method="post">
		<input type ="submit" value="Logout">
	</form>

</body>
</html>