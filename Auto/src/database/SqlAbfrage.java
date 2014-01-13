package database;


import domain.Settings;



public class SqlAbfrage extends SqlServer {
	public final static String SQL_AUTO = "SELECT Auto_ID, Kennzeichen, Anfangs_Km, Kauf_Datum, ErstZulassung, Aktuell_Km FROM dbo.T_Auto;";
		//	+ "WHERE Kennzeichen = "
		//	+ "'" + Settings.getAuto().getKfz() + "'" ;
	public final static String SQL_BENZINART = "SELECT Benzienart, ID FROM dbo.T_Benzinart;";
	public final static String SQL_ORT = "SELECT ID, Name, LandID FROM T_Ort";
	public final static String SQL_LAENDER = "SELECT ID, Name FROM dbo.T_Land;";
	public final static String SQL_SONSTIGE_AUSGABEN = "SELECT ID_Sons_Ausgaben, Datum, Km_Stand, Komentar, Kosten, Auto_ID FROM T_Sonstige_Ausgaben WHERE Auto_ID = "
			+ Settings.getAuto().getId() ;
	public final static String SQL_TANKEN = "SELECT ID_Tanken, Km_Stand, Land, Ort, Voll, Kosten, Auto_ID, "
	+ "Datum, Liter, Preis_p_Liter, BenzinartID FROM T_Tanken WHERE Auto_ID = " + Settings.getAuto().getId() ;
//	public static ResultSet giveAuto(){
//		 return datenAbfragen(sqlAuto); 
//	}

}