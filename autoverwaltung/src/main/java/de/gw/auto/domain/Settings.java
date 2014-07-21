package de.gw.auto.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.gw.auto.hibernate.DatenAbrufen;

public class Settings {

	DatenAbrufen da = new DatenAbrufen();

	private List<Auto> autos = new ArrayList<Auto>();
	private Benutzer benutzer = null;

	public Settings(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public void setAutos() throws SQLException {

		autos = da.getAutos(benutzer);

	}

	public List<Auto> getAutos() throws SQLException {
		for (Auto a : autos) {
			if (a.getBenutzer() == benutzer) {
				return autos;
			} else {
				setAutos();
				getAutos();
			}
		}
		return autos;
	}

}
