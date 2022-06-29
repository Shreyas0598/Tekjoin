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
 * Servlet implementation class ChatServlet
 */
@WebServlet("/chatservlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String msg=request.getParameter("msg");
		int uid1=((UserDetails)request.getSession().getAttribute("user")).getUserId();
		String uname=((UserDetails)request.getSession().getAttribute("user")).getName();
		int uid2=Integer.valueOf(request.getParameter("receiver"));
		DatabaseService db=new DatabaseService();
		try {
				db.chat(uid1,uid2,msg,uname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("mypeople.jsp");
		rd.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("mypeople.jsp");
	}


}
