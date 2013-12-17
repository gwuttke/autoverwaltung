package dao;

import java.util.ArrayList;
import java.util.List;

import database.SqlServer;
import domain.Land;


public class LandDao {

	private List<Land> Länder = new ArrayList<Land>();
	
	
	public List<Land> getLänder() {
		return Länder;
	}	
}
