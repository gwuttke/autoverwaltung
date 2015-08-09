package de.gw.auto.view.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Kraftstoff;
import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Tank;

public class NewTanken extends ListTanken {
	private int kmStand;

	private int landId;

	private int ortId;

	private int tankId;
	
	private BigDecimal kosten;

	private Date datum;

	private BigDecimal liter;

	private BigDecimal preisProLiter;

	private int userKraftstoffsorte;

	private Auto auto;

	public List<Land> getLaender() {
		return laender;
	}

	public int getLandId() {
		return landId;
	}

	public void setLandId(int landId) {
		this.landId = landId;
	}

	public int getOrtId() {
		return ortId;
	}

	public void setOrtId(int ortId) {
		this.ortId = ortId;
	}

	public void setLaender(List<Land> laender) {
		this.laender = laender;
	}

	public int getKmStand() {
		return kmStand;
	}

	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
	}


	public int getTankId() {
		return tankId;
	}

	public void setTankId(int tankId) {
		this.tankId = tankId;
	}

	public BigDecimal getKosten() {
		return kosten;
	}

	public void setKosten(String kosten) {
		this.kosten = new BigDecimal(kosten);
	}

	public Date getDatum() {
		return datum;
	}

	/*
	 * public void setDatum(Date date) { this.datum = date; }
	 */
	public void setDatum(String datum) throws ParseException {
		this.datum = AutoModel.stringToDate(datum);
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

	public void setPreisProLiter(String preisProLiter) {
		this.preisProLiter = new BigDecimal(preisProLiter);
	}

	public int getUserKraftstoffsorte() {
		return userKraftstoffsorte;
	}

	public void setUserKraftstoffsorte(int userKraftstoffsorte) {
		this.userKraftstoffsorte = userKraftstoffsorte;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
}
