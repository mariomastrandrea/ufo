package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDB
{
	public static void main(String[] args)
	{
		SightingDAO dao = new SightingDAO();
		List<String> shapes = dao.readShapes();
		
		for(String shape : shapes)
		{
			int numberOfShapes = dao.countByShape(shape);
			System.out.println("Ufo di forma \""+shape+"\" sono: "+numberOfShapes+" elementi");
		}
	}
	
	/////////////////////////////////////////////
	public static void main2(String[] args)
	{
		String jdbcURL = "jdbc:mysql://127.0.0.1/ufo_sightings?user=root&password=matematica";
		
		try( Connection conn = DriverManager.getConnection(jdbcURL))			 
		{
			System.out.println("Connessione ok");
			String sql = "SELECT DISTINCT shape " +
						 "FROM sighting " +
						 "WHERE shape<>'' " +
						 "ORDER BY shape ASC";
			
			//ResultSet result = statement.executeQuery(sql); //utile solo per query di tipo SELECT
															//per INSERT e UPDATE utilizzare .executeUpdate()
			
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			ResultSet result = prepStatement.executeQuery();
			
			List<String> formeUfo = new ArrayList<>();
			
			while(result.next())
			{
				String formaTemp = result.getString("shape");
				formeUfo.add(formaTemp);
			}
			System.out.println(formeUfo);
			prepStatement.close();
			result.close();
			////
			
			System.out.println("\nNuova query");
			String sql2 = "SELECT COUNT(*) FROM sighting WHERE shape= ? ";
			String shapeScelta = "circle";
			
			PreparedStatement st2 = conn.prepareStatement(sql2);
			st2.setString(1, shapeScelta);
			
			ResultSet result2 = st2.executeQuery();
			result2.first();
			int count = result2.getInt("COUNT(*)");
			st2.close();
			result2.close();
			
			System.out.println("UFO di forma \""+shapeScelta+"\" sono: "+count);
			
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
				
				
				
	}

}
