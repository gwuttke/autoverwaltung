package de.gw.auto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.gw.auto.hibernate.DatenAbrufen;

public class Settings {

	private DatenAbrufen da = new DatenAbrufen();

	private List<Auto> autos = new ArrayList<Auto>();
	private Benutzer benutzer = null;
	private Auto aktuellAuto;

	public Settings(Benutzer benutzer) {
		this.benutzer = benutzer;
		load();
	}
	
	public DatenAbrufen getDaten(){
		return da;
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
	
	public Auto[] getAutosArray(){
		Auto[] autos = new Auto[this.autos.size()];
		int i = 0;
		for (Auto a : this.autos){
			autos[i] = a;
			i++;
		}
		return autos;
	}
	
	public void set(Settings setting){
		this.aktuellAuto = setting.aktuellAuto;
		this.autos = setting.autos;
		this.benutzer = setting.benutzer;
	}
	
	public Set<Benzinart> getAutoBenzinarten(){
		return aktuellAuto.getBenzinarten();
	}
	
	public void load(){
		autos = da.getAutos(benutzer);
		if(autos.isEmpty()){
			return;
		}
		aktuellAuto = autos.get(autos.size() -1);
	}

}
