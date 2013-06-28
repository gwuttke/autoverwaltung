package domain;


import java.sql.Date;

public class SonstigeAusgaben {
	private Date datum;
	private int kmStand;
	private String kommentar;
	private double kosten;
	private int autoId;

	private static Berechnung berechne = new Berechnung();
	
	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getKmStand() {
		return kmStand;
	}

	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public String getKosten() {
		return berechne.getRound(kosten, 2);
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

}
