package Database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class changeEmail extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String oldEmail = null;



			Cookie[] cookies = request.getCookies();
			if(cookies != null)
			{
				for(Cookie cookie: cookies)
				{
					if(cookie.getName().equals("Email")) oldEmail = cookie.getValue();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("hello");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();


			String newUser = request.getParameter("newUser");
			// Establish a connection to the database
			Connection conn = Database.getConn();

			// Create a PreparedStatement object
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE mytable SET Email=? WHERE Email=?");

			// Set the parameters of the prepared statement
			ps.setString(1, "newUser");
			ps.setString(2, "oldEmail");

			int result = ps.executeUpdate();
			if(result >0) {
				out.println("<script>alert('Data Updated');</script>");
			}
			// Close the statement and connection
			ps.close();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
