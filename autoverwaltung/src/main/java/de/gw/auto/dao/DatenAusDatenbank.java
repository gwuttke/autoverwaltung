package de.gw.auto.dao;

import java.util.List;

import de.gw.auto.domain.Autoverwaltung;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.hibernate.DatenbankZugriff;

public class DatenAusDatenbank extends DatenbankZugriff{
	
	Autoverwaltung autoverwaltung = new Autoverwaltung();
	DatenAbrufen datenAbrufen = new DatenAbrufen();
	
	private List<Benutzer> giveBenutzer() throws Exception{
		return datenAbrufen.getBenutzer();
	}
	

}
