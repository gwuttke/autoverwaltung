package database;

import java.sql.ResultSet;

import domain.Settings;



public class SqlAbfrage extends SqlServer {
	public final static String SQL_AUTO = "SELECT Auto_ID, Kennzeichen, Anfangs_Km, Kauf_Datum, ErstZulassung, Aktuell_Km FROM dbo.T_Auto ";
		//	+ "WHERE Kennzeichen = "
		//	+ "'" + Settings.getAuto().getKfz() + "'" ;
	public final static String SQL_BENZINART = "SELECT Benzienart, ID FROM dbo.T_Benzinart";
//	public static ResultSet giveAuto(){
//		 return datenAbfragen(sqlAuto); 
//	}

}
