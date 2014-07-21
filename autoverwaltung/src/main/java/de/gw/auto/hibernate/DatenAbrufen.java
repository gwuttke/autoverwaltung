package de.gw.auto.hibernate;

import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Benzinart;

public class DatenAbrufen extends DatenbankZugriff {

	private List<Benzinart> benzinarten = new ArrayList<Benzinart>();

	
	
	public DatenAbrufen() throws Exception {
		super();
		giveBenzinarten();
	}

	private void giveBenzinarten() throws Exception {
		benzinarten = (List<Benzinart>) this.select(this.FROM + "Benzinart");
	}

	public List<Benzinart> getBenzinarten() {
		return benzinarten;
	}	

}
