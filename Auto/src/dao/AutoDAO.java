package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Procedures;
import database.SqlAbfrage;
import database.SqlServer;
import domain.Auto;
import domain.Benzinart;

public class AutoDAO extends SqlServer {
	
	Procedures proc = new Procedures();
	private static List<Auto> autoList = new ArrayList<Auto>();
	Auto a = new Auto();

	public int CarIntoDatabase(String kfz, int kaufKm,
			java.util.Date kaufDatum, java.util.Date erstZulassung,
			Benzinart[] benzinArten) {
		java.sql.Date erstSqlDate = new java.sql.Date(erstZulassung.getTime());
		java.sql.Date kaufSqlDate = new java.sql.Date(kaufDatum.getTime());

		proc.setAddAuto(kfz, kaufKm, kaufSqlDate, erstSqlDate, benzinArten);

		return 0;
	}

	public static void updateAutoList(int autoID, Benzinart[] benzinarten,
			Date eZulassung, Date kauf, int anfKm, int aktuKm) {
		for (Auto a : getAutoList()) {
			if (a.getId() == autoID) {
				if (benzinarten != null) {
					a.setBenzinArten(benzinarten);
				}
				if (eZulassung != null) {
					a.setErstZulassung((java.sql.Date) eZulassung);
				}
				if (kauf != null) {
					a.setKauf((java.sql.Date) kauf);
				}
				if (anfKm > 0) {
					a.setKmKauf(anfKm);
				}
				if (aktuKm > 0 && aktuKm > a.getKmKauf()) {
					a.setKmAktuell(aktuKm);
					a.setKmGefahren();
				}

			}
		}

	}
	
	public static void setAutoList() throws SQLException {

		ResultSet rsAuto = retrieveRS(SqlAbfrage.SQL_AUTO);
		
		try {
			while (rsAuto.next()) {
				Auto a = new Auto();
				a.setId(rsAuto.getInt("Auto_ID"));
				a.setKfz(rsAuto.getString("Kennzeichen"));
				a.setKmKauf(rsAuto.getInt("Anfangs_Km"));
				a.setKmAktuell(rsAuto.getInt("Aktuell_Km"));
				a.setKauf(rsAuto.getDate("Kauf_Datum"));
				if (rsAuto.getDate("ErstZulassung") != null) {
					a.setErstZulassung(rsAuto.getDate("ErstZulassung"));
				}
				a.setAlter();
				a.setKmGefahren();
				autoList.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeResults(getSt(), getRs(), null);
		}

	}

	public static List<Auto> getAutoList() {
		return autoList;
	}
	
	public static Auto getAuto(String kennzeichen){
		for (Auto a : autoList){
			if (a.getKfz().equals(kennzeichen)){
				return a;
			}
		}
		return null;
	}

}
