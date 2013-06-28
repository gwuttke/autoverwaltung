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

	public void setL�nder() {
		ResultSet rs = getLand();
		
		try {
			while (rs.next()){
				final Land land = new Land();
				
				land.setId(rs.getInt("ID"));
				land.setName(rs.getString("Name"));
				L�nder.add(land);
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
