package model;

import java.sql.*;

public class bill {
	
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.cj.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		public String insertBill(String bname, String bdate, String accno,double prereading,double curreading)
		 {
		 String output = "";
		 double preReading;
		 double currentReading;
		 double units;
		 double total;
		 try
		 {
		 Connection con = connect();
		 
		 preReading = prereading;
		 currentReading = curreading;
		 units = (currentReading-preReading);
		 
		 if(units<=80) {
			 total = (units*5)+300;
		 }
		 else {
			 total = (units*12)+300;
		 } 
		 
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into bill (`billID`,`bname`,`bdate`,`accno`,`prereading`,`curreading`,`units`,`total`)"
		 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, bname);
		 preparedStmt.setString(3, bdate);
		 preparedStmt.setString(4, accno);
		 preparedStmt.setDouble(5, prereading);
		 preparedStmt.setDouble(6, curreading);
		 preparedStmt.setDouble(7, units);
		 preparedStmt.setDouble(8, total);

		 

		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 
			 e.printStackTrace();
			 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String readItems()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Bill ID</th>"
					+"<th>Name</th>"
					+ "<th>Date</th>"
					+"<th>Account Number</th>"
					+"<th>Pre Reading</th>"
					+"<th>Currant Reading</th>"
					+"<th>Units</th>"
					+"<th>Total</th>"
					+"<th>Update</th><th>Remove</th></tr>";


		 String query = "select * from bill";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String billID = Integer.toString(rs.getInt("billID"));
			 String bname = rs.getString("bname");
			 String bdate = rs.getString("bdate");
			 String accno = rs.getString("accno");
			 String prereading = Double.toString(rs.getDouble("prereading"));
			 String curreading = Double.toString(rs.getDouble("curreading"));
			 String units = Double.toString(rs.getDouble("units"));
			 String total = Double.toString(rs.getDouble("total"));
		 // Add into the html table
			 output += "<tr><td>" + billID + "</td>";
			 output += "<td>" + bname + "</td>";
			 output += "<td>" + bdate + "</td>";
			 output += "<td>" + accno + "</td>";
			 output += "<td>" + prereading + "</td>";
			 output += "<td>" + curreading + "</td>";
			 output += "<td>" + units + "</td>";
			 output += "<td>" + total + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>"+"<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + billID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }

		//Update
		public String updateBill(String ID, String bname, String bdate, String accno, String prereading, String curreading)
		{
			 String output = "";
			 try
			 {
				  
				 Double preReading = Double.parseDouble(prereading);
				 Double currentReading = Double.parseDouble(curreading);
				 Double units = (currentReading-preReading);
				 Double total = null;
				 
				 if(units<=80) {
					 total = (units*5)+300;
				 }
				 else {
					 total = (units*12)+300;
				 }
				 
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE bill SET bname=?,bdate=?,accno=?,prereading=?,curreading=?,units=?,total=?  WHERE billID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, bname);
			 preparedStmt.setString(2, bdate);
			 preparedStmt.setString(3, accno);
			 preparedStmt.setDouble(4, Double.parseDouble(prereading));
			 preparedStmt.setDouble(5, Double.parseDouble(curreading));
			 preparedStmt.setDouble(6, units);
			 preparedStmt.setDouble(7, total);
			  preparedStmt.setInt(8, Integer.parseInt(ID));
			 
			
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			
		//Delete
		public String deleteBill(String billID)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 // create a prepared statement
			 String query = "delete from bill where billID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(billID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while deleting the item.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
}
