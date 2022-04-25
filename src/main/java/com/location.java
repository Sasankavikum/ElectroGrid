package model;
import java.sql.*;
public class Item
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/list", "root", "12345");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertItem(String Code, String Name, String Date, String Time)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into items(`locationID`,`locationCode`,`locationName`,`date`,`time`)"
 + " values (?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, Code);
 preparedStmt.setString(3, Name);
 preparedStmt.setString(4, Date);
 preparedStmt.setString(5, Time);
 // execute the statement
 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
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
 output = "<table border='1'><tr><th>Location Code</th><th>Location Name</th>" +
 "<th>Date</th>" +
 "<th>Time</th>" +
 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from items";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String locationID = Integer.toString(rs.getInt("locationID"));
 String locationCode = rs.getString("locationCode");
 String locationName = rs.getString("locationName");
 String date = rs.getString("date");
 String time = rs.getString("time");
 // Add into the html table
 output += "<tr><td>" + locationCode + "</td>";
 output += "<td>" + locationName + "</td>";
 output += "<td>" + date + "</td>";
 output += "<td>" + time + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
 + "<input name='itemID' type='hidden' value='" + locationID
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
public String updateItem(String ID, String Code, String Name, String Date, String Time)
{
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE items SET locationCode=?,locationName=?,date=?,time=?WHERE locationID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, Code);
	 preparedStmt.setString(2, Name);
	 preparedStmt.setString(3, Date);
	 preparedStmt.setString(4, Time);
	 preparedStmt.setInt(5, Integer.parseInt(ID));
	 
	 