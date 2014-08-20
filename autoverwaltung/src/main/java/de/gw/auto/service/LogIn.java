package de.gw.auto.service;

import de.gw.auto.dao.BenutzerDAO;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Settings;
import de.gw.auto.exception.AllException;

public class LogIn {

	public Settings Login(String benutzerName, String passwort) throws Exception{
		BenutzerDAO benutzerDao = new BenutzerDAO();
		Benutzer benutzer = new Benutzer(benutzerName, passwort);
		benutzerDao.setBenutzer(benutzer);
		
		benutzer = benutzerDao.getBenutzer();
		
		if (benutzer == null){
			AllException.error("Benutzername oder Passwort ist Falsch, "
					+ "bitte registrieren Sie sich wenn Sie noch kein Konto haben", new IllegalArgumentException() );
			return null;
		}
		Settings settings = new Settings(benutzer);
		System.out.println("LogIn!!");
		return settings;
		
	}
	
	
}