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
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/notice")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String notice = request.getParameter("notice");
		Notice n = new Notice();
		UserDetails u=(UserDetails)request.getSession().getAttribute("user");
		n.setNotice(notice);
		n.setUserId(u.getUserId());
		DatabaseService db=new DatabaseService();
		try {
			db.InsertNotice(n);
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
