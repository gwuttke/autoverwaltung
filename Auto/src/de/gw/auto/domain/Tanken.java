package de.gw.auto.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Tanken {

	private int kmStand;
	private Land land;
	private Ort ort;
	private Tank tank;
	private BigDecimal kosten;
	private Auto auto;
	private Date datum;
	private BigDecimal liter;
	private BigDecimal preisProLiter;
	private Benzinart benzinArt;
	private int gefahreneKm;

	private static Berechnung berechne = new Berechnung();

	public int getGefahreneKm() {
		return gefahreneKm;
	}
		
	
	public String getPreisProLiter() {

		return berechne.getRound(preisProLiter, 3);
	}

	public void setPreisProLiter(BigDecimal preisProLiter) {
		this.preisProLiter = preisProLiter;
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	public int getKmStand() {
		return kmStand;
	}

	public void setKmStand(int kmStand) {
		
		
		this.kmStand = kmStand;
		int akt = auto.getKmAktuell();
	this.gefahreneKm = this.kmStand - akt;
	
		
	}

	public BigDecimal getKosten() {
		return kosten;

	}

	public void setKosten(BigDecimal kosten) {
		this.kosten = kosten;
	}

	public Auto getAuto() {
		return auto;
	}


	public void setAuto(int autoId) {
		int id = autoId;
		
		
		
	}


	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public BigDecimal getLiter() {
		return liter;
	}
	
	public String getLiterString() {
		return berechne.getRound(liter, 2);
	}

	public void setLiter(BigDecimal liter) {
		this.liter = liter;
	}

	public Benzinart getBenzinArt() {
		return benzinArt;
	}

	public void setBenzinArt(Benzinart benzinArt) {
		this.benzinArt = benzinArt;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public Ort getOrt() {
		return ort;
	}

	public void setOrt(Ort ort) {
		this.ort = ort;
	}

	public String getKostenString() {
		return berechne.getRound(kosten, 3);

	}

}
