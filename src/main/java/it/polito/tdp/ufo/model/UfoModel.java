package it.polito.tdp.ufo.model;

import java.util.Collection;
import it.polito.tdp.ufo.db.SightingDAO;

public class UfoModel
{
	private SightingDAO dao;
	
	public UfoModel()
	{
		this.dao = new SightingDAO();
	}
	
	public Collection<String> getAllShapes()
	{
		return this.dao.readShapes();
	}
	
	public int getNumSightingsOf(String shape)
	{
		return this.dao.countByShape(shape);
	}
}
