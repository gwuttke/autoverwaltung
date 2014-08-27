package de.gw.auto.dao;

import de.gw.auto.domain.Benutzer;
import de.gw.auto.exception.AllException;
import de.gw.auto.hibernate.DatenAbrufen;

public class BenutzerDAO {

	
	Benutzer benutzer = null;
	
	public void setBenutzer(Benutzer loginBenutzer){
		
		try {
			 this.benutzer = new DatenAbrufen().getBenutzer(loginBenutzer);
		} catch (Exception e) {
			AllException.messageBox("Falscher Benutzer", "Dieser Benutzer ist nicht vorhanden");
		}
	}
	
	public Benutzer getBenutzer() {
		return this.benutzer;
	}
	
	

}
