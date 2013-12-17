package database;

import java.sql.ResultSet;



public class SqlAbfrage extends SqlServerOverJNDI {
	private final static String sqlAuto = "SELECT Auto_ID, Kennzeichen, Anfangs_Km, Kauf_Datum, ErstZulassung, Aktuell_Km FROM dbo.T_Auto";
	//'" + settings.getAuto().getKfz() + "'" ;
	
	public static ResultSet giveAuto(){
		 return datenAbfragen(sqlAuto); 
	}

}
