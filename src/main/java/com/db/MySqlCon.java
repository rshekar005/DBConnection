package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.pojo.AtlasLoginModel;

public class MySqlCon {

	
	public static AtlasLoginModel getData() throws ClassNotFoundException, SQLException 
	{
		Connection con = null;
		AtlasLoginModel atl = null;
		try
		{
			// connecting to mysql server
			Class.forName("com.mysql.jdbc.Driver");
			
			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium","root","root");
			
			Statement stmt= con.createStatement();
			
			ResultSet rs= stmt.executeQuery("select * from credentials");
			
			// ArrayList<String> array = new ArrayList<>();
			while(rs.next())
			{
				atl= new AtlasLoginModel();
				atl.setUsername(rs.getString(1));
				atl.setPassword(rs.getString(2));
				//System.out.println("username is " +username+ " password is "+pass) ;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			con.close();
			System.out.println("Connection closed");
		}
		return atl;
		
        
	   
	
	}

}
