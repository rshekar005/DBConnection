package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pojo.AtlasLoginModel;

public class GMDB {

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		try
		{
			// connecting to mysql server
			Class.forName("com.mysql.jdbc.Driver");
			
			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium","root","root");
			
			Statement stmt= con.createStatement();
			
			ResultSet rs= stmt.executeQuery("select * from credentials");
			
			while(rs.next())
			{
				String message = rs.getString("CODE");
				System.out.println(message);
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
		
	}

}
