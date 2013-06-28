package domain;


import java.sql.Date;

public class Tanken {

	private int kmStand;
	private int landId;
	private int ortId;
	private int voll;
	private double kosten;
	private int autoId;
	private Date datum;
	private double liter;
	private double preisProLiter;
	private int benzinArtId;

	private static Berechnung berechne = new Berechnung();

	public double getPreisProLiter() {

		return preisProLiter;
	}

	public void setPreisProLiter(double preisProLiter) {
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

	public void setVoll(int voll) {
		this.voll = voll;
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

	public void setKosten(double kosten) {
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

	public void setLiter(double liter) {
		this.liter = liter;
	}


}
