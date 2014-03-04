package domain;

import gui.Button.Funktionen;

import java.sql.ResultSet;
import java.sql.SQLException;

import Exception.AllException;
import database.SqlAbfrage;
import database.SqlServer;

public class Settings {

	// SqlAbfrage abfrage = new SqlAbfrage();

	private Auto auto = null;
	private static Benutzer benutzer = null;

	public Auto getAuto() {
		return auto;
	}

	public Auto setAuto(String kfz) throws SQLException {
		SqlAbfrage abfrage = new SqlAbfrage();
		ResultSet rs = SqlServer.retrieveRS(abfrage.SQL_AUTO);
		Auto a = null;
		while (rs.next()) {
			String dbKfz = rs.getString("Kennzeichen");
			if (dbKfz.equals(kfz)) {
				a = new Auto();
				a.setId(rs.getInt("Auto_ID"));
				a.setKfz(rs.getString("Kennzeichen"));
				a.setKmKauf(rs.getInt("Anfangs_Km"));
				a.setKauf(rs.getDate("Kauf_Datum"));
				a.setErstZulassung(rs.getDate("ErstZulassung"));
				a.setKmAktuell(rs.getInt("Aktuell_Km"));
				return a;
			}
		}

		if (a.equals(null)) {
			AllException.messageBox(Texte.Error.Titel.FALSCHE_EINGABE,
					"Das Kennzeichen ist falsch");
		}
		return a;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
}
