package domain;

import java.math.BigDecimal;
import java.sql.Date;

public class SonstigeAusgaben {
	private Date datum;
	private int kmStand;
	private String kommentar;
	private double kosten;
	private int autoId;

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

	public BigDecimal getKosten() {
		BigDecimal b = new BigDecimal(kosten);
		b=b.setScale(2, BigDecimal.ROUND_HALF_UP);
		return b;
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

	public static void main(String[] args) {
		SonstigeAusgaben sonsAusg = new SonstigeAusgaben();

	}
}
