package domain;


import java.math.BigDecimal;
import java.sql.Date;

public class Tanken {

	private int kmStand;
	private int landId;
	private int ortId;
	private int voll;
	private BigDecimal kosten;
	private int autoId;
	private Date datum;
	private BigDecimal liter;
	private BigDecimal preisProLiter;
	private int benzinArtId;

	private static Berechnung berechne = new Berechnung();

	public String getPreisProLiter() {

		return berechne.getRound(preisProLiter, 3);
	}

	public void setPreisProLiter(BigDecimal preisProLiter) {
		this.preisProLiter = preisProLiter;
	}

	public int getBenzinArtId() {
		return benzinArtId;
	}

	public void setBenzinArtId(int benzinArtId) {
		this.benzinArtId = benzinArtId;
	}

	public int getVoll() {
		return voll;
	}

	public void setVoll(int b) {
		this.voll = b;
	}

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

	public String getKosten() {
		
		return berechne.getRound(kosten, 3);

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

	public String getLiter() {
		return berechne.getRound(liter, 2);
	}

	public void setLiter(BigDecimal liter) {
		this.liter = liter;
	}


}
