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

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDetails u=(UserDetails)request.getSession().getAttribute("user");
		int userId=u.getUserId();
		String name=request.getParameter("name");
		String type=request.getParameter("type");
		String design=request.getParameter("design");
		String job=request.getParameter("org");
		String about=request.getParameter("about");
		System.out.println("IN DBS UPDATE SERVLET");
		ProfileDetails pd=new ProfileDetails();
		pd.setAbout(about);
		pd.setDesign(design);
		pd.setJob(job);
		pd.setName(name);
		pd.setType(type);
		pd.setUserId(userId);
		request.getSession().setAttribute("pd",pd);
		DatabaseService db=new DatabaseService();
		try {
			db.InsertProfileFields(pd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
		dispatcher.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("profile.jsp");
	}


}
