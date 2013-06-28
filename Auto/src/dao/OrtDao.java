package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlServer;

import domain.Land;
import domain.Ort;

public class OrtDao {

	private List<Ort> orte = new ArrayList<Ort>();
	private List<Ort> landOrte = new ArrayList<Ort>();
	private SqlServer sqlS = new SqlServer();
	private LandDao l = new LandDao();

	public List<Ort> getOrte() {
		return orte;
	}

	public void setOrte() {
		ResultSet rs = getOrt();

		if (rs != null) {
			try {
				while (rs.next()) {
					final Ort ort = new Ort();
					ort.setOrt(rs.getString("Name"));
					ort.setId(rs.getInt("ID"));
					ort.setLandId(rs.getInt("LandID"));
					orte.add(ort);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("keine Orte Vorhanden");
		}

	}

	public List<Ort> getLandOrte() {
		return landOrte;
	}

	public void setLandOrte(List<Ort> landOrte) {
		this.landOrte = landOrte;
	}

	public void updateOrte(String land) {
		int landID = -1;
		for (Land la : l.getLänder()) {
			if (la.getName() == land) {
				landID = la.getId();
			}

		}
		if (landID > -1) {
			for (Ort o : orte) {
				if (o.getLandId() == landID) {
					final Ort ort = new Ort();
					ort.setOrt(o.getOrt());
					landOrte.add(ort);
				}

			}
		}
	}

	private ResultSet getOrt() {
		ResultSet ortRS = null;

		String sql = "SELECT ID, Name, LandID FROM T_Ort";

		try {
			ortRS = sqlS.retrieveRS(sql);
			return ortRS;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Es wurden keine Orte geladen");
		}

		return ortRS;

	}
}
