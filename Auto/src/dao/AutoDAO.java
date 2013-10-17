package dao;

import gui.Willkommen;

import java.sql.Date;


import database.Procedures;
import database.SqlServer;
import domain.Auto;

public class AutoDAO extends Willkommen{
	SqlServer sqlS = new SqlServer();
	Procedures proc = new Procedures();
	Auto a = new Auto();

	
	public int CarIntoDatabase(String kfz, int kaufKm, Date kaufDatum, Date erstZulassung, int[] benzinArten){
		proc.setAddAuto(kfz, kaufKm, kaufDatum, erstZulassung, benzinArten);
		
		return 0;
	}

	public void updateAutoList(int autoID, int[] benzinarten, Date eZulassung, Date kauf, int anfKm, int aktuKm){
	for (Auto a : getAutoList()){
		if (a.getId() == autoID){
			if (benzinarten != null){
				a.setBenzinArten(benzinarten);
			}
			if (eZulassung != null){
				a.setErstZulassung((java.sql.Date) eZulassung);
			}
			if (kauf != null){
				a.setKauf((java.sql.Date) kauf);
			}
			if (anfKm > 0){
				a.setKmKauf(anfKm);
			}
			if (aktuKm > 0 && aktuKm > a.getKmKauf()){
				a.setKmAktuell(aktuKm);
				a.setKmGefahren();
			}
			
		}
	}
	
	}
}
