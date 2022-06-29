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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDetails user = new UserDetails();
		user.setName(username);
		user.setPassword(password);
		DatabaseService dbs = new DatabaseService();
		try {
			 user = dbs.loginAuthenticate(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user!= null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.setAttribute("user", user);
			System.out.println(user.getUserId());
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
			String error = "Wrong Username or Password";
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("log.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDetails u = (UserDetails)request.getSession().getAttribute("user");	    
		if(u!= null)
			response.sendRedirect("profile.jsp");
		else
			response.sendRedirect("log.jsp");
	}

}
