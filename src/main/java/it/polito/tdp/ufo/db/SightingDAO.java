package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SightingDAO
{
	public List<String> readShapes()  
	{
		List<String> formeUfo = new ArrayList<>();
		
		try
		{
			Connection connection = DBConnect.getConnection(); 
			
			String sqlQuery = "SELECT DISTINCT shape " +
						 "FROM sighting " +
						 "WHERE shape<>'' AND shape IS NOT NULL " +
						 "ORDER BY shape ASC";
							
			//ResultSet result = statement.executeQuery(sql); //utile solo per query di tipo SELECT
															 //per INSERT e UPDATE utilizzare .executeUpdate()
				
			PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);
			ResultSet result = prepStatement.executeQuery();
									
			while(result.next())
			{
				String shape = result.getString("shape");
				formeUfo.add(shape);
			}
			
			result.close();
			prepStatement.close();
			connection.close();
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("Database error in readShapes()", sqle);
		}
		
		return formeUfo;
	}
	
	public int countByShape(String shape)
	{
		int count = 0;
		
		try
		{
			Connection connection = DBConnect.getConnection();	 
			
			String sqlQueryTemplate = "SELECT COUNT(*) AS numShapes FROM sighting WHERE shape=? ";
			
			PreparedStatement statement = connection.prepareStatement(sqlQueryTemplate);
			statement.setString(1, shape);
			ResultSet result = statement.executeQuery();
			
			result.first();
			count = result.getInt("numShapes");
			
			result.close();
			statement.close();
			connection.close();
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("Database error in countByShape()", sqle);
		}
		
		return count;
	}

}
