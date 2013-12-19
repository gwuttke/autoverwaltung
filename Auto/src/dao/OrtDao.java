package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlAbfrage;
import database.SqlServer;
import domain.Land;
import domain.Ort;

public class OrtDao extends SqlServer{

	private static List<Ort> orte = new ArrayList<Ort>();
	
	public static List<Ort> getOrtList() {
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
					ort.setLand(getLand(rsOrt.getInt("LandID")));

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

	private static Land getLand(int landId) {
		for (Land l : LandDao.getLaender()) {
			if (l.getId() == landId) {
				return l;
			}
		}
		return null;
	}

	public List<Ort> getOrteInLand(Land land) {
		List<Ort> l = null;

		for (Ort o : orte) {
			if (o.getLand().equals(land)) {
				l.add(o);
			}
		}
		return l;
	}
}
