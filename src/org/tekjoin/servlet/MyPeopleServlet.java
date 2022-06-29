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
 * Servlet implementation class MyPeopleServlet
 */
@WebServlet("/mypeople")
public class MyPeopleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result = request.getParameter("b2");
		String result2=request.getParameter("button");
		System.out.println("The result stores "+result);
		System.out.println("The result 2 stores "+result2);
		int count = 0;
		UserDetails u=(UserDetails)request.getSession().getAttribute("user");
		int uid1=u.getUserId();
		int uid2 =Integer.valueOf(result);
		System.out.println("The result 2 stores "+result2);
		System.out.println("The entry in mypeople from front is "+result);
		DatabaseService db = new DatabaseService();
		if(result2.equals("Accept Request"))
		{
			count = 1;
			}
		else
		{
			count = 2;
			
		}
		try {
			db.AcceptRequest(count, uid1, uid2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
		dispatcher.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("mypeople.jsp");
	}


}
