package filters.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthenticationFilter implements Filter{
	private ServletContext context;
	
	@Override
	public void destroy() {}
	public void init (FilterConfig filterConfig) throws ServletException{}


	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
			
		//Check if the request is a login or logout request
		String uri = req.getRequestURI();
		boolean isLoginJsp = uri.endsWith("login.jsp");
		boolean isLoginServlet = uri.endsWith("LoginServlet");
		boolean isLogoutServlet = uri.endsWith("LogoutServlet");
		this.context.log("Requested Resource::" + uri);
			
		HttpSession session = req.getSession(false);
		boolean loggedIn = session != null && session.getAttribute("user")!= null;
		
		if(!loggedIn && !(isLoginJsp || isLoginServlet)) 
		{
			res.sendRedirect(req.getContextPath()+"/JSP/Login.jsp");
		}
		else if(loggedIn && !(!isLoginJsp || isLogoutServlet))
		{
			res.sendRedirect(req.getContextPath()+"/JSP/home.jsp");
		}
		else
		{
			//pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	
}
	

	


