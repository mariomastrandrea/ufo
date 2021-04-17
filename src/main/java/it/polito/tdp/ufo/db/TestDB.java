package it.polito.tdp.ufo.db;

import java.util.List;

public class TestDB
{
	public static void main(String[] args)
	{
		SightingDAO dao = new SightingDAO();
		
		List<String> shapes = dao.readShapes();
		
		for(String shape : shapes)
		{
			int numOfShapes = dao.countByShape(shape);
			System.out.println("Numero di avvistamenti di forma \"" + shape + "\": " + numOfShapes);
		}
				
	}

}
