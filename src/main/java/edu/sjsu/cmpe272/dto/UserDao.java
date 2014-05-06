package edu.sjsu.cmpe272.dto;

import java.sql.*;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class UserDao {
	Connection conn = null;
	Statement stmt = null;

	// Constructure with JDBC connection
	public UserDao()
	{
		/* try{
		      try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      conn = DriverManager.getConnection("jdbc:mysql://localhost/CMPE272","root","root");
		   }
		   catch (SQLException e) {
					e.printStackTrace();

			}*/
	}


	public JSONArray getscripting(String str) throws Exception{

		JSONArray obj = new JSONArray();

		ResultSet rs;
	
			try{
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cmpe272"); // "jdbc/mydb" is also a valid JNDI name
				Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement();
				System.out.println("Connection done");
				String query = "Select * from processeddata where CategoryName = '"+str+"'";
				rs = stmt.executeQuery(query);
				System.out.println("selection done");
				while(rs.next()){
					System.out.println("In while loop");
					String name = rs.getString("ItemName");
					System.out.println(name);
					int count = rs.getInt("Count");
					obj.put(jsonobj(name,count));
					
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			  return obj;
		  }
		  
	  public JSONArray getinterview(String str) throws Exception{

		JSONArray obj = new JSONArray();

		ResultSet rs;
	
			try{
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cmpe272"); // "jdbc/mydb" is also a valid JNDI name
				Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement();
				System.out.println("Connection done");
				String query = "Select * from interview_table where tags = '"+str+"'";
				rs = stmt.executeQuery(query);
				System.out.println("selection done");
				while(rs.next()){
					System.out.println("In while loop");
					String name = rs.getString("ItemName");
					int count = rs.getInt("Count");
					obj.put(jsonobj(name,count));
					
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			  return obj;
		  }
		  
	
	  public JSONObject jsonobj(String name, int count) {
			System.out.println("In json obj function");
			JSONObject json = new JSONObject();
			
			try {
				json.put("Name", name);
				json.put("Count", count);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return json;
			
	}
		
		public JSONObject jsonobjinterview(String tags, String body) {
			System.out.println("In json obj interview function");
			JSONObject json = new JSONObject();
			
			try {
				json.put("Tags", tags);
				json.put("Body", body);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return json;
			
	}

	}

