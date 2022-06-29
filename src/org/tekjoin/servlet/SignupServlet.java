package org.tekjoin.servlet;
import org.tekjoin.bean.*;
import org.tekjoin.dao.*;
import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDetails user = new UserDetails();
		String username = request.getParameter("username");
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		String email = request.getParameter("email");
		if(pass1.equals(pass2))
		{
			user.setName(username);
			user.setPassword(pass1);
			user.setEmail(email);
			DatabaseService dbs = new DatabaseService();
			try {
				dbs.insertSignup(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.setAttribute("user", user);
			DatabaseService db = new DatabaseService();
			TechDetails td = new TechDetails();
			try {
				td = db.getTechDetails(user.getUserId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ProfileDetails pd = new ProfileDetails();
			try {
				pd = db.getProfileDetails(user.getUserId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("td", td);
			session.setAttribute("pd", pd);
			request.setAttribute("val",0);
			RequestDispatcher dispatcher = request.getRequestDispatcher("MatchesServlet");
			dispatcher.forward(request, response);
		}
		else
		{
			response.sendRedirect("signup.jsp");
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDetails u = (UserDetails)request.getSession().getAttribute("user");	    
		if(u!= null)
			response.sendRedirect("profile.jsp");
		else
			response.sendRedirect("signup.jsp");
}


}
