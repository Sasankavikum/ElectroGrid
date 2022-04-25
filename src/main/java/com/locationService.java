package com;
import model.Item;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Items")
public class ItemService
{
 Item itemObj = new Item();
 
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readItems()
 {
	return itemObj.readItems();
 }

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertItem(@FormParam("locationCode") String locationCode,
		 @FormParam("locationName") String locationName,
		 @FormParam("date") String date,
		 @FormParam("time") String time)
		{
	String output = itemObj.insertItem(locationCode, locationName, date, time);
	return output;
		}




}
