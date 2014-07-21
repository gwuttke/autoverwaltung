package de.gw.auto.domain;



import java.sql.ResultSet;
import java.sql.SQLException;

import de.gw.auto.exception.AllException;


public class Settings {

	// SqlAbfrage abfrage = new SqlAbfrage();

	private Auto auto = null;
	private static Benutzer benutzer = null;
	
	
	public Auto getAuto() {
		return auto;
	}
	
	

	public void setAuto(String kfz) throws SQLException {
		SqlAbfrage abfrage = new SqlAbfrage();
		ResultSet rs = SqlServer.retrieveRS(abfrage.SQL_AUTO);
		Auto a = null;
		while (rs.next()) {
			String dbKfz = rs.getString("Kennzeichen");
			if (dbKfz.equals(kfz)) {
				a = new Auto();
				a.setId(rs.getInt("Auto_ID"));
				a.setKfz(dbKfz);
				a.setKmKauf(rs.getInt("Anfangs_Km"));
				a.setKauf(rs.getDate("Kauf_Datum"));
				a.setErstZulassung(rs.getDate("ErstZulassung"));
				a.setKmAktuell(rs.getInt("Aktuell_Km"));
				this.auto = a;
				return;
			}
		}

		if (a.equals(null)) {
		AllException.messageBox(Texte.Error.Titel.FALSCHE_EINGABE,
					"Das Kennzeichen ist falsch");
		}
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
}
