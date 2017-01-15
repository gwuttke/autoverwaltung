package de.gw.auto.view.model;

import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.domain.Tank;

public class ListTanken extends ListGeneral{

	private List<Tank> fuellstaende = new ArrayList<Tank>();

	private List<Kraftstoffsorte> kraftstoffsorten = new ArrayList<Kraftstoffsorte>();


	public List<Kraftstoffsorte> getKraftstoffsorten() {
		return kraftstoffsorten;
	}

	public void setKraftstoffsorten(List<Kraftstoffsorte> kraftstoffsorten) {
		this.kraftstoffsorten = kraftstoffsorten;
	}

	public List<Tank> getFuellstaende() {
		return fuellstaende;
	}

	public void setFuellstaende(List<Tank> fuellstaende) {
		this.fuellstaende = fuellstaende;
	}
}
