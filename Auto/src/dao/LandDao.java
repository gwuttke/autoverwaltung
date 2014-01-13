package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlAbfrage;
import database.SqlServer;
import domain.Land;

public class LandDao extends SqlServer {

	private static List<Land> laender = new ArrayList<Land>();

	public List<Land> getLaender() {
		return laender;
	}

	public static void setLaender() throws SQLException {
		ResultSet rsLand = retrieveRS(SqlAbfrage.SQL_LAENDER);
		try {
			while (rsLand.next()) {
				Land l = new Land();
				l.setId(rsLand.getInt("ID"));
				l.setName(rsLand.getString("Name"));

				laender.add(l);
			}
		} catch (SQLException e) {
			System.out.println("Leander sind nicht vorhanden");
			e.printStackTrace();
		}
	}
	
	public Land getLand(int id){
		for (Land l : laender){
			if(l.getId() == id){
				return l;
			}
		}
		return null;
		
	}

}
