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
	private int liter;
	private BigDecimal preisProLiter;

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

	public int getOrt() {
		return ort;
	}

	public void setOrt(int ort) {
		this.ort = ort;
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

	public int getLiter() {
		return Liter;
	}

	public void setLiter(int liter) {
		Liter = liter;
	}

	public BigDecimal getPreisProLiter() {
		return preisProLiter;
	}

	public void setPreisProLiter(BigDecimal preisProLiter) {
		this.preisProLiter = preisProLiter;
	}

}
