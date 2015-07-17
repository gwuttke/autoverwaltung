package de.gw.auto.view.model;

import java.util.List;

import de.gw.auto.domain.Kraftstoff;
import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Tank;

public class ListTanken {
	List<Land> laender;

	private List<Ort> orte;

	private List<Tank> fuellstaende;

	private List<Kraftstoff> kraftstoffarten;

	public List<Land> getLaender() {
		return laender;
	}

	public void setLaender(List<Land> laender) {
		this.laender = laender;
	}

	public List<Ort> getOrte() {
		return orte;
	}

	public void setOrte(List<Ort> orte) {
		this.orte = orte;
	}

	public List<Tank> getFuellstaende() {
		return fuellstaende;
	}

	public void setFuellstaende(List<Tank> fuellstaende) {
		this.fuellstaende = fuellstaende;
	}

	public List<Kraftstoff> getKraftstoffarten() {
		return kraftstoffarten;
	}

	public void setKraftstoffarten(List<Kraftstoff> kraftstoffarten) {
		this.kraftstoffarten = kraftstoffarten;
	}
}
