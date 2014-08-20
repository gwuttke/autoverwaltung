package de.gw.auto.hibernate;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.domain.Tanken;

public class UpdateDaten extends DatenbankZugriff {
	
	public void addAuto(Auto auto){
		speichern(auto);
	}
	
	public void updateAuto(Auto auto){
		update(auto);
	}
	
	public void addSonstigeAusgaben(SonstigeAusgaben sa){
		speichern(sa);
	}

	public void addBenutzer(Benutzer benutzer) {
		speichern(benutzer);
	}
	
	public void addTanken(Tanken tanken){
		speichern(tanken);
	}
	
	public void updateBenutzer(Benutzer benutzer){
		update(benutzer);
	}
	
	

}
