package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.hibernate.DatenAbrufen;

public class LandDao {

	private static List<Land> laender = new ArrayList<Land>();

	public List<Land> getLaender() {
		if (laender.isEmpty()){
			setLaender();
		}
		return laender;
	}

	public static void setLaender() {
		laender = new DatenAbrufen().getLaender();
	}

	public Land searchLand(int id) {
		for (Land l : laender) {
			if (l.getId() == id) {
				return l;
			}
		}
		return null;
	}

	public Set<Ort> getOrteByLand(Land land) {
		if (laender.isEmpty()) {
			setLaender();
			if (laender.isEmpty()){
				return null;
			}
		}
		return searchLand(land.getId()).getOrte();
	}
}
