package de.gw.auto.view.model;

import java.math.BigDecimal;
import java.util.Date;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Tank;

public class NewTanken extends ListTanken {
	private int kmStand;

	private Land land;

	private Ort ort;

	private Tank tank;

	private BigDecimal kosten;

	private Date datum;

	private BigDecimal liter;

	private BigDecimal preisProLiter;

	private Kraftstoffsorte benzinart;

	private Auto auto;

	public int getKmStand() {
		return kmStand;
	}

	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
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

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	public BigDecimal getKosten() {
		return kosten;
	}

	public void setKosten(BigDecimal kosten) {
		this.kosten = kosten;
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

	public void setLiter(BigDecimal liter) {
		this.liter = liter;
	}

	public BigDecimal getPreisProLiter() {
		return preisProLiter;
	}

	public void setPreisProLiter(BigDecimal preisProLiter) {
		this.preisProLiter = preisProLiter;
	}

	public Kraftstoffsorte getBenzinart() {
		return benzinart;
	}

	public void setBenzinart(Kraftstoffsorte benzinart) {
		this.benzinart = benzinart;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
}
