package com;

import model.bill;
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

public class billService {
	
	bill itemObj = new bill();
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
	public String insertItem(@FormParam("bname") String bname,
	 @FormParam("bdate") String bdate,
	 @FormParam("accno") String accno,
	 @FormParam("prereading") double prereading,
	 @FormParam("curreading") double curreading)
	{
	 String output = itemObj.insertBill(bname, bdate, accno, prereading,curreading);
	return output;
	}

	//update part
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String billID = itemObject.get("billID").getAsString();
	 String bname = itemObject.get("bname").getAsString();
	 String bdate = itemObject.get("bdate").getAsString();
	 String accno = itemObject.get("accno").getAsString();
	 String prereading = itemObject.get("prereading").getAsString();
	 String curreading = itemObject.get("curreading").getAsString();
	 String output = itemObj.updateBill(billID, bname, bdate, accno, prereading,curreading);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String billid = doc.select("billid").text();
	 String output = itemObj.deleteBill(billid);
	return output;
	}

}
