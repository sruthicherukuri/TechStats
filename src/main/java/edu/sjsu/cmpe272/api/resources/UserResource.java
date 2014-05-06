package edu.sjsu.cmpe272.api.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


import edu.sjsu.cmpe272.dto.UserDao;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	
	private UserDao userdao = new UserDao();
	
	
	
	@POST
	@Path("/getscripting")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public JSONArray getscripting(String str) throws Exception
	{
		System.out.println("String is"+str);
		System.out.println("In  getscripting");
		return userdao.getscripting(str);
	}
	
	@POST
	@Path("/getinterview")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public JSONArray getinterview(String str) throws Exception
	{
		return userdao.getinterview(str);
	}
	
}
