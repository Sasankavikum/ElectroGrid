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

@Path("/Item")
public class ItemService
{
	Item itemObj = new Item();
	@GET
	@Path("/")
	//@Produces(MediaType.TEXT_HTML)

	public String readItems()
	{
		return itemObj.readItems();
		
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("userNumber") String userNumber,
	 @FormParam("name") String name,
	 @FormParam("address") String address,
	 @FormParam("phoneNumber") String phoneNumber,
	 @FormParam("email") String email)
	{
	 String output = itemObj.insertItem(userNumber, name, address, phoneNumber, email);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String userID = itemObject.get("userID").getAsString();
	 String userNumber = itemObject.get("userNumber").getAsString();
	 String name = itemObject.get("name").getAsString();
	 String address = itemObject.get("address").getAsString();
	 String phoneNumber = itemObject.get("phoneNumber").getAsString();
	 String email = itemObject.get("email").getAsString();
	 String output = itemObj.updateItem(userID, userNumber, name, address, phoneNumber, email);
	return output;
	}
	
	
	
	
}