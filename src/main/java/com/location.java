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
