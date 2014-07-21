package de.gw.auto.hibernate;

import de.gw.auto.domain.Auto;

public class UpdateDaten extends DatenbankZugriff {
	
	public void addAuto(Auto auto){
		speichern(auto);
	}

}
