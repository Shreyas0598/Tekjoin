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
 * Servlet implementation class TechServlet
 */
@WebServlet("/techservlet")
public class TechServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ar[]=request.getParameterValues("card1");
		for(String e:ar)
		{
			System.out.println(e);
		}
		int length=ar.length;
		StringBuilder fields=new StringBuilder("000000000000000000");
		System.out.println(fields);
		for(int i=0;i<ar.length;i++)
		{
			fields.setCharAt(Integer.valueOf(ar[i])-1,'1');
		}
		UserDetails u=(UserDetails)request.getSession().getAttribute("user");
		int id=u.getUserId();
		TechDetails td = new TechDetails();
		td.setUserId(id);
		td.setFields(fields.toString());
		td.setLength(length);
		DatabaseService db = new DatabaseService();
		try {
			db.InsertTechFields(td);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("val",1);
		request.getSession().setAttribute("td",td);
		RequestDispatcher dispatcher = request.getRequestDispatcher("MatchesServlet");
		dispatcher.forward(request, response);
		
		System.out.println(fields);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("profile.jsp");
	}

	

}
