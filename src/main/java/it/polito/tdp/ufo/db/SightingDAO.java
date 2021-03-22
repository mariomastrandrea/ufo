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
		List<String> formeUfo;
		try
		{
			Connection conn = DBConnect.getConnection();	 
			
			String sql = "SELECT DISTINCT shape " +
						 "FROM sighting " +
						 "WHERE shape<>'' " +
						 "ORDER BY shape ASC";
				
			//ResultSet result = statement.executeQuery(sql); //utile solo per query di tipo SELECT
															 //per INSERT e UPDATE utilizzare .executeUpdate()
				
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			ResultSet result = prepStatement.executeQuery();
					
			formeUfo = new ArrayList<>();
				
			while(result.next())
			{
				String formaTemp = result.getString("shape");
				formeUfo.add(formaTemp);
			}
			System.out.println(formeUfo);
			prepStatement.close();
			result.close();
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("Database error in readShape", sqle);
		}
		return formeUfo;
		
	}
	
	public int countByShape(String shape)
	{
		int count;
		try
		{
			Connection conn = DBConnect.getConnection();	 
			
			String sql2 = "SELECT COUNT(*) FROM sighting WHERE shape= ? ";
			String shapeScelta = shape;
			
			PreparedStatement st2 = conn.prepareStatement(sql2);
			st2.setString(1, shapeScelta);
			
			ResultSet result2 = st2.executeQuery();
			result2.first();
			count = result2.getInt("COUNT(*)");
			st2.close();
			result2.close();
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("Database error in countByShape", sqle);
		}
		return count;
	}

}
