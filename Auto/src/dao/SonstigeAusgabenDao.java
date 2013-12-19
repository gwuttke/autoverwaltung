package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlAbfrage;
import database.SqlServer;
import domain.SonstigeAusgaben;

public class SonstigeAusgabenDao extends SqlServer {


	private static List<SonstigeAusgaben> sonstigeAusgabenList = new ArrayList<SonstigeAusgaben>();


	public static List<SonstigeAusgaben> getSonstigeAusgabenList() {
		return sonstigeAusgabenList;
	}

	public static void setSonstigeAusgabenList() throws SQLException {
		
		ResultSet rsSonsAusg = retrieveRS(SqlAbfrage.SQL_SONSTIGE_AUSGABEN);

		try {
			while (rsSonsAusg.next()) {
				SonstigeAusgaben sA = new SonstigeAusgaben();

				sA.setAutoId(rsSonsAusg.getInt("Auto_ID"));
				sA.setDatum(rsSonsAusg.getDate("Datum"));
				sA.setKmStand(rsSonsAusg.getInt("Km_Stand"));
				sA.setKommentar(rsSonsAusg.getString("Komentar"));
				sA.setKosten(rsSonsAusg.getBigDecimal("Kosten"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateListe() throws SQLException{
		getSonstigeAusgabenList().clear();
		setSonstigeAusgabenList();
	}

}
