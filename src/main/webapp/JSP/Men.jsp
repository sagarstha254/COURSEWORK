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
<link rel="stylesheet" href="../CSS/Navbar.css">

<link rel="stylesheet" href="../CSS/Product.css">
<meta charset="UTF-8">
<title>Daraz-inspired Produ</title>

</head>
<body>


	<nav>
		<div id="header">
			<img id="logo" src="../IMAGES/Website/logo.png" alt="Logo">
			<h1>ELEVATE COMFORT</h1>
		</div>
		<div class="main">
			<div class="second">
				<form>
					<input type="text" name="search" placeholder="Search...">
					<button type="submit">Search</button>
				</form>
			</div>
			<div class="inner">
				<a href="./index.jsp">Home</a> <a href="./Product.jsp">Product</a> <a
					class="active" href="#">Men</a> <a href="./Women.jsp">women</a> <a
					href="./Kid.jsp">Kids</a>
			</div>
			<div class="cart">
				<a href="cart.html"><img style="border-radius: 50%;"
					src="../IMAGES/Website/cart.jpg" width="25px" height="25px"></a>
			</div>
			<div class="login">
				<a href="../HTML/Register.html">Sign up </a> <a
					href="../JSP/profile.jsp">Profile </a>
			</div>
		</div>
	</nav>
	<%
		try {
			//Connection con = Database.getConn();

			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/company";
			String user = "root";
			String pass = "";
			con = DriverManager.getConnection(url, user, pass);

			String query = "Select Name, B_Name, Image, Price, Description from product where type=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,"Men");
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				String Name = result.getString("Name");
				String B_Name = result.getString("B_Name");
				String Image = result.getString("Image");
				String Price = result.getString("Price");
				String Description = result.getString("Description");

				%>
				<div class="container">
		<div class="product-container">

			<div class="product-item">
				<div class="product-image">
					<img src="../IMAGES/Product/1.jpg" alt="Product pic">
				</div>
				<div class="product-info">
					<h3 class="product-title">Name: <%=Name %></h3>
					<p class="product-price">Brand: <%=B_Name %></p>

					<p class="product-price">Price: <%=Price %></p>
					<p class="product-price">Description: <%=Description%></p>

					<button class="btn-add-to-cart">Add to Cart</button>
				</div>
			</div>
			</div>
			</div>
		
				<%
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		%>
	
	

</body>
</html>