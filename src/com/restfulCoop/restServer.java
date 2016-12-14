package com.restfulCoop;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

@Path("/coopInfo")
public class restServer {

	@GET
	@Produces("application/json")
	public Response getCoopInfo() throws JSONException, IOException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try { 
			Object obj = parser.parse(new FileReader(
                    "coop.txt"));
			jsonObject = (JSONObject) obj;
            String name = (String) jsonObject.get("coopName");
            int coopTemp = (int) jsonObject.get("temp");
            boolean heat = (boolean) jsonObject.get("heat");
 
            System.out.println("Name: " + name);
            System.out.println("Temp: " + coopTemp);
            System.out.println("Heat On: " + heat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(200).entity(jsonObject).build();

	}
	
	@SuppressWarnings("unchecked")
	@Path("{temp}")
	@GET
	@Produces("application/json")
	public Response setCoopTemp(@PathParam("temp") int temp) throws JSONException, IOException {
		
		Coop cknCoop = new Coop("CoopInterior", temp, false);
		JSONObject json = new JSONObject();
		
		json.put("coopName", cknCoop.getName());
		json.put("temp", cknCoop.getTemp());
		json.put("heat", cknCoop.isHeatOn());
		
		String message;
		try (FileWriter file = new FileWriter("coop.txt")) {
			file.write(json.toString());
			message = "Successfully Copied JSON Object to File...";
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + json);
		}
		message += "\n\n " + json;
		return Response.status(200).entity(message).build();
	}
}
