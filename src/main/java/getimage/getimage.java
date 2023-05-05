package getimage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getimage
 */
@WebServlet("/getimage")
public class getimage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getimage() {
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
		String imageId = request.getParameter("imageId");
		int id = Integer.parseInt(imageId);

		Connection con = null;
		int imgId = 0;
		String imgFileName = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","");

			String query = "Select Image from register_info";
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query);
			
			while(rs.next())
			{
				if(rs.getInt("imageId")==id)
				{
					imgId = rs.getInt("imageId");
					imgFileName = rs.getString("imageFileName");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		
		RequestDispatcher rd;
		request.setAttribute("id", String.valueOf(imgId));
		request.setAttribute("img", imgFileName);
		
		rd = request.getRequestDispatcher("JSP/getimage.jsp");
		rd.forward(request, response);
	}

}
