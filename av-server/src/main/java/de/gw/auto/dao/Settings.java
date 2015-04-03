package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.service.AutoService;
import de.gw.auto.service.BenutzerService;

@Service
public class Settings {

	@Autowired
	AutoService autoService;

	@Autowired
	BenutzerService benutzerService;

	private List<Auto> autos = new ArrayList<Auto>();
	private Benutzer benutzer = null;
	private Auto aktuellAuto;
	
	protected Settings(){}

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

		autos = autoService.findByBenutzer(benutzer);
	}

	public void addAuto(Auto auto) {
		autos.add(auto);
		setAktuellAuto(autos.get(autos.size() - 1));
	}

	public List<Auto> getAutos() {
		return autos;
	}

	public Auto[] getAutosArray() {
		Auto[] autos = new Auto[this.autos.size()];
		int i = 0;
		for (Auto a : this.autos) {
			autos[i] = a;
			i++;
		}
		return autos;
	}

	public void set(Settings setting) {
		this.aktuellAuto = setting.aktuellAuto;
		this.autos = setting.autos;
		this.benutzer = setting.benutzer;
	}

	public Set<Benzinart> getAutoBenzinarten() {
		return aktuellAuto.getBenzinarten();
	}

	public void load() {
		autos = autoService.findByBenutzer(benutzer);
		if (autos.isEmpty()) {
			return;
		}
		aktuellAuto = autos.get(autos.size() - 1);
	}

}
