package servlets.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.Database;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginServlet" })

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter print = response.getWriter();

		String Email_ID = request.getParameter("email");
		String Pass = request.getParameter("Pass");
		if (Email_ID.equals("admin") && Pass.equals("admin"))
		{
			response.sendRedirect(request.getContextPath()+ "/JSP/admin.jsp");

		}
		else 
		{
			try 
			{
				
				String bool = "False";
				Connection con = Database.getConn();
				String query = "Select Email, Password ,User_Name from register_info";
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet result = ps.executeQuery();

				while (result.next()) {

					String Email = result.getString("Email");
					String Password = result.getString("Password");
					String UserName = result.getString("User_Name");


					if (Email.equals(Email_ID) && Password.equals(Pass))
					{

						HttpSession session =request.getSession();
						session.setAttribute("Email", Email);

						//setting session to expiry in 10 mins
						session.setMaxInactiveInterval(10 * 60);

						Cookie Emailo = new Cookie("Email", Email);
						Cookie Passwordo = new Cookie("Password", Password);
						Cookie UserNameo = new Cookie("UserName", UserName);

						Emailo.setMaxAge(30 * 60);
						response.addCookie(Emailo);
						response.addCookie(Passwordo);
						response.addCookie(UserNameo);
						bool = "True";
						response.sendRedirect(request.getContextPath()+ "/JSP/profile.jsp");

					}
				}
				/*  					<%eout.println(encryption.decrypt(result.getString("password"),"my-secret-key-hi")); 
				 */
				if(bool == "False")
				{
					//forward request to login page
					RequestDispatcher dispatcher = request.getRequestDispatcher("/HTML/Login.html");
					dispatcher.include(request, response);


				}

			}

			catch (Exception ex) {
				print.println(ex.getMessage());
				ex.printStackTrace();


			}

		}
	}
}