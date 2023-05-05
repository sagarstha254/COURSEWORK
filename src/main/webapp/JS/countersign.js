function counter() {
               var x = document.getElementById("showPass");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
/*function changePassword() {
	string Email = request.getParameter("Email");


			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/company";
			String user = "root";
			String pass = "";
			Connection con = DriverManager.getConnection(url, user, pass);



			String query = "Update register_info SET Email = WHERE Email = ";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
}
     			i*/