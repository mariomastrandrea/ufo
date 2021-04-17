package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect
{
	public static Connection getConnection() throws SQLException
	{ 
		String jdbcURL = "jdbc:mysql://127.0.0.1/ufo_sightings?";
		String user = "root";
		String password = "root";
		
		return DriverManager.getConnection(jdbcURL, user, password);
	}
}
