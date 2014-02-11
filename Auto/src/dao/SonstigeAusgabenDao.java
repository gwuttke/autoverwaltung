package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlAbfrage;
import database.SqlServer;
import domain.Settings;
import domain.SonstigeAusgaben;

public class SonstigeAusgabenDao extends SqlServer {

	private Settings setting;
	
	
	public SonstigeAusgabenDao(Settings setting) {
		super();
		this.setting = setting;
	}



	private List<SonstigeAusgaben> sonstigeAusgabenList = new ArrayList<SonstigeAusgaben>();


	public List<SonstigeAusgaben> getSonstigeAusgabenList() {
		return sonstigeAusgabenList;
	}

	public void setSonstigeAusgabenList(Settings setting) throws SQLException {
	 SqlAbfrage abfrage = new SqlAbfrage(setting);
		ResultSet rsSonsAusg = retrieveRS(abfrage.getSonstigeAusgaben(setting));

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
		setSonstigeAusgabenList(setting);
	}

}
