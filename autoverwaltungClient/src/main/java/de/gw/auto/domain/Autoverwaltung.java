package de.gw.auto.domain;

import java.util.List;
import java.util.Set;

import de.gw.auto.hibernate.DatenAbrufen;

public class Autoverwaltung extends DatenAbrufen{

	private Set<Benutzer> benutzer;
	private Benutzer aktellBenutzer;
	private Set<Auto> autos;
	private Set<Benzinart> benzinarten;
	private Set<Land> laender;
	private Set<Ort> orte;
	private Set<SonstigeAusgaben> sonstigeAusgaben;
	private Set<Tank> betankungen;
	private Set<Tanken> tankfuellungen;
	

	public Autoverwaltung() {
	}
	
	public Autoverwaltung(Benutzer benutzer){
		aktellBenutzer = benutzer;
	}

	public synchronized int nextWeinId() {
		int maxId = 0;
		for (Auto auto : this.autos) {
			if (auto.getId() > maxId) {
				maxId = auto.getId();
			}
		}

		return maxId + 1;
	}

	public Set<Benutzer> addBenutzer(Benutzer benutzer) {
		this.benutzer.add(benutzer);
		return this.benutzer;
	}

	public Set<Auto> addAuto(Auto auto) {
		this.autos.add(auto);
		return this.autos;
	}

	public Set<Benzinart> addBenzinart(Benzinart benzinart) {
		this.benzinarten.add(benzinart);
		return this.benzinarten;
	}

	public Set<Land> addLand(Land land) {
		this.laender.add(land);
		return this.laender;
	}

	public Set<Ort> addOrt(Ort ort) {
		this.orte.add(ort);
		return this.orte;
	}

	public Set<SonstigeAusgaben> addSonstigeAusgaben(
			SonstigeAusgaben sonstigeAusgaben) {
		this.sonstigeAusgaben.add(sonstigeAusgaben);
		return this.sonstigeAusgaben;
	}

	public Set<Tank> addBetankung(Tank betankung) {
		this.betankungen.add(betankung);
		return this.betankungen;
	}

	public Set<Tanken> addTankfuellung(Tanken tankfuellung) {
		this.tankfuellungen.add(tankfuellung);
		return this.tankfuellungen;
	}

	private Benutzer searchBenutzer(Benutzer benutzer) {
		for (Benutzer b : this.benutzer) {
			if (b == benutzer) {
				return b;
			}
		}
		return null;
	}

	private Auto searchAuto(Auto auto) {
		for (Auto a : this.autos) {
			if (a == auto) {
				return a;
			}
		}
		return null;
	}

	public List<Auto> getAutos(Benutzer benutzer) {

		return searchBenutzer(benutzer).getAutos();
	}

	public Set<SonstigeAusgaben> getSonstigeAusgaben(Auto auto) {
		return searchAuto(auto).getSonstigeAusgaben();
	}

	public Set<Tanken> getTankfuellungen(Auto auto) {
		return searchAuto(auto).getTankfuellungen();
	}

	public Set<Auto> getAutos() {
		return autos;
	}

	public Set<SonstigeAusgaben> getSonstigeAusgaben() {
		return sonstigeAusgaben;
	}

	public Set<Tank> getBetankungen() {
		return betankungen;
	}

	public Set<Tanken> getTankfuellungen() {
		return tankfuellungen;
	}

	public void setBenutzer(Set<Benutzer> benutzer) {
		this.benutzer = benutzer;
	}

	public void setAutos(Set<Auto> autos) {
		this.autos = autos;
	}

	public void setBenzinarten(Set<Benzinart> benzinarten) {
		this.benzinarten = benzinarten;
	}

	public void setLaender(Set<Land> laender) {
		this.laender = laender;
	}

	public void setOrte(Set<Ort> orte) {
		this.orte = orte;
	}

	public void setSonstigeAusgaben(Set<SonstigeAusgaben> sonstigeAusgaben) {
		this.sonstigeAusgaben = sonstigeAusgaben;
	}

	public void setBetankungen(Set<Tank> betankungen) {
		this.betankungen = betankungen;
	}

	public void setTankfuellungen(Set<Tanken> tankfuellungen) {
		this.tankfuellungen = tankfuellungen;
	}
	
	private void load(){
		List<Auto> lAuto = this.getAutos(aktellBenutzer);
		List<Benzinart> lBenzinarten = this.getBenzinarten();
		List<Land> lLand = this.getLaender();
		
	}
	

}
