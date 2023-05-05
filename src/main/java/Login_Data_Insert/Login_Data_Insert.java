package Login_Data_Insert;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Login_Data_Insert
 */

@MultipartConfig
@WebServlet("/Login_Data_Insert")
public class Login_Data_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login_Data_Insert() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Part file = request.getPart("image");
		String imageFileName = file.getSubmittedFileName(); // get submitted image
		String destination = "C:/Users/sagar/eclipse-workspace/COURSE/src/main/webapp/IMAGES/" +imageFileName; //path to dave image

		try
		{
			FileOutputStream fos = new FileOutputStream(destination);
			InputStream in = file.getInputStream();

			byte[] data = new byte[in.available()];
			in.read(data);
			fos.write(data);
			fos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


		RequestDispatcher rd = null;

		try {
			String Email = request.getParameter("Email");
			String UserName = request.getParameter("UserName");
			String Password = request.getParameter("Password");
			String Mobile = request.getParameter("Mobile");

			//String Password = encryption.encrypt(request.getParameter("Password"),"my-secret-key-hi");
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/company";
			String user = "root";
			String pass = "";
			Connection con = DriverManager.getConnection(url, user, pass);

			String query = "Insert into register_info(Email, User_Name, Password, Mobile, Image)" + "values(?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, Email);
			ps.setString(2, UserName);
			ps.setString(3, Password);
			ps.setString(4, Mobile);
			ps.setString(5, imageFileName);

			int result = ps.executeUpdate();

			if (result > 0) 
			{
				rd = request.getRequestDispatcher("JSP/index.jsp");
			} 

			else 
			{
				rd = request.getRequestDispatcher("HTML/Register.html");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} 
		rd.forward(request, response);

	}

}
