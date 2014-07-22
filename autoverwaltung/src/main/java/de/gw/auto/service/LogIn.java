package de.gw.auto.service;

import de.gw.auto.dao.BenutzerDAO;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Settings;
import de.gw.auto.exception.AllException;

public class LogIn {

	public Settings LogIn(String benutzerName, String passwort) throws Exception{
		
		Benutzer benutzer = new Benutzer(benutzerName, passwort);
		
		BenutzerDAO benutzerDAO = new BenutzerDAO();
		benutzerDAO.setBenutzer(benutzer);
		
		benutzer = benutzerDAO.getBenutzer();
		
		if (benutzer == null){
			AllException.error("Benutzername oder Passwort ist Falsch, "
					+ "bitte registrieren Sie sich wenn Sie noch kein Konto haben", new IllegalArgumentException() );
			return null;
		}
		Settings settings = new Settings(benutzer);
		return settings;
		
	}
	
	
}
