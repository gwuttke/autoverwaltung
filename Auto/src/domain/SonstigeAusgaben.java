package domain;

import java.math.BigDecimal;
import java.sql.Date;

public class SonstigeAusgaben {
	private Date datum;
	private int kmStand;
	private String kommentar;
	private BigDecimal kosten;
	private int AutoId;

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
		return kosten;
	}

	public void setKosten(BigDecimal kosten) {
		this.kosten = kosten;
	}

	public int getAutoId() {
		return AutoId;
	}

	public void setAutoId(int autoId) {
		AutoId = autoId;
	}

}
