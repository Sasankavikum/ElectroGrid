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
		return con;	
	}
	public String insertPayment(String fullName, String NIC, String amount, String date, String bankName, String debitCard, String otpNumber)
	{
		String output ="";
		try {
			Connection con = connect();
			if(con == null) {
				return "can not connect to the database";}
				
				//create a prepared statement
				String query = "insert into payment(`payID`,`fullName`,`NIC`,`amount`,`date`,`bankName`,`debitCard`,`otpNumber`)"
				+" values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(query);
				
				//binding values
				ps.setInt(1, 0);
				ps.setString(2, fullName);
				ps.setString(3, NIC);
				ps.setString(4, amount);
				ps.setString(5, date);
				ps.setString(6, bankName);
				ps.setString(7, debitCard);
				ps.setString(8, otpNumber);
				
				//execute the statement
				ps.execute();
				con.close();
				
				output="Data inserted successfully";
			
		}catch(Exception e) {
			e.printStackTrace();
			//output ="Insert operation failed. check again.";
		}
		return output;
	}	
	
}
