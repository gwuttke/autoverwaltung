package gui;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlServer;
import domain.Auto;
import domain.Benzinart;
import domain.Land;
import domain.Ort;
import domain.SonstigeAusgaben;
import domain.Tanken;

public class Willkommen extends SqlServer {
	// Listen Declarieren

	List<Auto> autoList = new ArrayList<Auto>();
	List<Benzinart> benzinartList = new ArrayList<Benzinart>();
	List<Land> landList = new ArrayList<Land>();
	List<Ort> ortList = new ArrayList<Ort>();
	List<SonstigeAusgaben> sonstigeAusgabenList = new ArrayList<SonstigeAusgaben>();
	List<Tanken> tankenList = new ArrayList<Tanken>();
	

	private static final SqlServer sqlS = new SqlServer();
	public static final Willkommen wk = new Willkommen();
	String sql = null;

	// Listen füllen
	public static void main(String[] args) {

		System.out.println("Datenbank abfrage");
		
		openConnection();
		wk.setBenzinartList();
		wk.setAutoList();
		wk.setLandList();
		wk.setOrtList();
		wk.setSonstigeAusgabenList();
		wk.setTankenList();
		//sqlS.closeResults(sqlS.getSt(), sqlS.getRs(), sqlS.getConn());

		System.out.println("Datenbankabfragen wurden erfolgreich beendet");

	}

	public List<Auto> getAutoList() {
		return autoList;
	}

	//@SuppressWarnings("deprecation")
	private void setAutoList() {
		sql = "SELECT Auto_ID, Kennzeichen, Anfangs_Km, Kauf_Datum, ErstZulassung, Aktuell_Km FROM T_Auto";

		ResultSet rsAuto = giveRs(sql);

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
				this.autoList.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Benzinart> getBenzinartList() {
		return benzinartList;
	}

	public void setBenzinartList() {
		sql = "SELECT ID, Benzienart FROM T_Benzinart";

		ResultSet rsBenzinart = giveRs(sql);
		
			try {
				while (rsBenzinart.next()) {
					Benzinart benzinart = new Benzinart();
					benzinart.setId(rsBenzinart.getInt("ID"));
					benzinart.setName(rsBenzinart.getString("Benzienart"));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public List<Land> getLandList() {
		return landList;
	}

	public void setLandList() {

		sql = "SELECT ID, Name FROM T_Land";

		ResultSet rsLand = giveRs(sql);

		// ResultSet durchlaufen und jedes element zur Liste landList hinzufügen
		try {
			while (rsLand.next()) {
				Land l = new Land();
				l.setId(rsLand.getInt("ID"));
				l.setName(rsLand.getString("Name"));

				this.landList.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Ort> getOrtList() {
		return ortList;
	}

	public void setOrtList() {
		sql = "SELECT ID, Name, LandID FROM T_Ort";

		ResultSet rsOrt = giveRs(sql);

		if (rsOrt != null){ 
		try {
			while (rsOrt.next()) {
				Ort ort = new Ort();
				ort.setId(rsOrt.getInt("ID"));
				ort.setOrt(rsOrt.getString("Name"));
				ort.setLandId(rsOrt.getInt("LandID"));

				this.ortList.add(ort);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
			System.out.println("es gibt kein resultset");
		}

	}

	public List<SonstigeAusgaben> getSonstigeAusgabenList() {
		return sonstigeAusgabenList;
	}

	public void setSonstigeAusgabenList() {
		sql = "SELECT ID_Sons_Ausgaben, Datum, Km_Stand, Komentar, Kosten, Auto_ID FROM T_Sonstige_Ausgaben";

		ResultSet rsSonsAusg = giveRs(sql);

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

	public List<Tanken> getTankenList() {
		return tankenList;
	}

	public void setTankenList() {
		sql = "SELECT ID_Tanken, Km_Stand, Land, Ort, Voll, Kosten, Auto_ID, "
				+ "Datum, Liter, Preis_p_Liter, Benzinart FROM T_Tanken";

		ResultSet rsTanken = giveRs(sql);

		try {
			while (rsTanken.next()) {
				Tanken t = new Tanken();

				t.setAutoId(rsTanken.getInt("Auto_ID"));
				t.setBenzinArtId(rsTanken.getInt("Benzinart"));
				t.setKmStand(rsTanken.getInt("Km_Stand"));
				 t.setKosten((BigDecimal)rsTanken.getBigDecimal("Kosten"));
				t.setLandId(rsTanken.getInt("Land"));
				t.setLiter(rsTanken.getBigDecimal("Liter"));
				t.setOrtId(rsTanken.getInt("Ort"));
				t.setPreisProLiter(rsTanken.getBigDecimal("Preis_p_Liter"));
				t.setVoll(rsTanken.getInt("Voll"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private ResultSet giveRs(String sql) {
		try {
			return retrieveRS(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Es wird null zurückgegeben");
			return null;
		}
	}
}
