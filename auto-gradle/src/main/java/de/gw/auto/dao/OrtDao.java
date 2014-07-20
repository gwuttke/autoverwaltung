package de.gw.auto.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.gw.auto.database.SqlAbfrage;
import de.gw.auto.database.SqlServer;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;

public class OrtDao extends SqlServer{

	private static LandDao lDao = new LandDao();
	private static List<Ort> orte = new ArrayList<Ort>();
	
	
	public List<Ort> getOrtList() {
		return orte;
	}

	public static void setOrtList() throws SQLException {
		ResultSet rsOrt = retrieveRS(SqlAbfrage.SQL_ORT);

		if (rsOrt != null) {
			try {
				while (rsOrt.next()) {
					Ort ort = new Ort();
					ort.setId(rsOrt.getInt("ID"));
					ort.setOrt(rsOrt.getString("Name"));
					ort.setLand(lDao.getLand(rsOrt.getInt("LandID")));

					orte.add(ort);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("es gibt kein resultset");
		}

	}
	
	public Ort getOrt(int id){
		for(Ort o : orte){
			if (o.getId() == id){
				return o;
			}
		}
		return null;
	}

	private Land getLand(int landId) {
		for (Land l : lDao.getLaender()) {
			if (l.getId() == landId) {
				return l;
			}
		}
		return null;
	}

	public List<Ort> getOrteInLand(Land land) {
		List<Ort> l = new ArrayList<Ort>();

		for (Ort o : orte) {
			if (o.getLand().equals(land)) {
				l.add(o);
			}
		}
		return l;
	}
}
