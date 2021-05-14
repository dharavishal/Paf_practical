package com;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {
	
	private static String url = "jdbc:mysql://localhost:3306/item";
	private static String userName = "root";
	private static String password = "12345";
	private static Connection con;

	public static Connection getConnection() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(url, userName, password);
		}	
		
		
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database connection is not success!!!");
		}
		
		return con;


	}
	
	
	public String insertItem(String code, String name, String price, String des)  {
		
		
		String output = "";
		
		try 
		{
			Connection con = getConnection();
		
			if(con == null) {
			
				return "Error while connecting to the database";
			}
		
		
			String query = "insert into item values(?,?,?,?,?)";
		
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2,code);
			preparedStmt.setString(3,name);
			preparedStmt.setDouble(4,Double.parseDouble(price));
			preparedStmt.setString(5,des);
		
			preparedStmt.execute();
			
			output = "inserted successfully";
			
			con.close();
			
			
		
		}
		catch(Exception e){
			
			System.err.println(e.getMessage());
		}
		
		
		return output;
		
		
	}
	
	public String readItems() {
		
		
		String output = "";
		
		
		try 
		{
			Connection con = getConnection();
			if(con == null) {
			 return "Error while connecting to the database for reading";
			}
			
			//prepare the  table to be displayed
			 output = "<table class=\"table table-bordered\">\r\n"
				 		+ "  <thead>\r\n"
				 		+ "    <tr>\r\n"
				 		+ "      <th scope=\"col\">Code</th>\r\n"
				 		+ "		<th scope=\"col\">Name</th>\r\n"
				 		+ "		<th scope=\"col\">Price</th>\r\n"
				 		+ "      <th scope=\"col\">Description</th>\r\n"
				 		+ "      <th scope=\"col\" colspan=\"2\">Upadate/Delete</th>\r\n"
				 		+ "    </tr>\r\n"
				 		+ "  </thead>\r\n"
				 		+ "</table";
			
			String query = "select * from item";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//iterate through the row in the result set
			while(rs.next())
			{
				String ID = Integer.toString(rs.getInt("id"));
				String code = rs.getString("icode");
				String name = rs.getString("iname");
				String price = Double.toString(rs.getDouble("iprice"));
				String des = rs.getString("idesc");
				
				//add into the html table
				output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + ID + "'>"
				 		  + code + "</td>";
		 output += "<td>" + name + "</td>";
		 output += "<td>" + price + "</td>"; 
		 output += "<td>" + des + "</td>";		
				
				//buttons
		 output += "<td>"
				 + "<input name='btnUpdate' "
			     + " type='button' class='btnUpdate btn btn-outline-dark' value='Update'>"
				 + "<input name='itemID' type='hidden' "
				 + " value='" + ID + "'>" + "</td>"
				 + "<td><form method='post' action='Insert.jsp'>"
				 + "<input name='btnRemove' "
				 + " type='submit' class='btn btn-outline-danger' value='Remove'>"
				 + "<input name='hidItemIDDelete' type='hidden' "
				 + " value='" + ID + "'>" + "</form></td></tr>";
				}
			
			con.close();
			
			//complete the html table
			output += "</table>";
			}
			catch(Exception e){
				output = "Error while reading the Items.";
				System.err.println(e.getMessage());
			}
			
		return output;
		
		}
	
	public String updateItem(String id,String code, String name, String price, String des) 
	{
		
		String output = "";
		try {
			Connection con = getConnection();
			if(con == null) {
				return "Error while connecting to the database for the Update";
			}
			
			String query ="Update item set icode=?, iname=?, iprice=?, idesc=? where id='"+id+"'";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
		//	preparedStmt.setInt(1, 0);
			preparedStmt.setString(1,code);
			preparedStmt.setString(2,name);
			preparedStmt.setDouble(3,Double.parseDouble(price));
			preparedStmt.setString(5,des);
			
			preparedStmt.execute();
			con.close();
			
			String newi = readItems();
			output = "{\"status\":\"success\", \"data\": \"" +
			newi + "\"}";
			
			
			output = "Updated Successfully";
			
		}catch(Exception e) {
			
			output = "{\"status\":\"error\", \"data\":"
					+"\"Error while updating the data.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	public String deleteItem(String id) {
	
	String output = "";
	
	try 
	{
		Connection con = getConnection();
		if (con == null) {
			return "Error while connecting to the database for deleting";
		}
		
		String query = "delete from item where id=?";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		preparedStmt.setInt(1,Integer.parseInt(id));
		
		preparedStmt.execute();
		con.close();
		output = "Deleted Successfully";
		
	}catch (Exception e) {
		output = "{\"status\":\"error\", \"data\":"
				+"\"Error while deleting the item.\"}";
		System.err.println(e.getMessage());
	}
	
	return output;
}
	
	

}
