package de.gw.auto.view.model;

import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;

public class ListGeneral {
	private List<Land> laender;

	private List<Ort> orte = new ArrayList<Ort>();

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
}
