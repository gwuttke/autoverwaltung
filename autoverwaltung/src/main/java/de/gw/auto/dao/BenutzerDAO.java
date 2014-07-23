package de.gw.auto.dao;

import java.util.HashSet;
import java.util.Set;

import de.gw.auto.domain.Benutzer;
import de.gw.auto.exception.AllException;
import de.gw.auto.hibernate.DatenAbrufen;

public class BenutzerDAO {

	
	Benutzer benutzer = null;
	
	public static void setBenutzer(Benutzer benutzer){
		
		try {
			benutzer = new DatenAbrufen().getBenutzer(benutzer);
		} catch (Exception e) {
			AllException.messageBox("Falscher Benutzer", "Dieser Benutzer ist nicht vorhanden");
		}
	}
	
	public Benutzer getBenutzer() {
		return benutzer;
	}
	
	

}
