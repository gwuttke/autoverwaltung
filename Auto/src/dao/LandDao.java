package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlServer;
import domain.Land;


public class LandDao {

	private List<Land> L�nder = new ArrayList<Land>();
	
	private SqlServer sqlS = new SqlServer();
	
	public List<Land> getL�nder() {
		return L�nder;
	}	
}
