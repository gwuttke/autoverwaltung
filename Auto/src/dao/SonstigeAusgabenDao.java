package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlServer;
import domain.SonstigeAusgaben;

public class SonstigeAusgabenDao {

	private SqlServer sqlS = new SqlServer();

	private List<SonstigeAusgaben> sonstigeAusgabenList = new ArrayList<SonstigeAusgaben>();
	private ResultSet sonstigeAusgabenRS = null;

	public List<SonstigeAusgaben> getSonstigeAusgabenList() {
		if (sonstigeAusgabenList != null) {
			return sonstigeAusgabenList;
		} else {
			this.setSonstigeAusgabenList();
			this.getSonstigeAusgabenList();
		}
		return sonstigeAusgabenList;
	}

	public void setSonstigeAusgabenList() {

		ResultSet rs = getSonstigeAusgaben();

		try {
			while (rs.next()) {
				final SonstigeAusgaben sonsAusgaben = new SonstigeAusgaben();
				sonsAusgaben.setAutoId(rs.getInt("Auto_ID"));
				sonsAusgaben.setDatum(rs.getDate("Datum"));
				sonsAusgaben.setKmStand(rs.getInt("KM_Stand"));
				sonsAusgaben.setKommentar(rs.getString("Kommentar"));
				sonsAusgaben.setKosten(rs.getBigDecimal("Kosten"));
				sonstigeAusgabenList.add(sonsAusgaben);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private ResultSet getSonstigeAusgaben() {

		String sql = "SELECT ID_Sons_Ausgaben, Datum, Km_Stand, Kommentar, Kosten, Auto_ID FROM T_Land";

		try {
			sonstigeAusgabenRS = sqlS.retrieveRS(sql);
			return sonstigeAusgabenRS;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sonstigeAusgabenRS;

	}
	
	public void updateListe(){
		this.getSonstigeAusgabenList().clear();
		this.setSonstigeAusgabenList();
	}

}
