package de.gw.auto.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.gw.auto.hibernate.DatenAbrufen;

public class Settings {

	DatenAbrufen da = new DatenAbrufen();

	private List<Auto> autos = new ArrayList<Auto>();
	private Benutzer benutzer = null;
	private Auto aktuellAuto;

	public Settings(Benutzer benutzer) {
		this.benutzer = benutzer;
		load();
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}
	
	public Auto getAktuellAuto() {
		return aktuellAuto;
	}

	public void setAktuellAuto(Auto aktuellAuto) {
		this.aktuellAuto = aktuellAuto;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public void setAutos() {

		autos = da.getAutos(benutzer);

	}
	
	public void addAuto(Auto auto){
		autos.add(auto);
		 setAktuellAuto(autos.get(autos.size() -1));
	}

	public List<Auto> getAutos() {
		return autos;
	}
	
	public void set(Settings setting){
		this.aktuellAuto = setting.aktuellAuto;
		this.autos = setting.autos;
		this.benutzer = setting.benutzer;
	}
	
	public void load(){
		autos = da.getAutos(benutzer);
		if(autos.isEmpty()){
			return;
		}
		aktuellAuto = autos.get(autos.size() -1);
	}

}
