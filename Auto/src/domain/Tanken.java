package domain;

import java.math.BigDecimal;
import java.sql.Date;

public class Tanken {

	private int kmStand;
	private int landId;
	private int ortId;
	private boolean voll;
	private BigDecimal kosten;
	private int autoId;
	private Date datum;
	private double liter;
	public int getKmStand() {
		return kmStand;
	}
	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
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
	public boolean isVoll() {
		return voll;
	}
	public void setVoll(boolean voll) {
		this.voll = voll;
	}
	public BigDecimal getKosten() {
		return kosten;
	}
	public void setKosten(BigDecimal kosten) {
		this.kosten = kosten;
	}
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public double getLiter() {
		return liter;
	}
	public void setLiter(double liter) {
		this.liter = liter;
	}
	
	
	

}
