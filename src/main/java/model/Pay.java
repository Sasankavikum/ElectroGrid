package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Pay {
	private Connection connect()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//provide user-name and password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/test","root","");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("can not connect");
		}
		return con;	}
	
	

}
