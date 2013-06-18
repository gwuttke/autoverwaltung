package domain;

import java.sql.Date;

public class Auto {

	private String kfz;
	private int id;
	private double km;
	private Date kauf;
	private Date erstZulassung;
	private String[] benzinArten;

	public String[] getBenzinArten() {
		return benzinArten;
	}

	public void setBenzinArten(String[] benzinArten) {
		benzinArten = benzinArten;
	}

	public String getKfz() {
		return kfz;
	}

	public void setKfz(String kfz) {
		this.kfz = kfz;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

	public Date getKauf() {
		return kauf;
	}

	public void setKauf(Date kauf) {
		this.kauf = kauf;
	}

	public Date getErstZulassung() {
		return erstZulassung;
	}

	public void setErstZulassung(Date erstZulassung) {
		this.erstZulassung = erstZulassung;
	}


	
	
	
}
