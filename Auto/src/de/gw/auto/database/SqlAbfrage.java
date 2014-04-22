package de.gw.auto.database;

import de.gw.auto.domain.Settings;

public class SqlAbfrage extends SqlServer {

	Settings setting = new Settings();

	public SqlAbfrage() {

	}

	public SqlAbfrage(Settings setting) {
		
		this.setting.setAuto(setting.getAuto());
	}

	public final String SQL_AUTO = "SELECT Auto_ID, Kennzeichen, Anfangs_Km, Kauf_Datum, ErstZulassung, Aktuell_Km FROM dbo.T_Auto;";
	// + "WHERE Kennzeichen = "
	// + "'" + setting.getAuto().getKfz() + "';" ;
	public final static String SQL_BENZINART = "SELECT Benzienart, ID FROM dbo.T_Benzinart;";
	public final static String SQL_ORT = "SELECT ID, Name, LandID FROM T_Ort;";
	public final static String SQL_LAENDER = "SELECT ID, Name FROM dbo.T_Land;";
	public final static String SQL_Tankfuellung = "SELECT Id, Beschreibung FROM T_Tank;";

	public String getSonstigeAusgaben(Settings setting) {
		final String sqlSonstigeAusgaben = "SELECT ID_Sons_Ausgaben, Datum, Km_Stand, Komentar, Kosten, Auto_ID FROM T_Sonstige_Ausgaben "
				+ "WHERE Auto_ID = " + setting.getAuto().getId() + ";";
		return sqlSonstigeAusgaben;

	}
	
	public String getTanken() {

		final String sqlTanken = "SELECT ID, Km_Stand, Land, Ort, Voll, Kosten, Auto_ID, "
				+ "Datum, Liter, Preis_p_Liter, BenzinartID FROM T_Tanken WHERE Auto_ID = "
				+ setting.getAuto().getId();
		
		return sqlTanken;
	}

	// public static ResultSet giveAuto(){
	// return datenAbfragen(sqlAuto);
	// }

}
