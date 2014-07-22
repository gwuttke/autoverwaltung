package de.gw.auto.dao;

import java.util.HashSet;
import java.util.Set;

import de.gw.auto.domain.Benutzer;
import de.gw.auto.hibernate.DatenAbrufen;

public class BenutzerDAO {

	Benutzer benutzer = null;
	
	public static void setBenutzer(Benutzer benutzer){
		benutzer = new DatenAbrufen().getBenutzer(benutzer);
	}
	
	public Benutzer getBenutzer() {
		return benutzer;
	}
	
	

}
