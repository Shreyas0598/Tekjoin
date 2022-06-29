package org.tekjoin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	public static Connection getCon() throws SQLException
	{
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/try";
		String username = "root";
		String password = "7774008206";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
			System.out.println("Printing connection object "+con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(con != null)
			 System.out.println("Connection Successful");
		return con;
		
	}

}
