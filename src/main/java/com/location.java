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
