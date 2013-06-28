package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlServer;
import domain.Land;


public class LandDao {

	private List<Land> Länder = new ArrayList<Land>();
	
	private SqlServer sqlS = new SqlServer();
	
	public List<Land> getLänder() {
		return Länder;
	}

	public void setLänder() {
		ResultSet rs = getLand();
		
		try {
			while (rs.next()){
				final Land land = new Land();
				
				land.setId(rs.getInt("ID"));
				land.setName(rs.getString("Name"));
				Länder.add(land);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private ResultSet getLand(){
		ResultSet landRS = null;
		
		String sql = "SELECT ID, Name FROM T_Land";

		try {
			landRS = sqlS.retrieveRS(sql);
			return landRS;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return landRS;
		
	}
	
}
