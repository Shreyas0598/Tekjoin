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
 * Servlet implementation class MatchesServlet
 */
@WebServlet("/MatchesServlet")
public class MatchesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TechDetails td=(TechDetails)request.getSession().getAttribute("td");
		if(td==null)
			System.out.println("TD IS NULL");
		else
			System.out.println("TD RETURN VALUE");
		DatabaseService db = new DatabaseService();
		int val=(int) request.getAttribute("val");
		try {
			db.MakeMatch(td,val);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
		dispatcher.forward(request, response);
		
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("profile.jsp");
	}


}
