package domain;

import java.sql.Date;


public class Auto {

	private String kfz;
	private int id;
	private int kmKauf;
	private Date kauf;
	private Date erstZulassung;
	private String[] benzinArten;
	private int kmAktuell;
	private int kmGefahren;
	private String autoAlter;
	private String kaufAlter;

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

	public int getKmKauf() {
		return kmKauf;
	}

	public void setKmKauf(int kmKauf) {
		this.kmKauf = kmKauf;
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

	public String[] getBenzinArten() {
		return benzinArten;
	}

	public void setBenzinArten(String[] benzinArten) {
		this.benzinArten = benzinArten;
	}

	public int getKmAktuell() {
		return kmAktuell;
	}

	public void setKmAktuell(int kmAktuell) {
		this.kmAktuell = kmAktuell;
	}

	public int getKmGefahren() {
		return kmGefahren;
	}

	public void setKmGefahren() {
		this.kmGefahren = berechneGefahreneKm();
	}

	public String getAutoAlter() {
		return autoAlter;
	}

	public void setAutoAlter(String autoAlter) {
		this.autoAlter = autoAlter;
	}

	public String getKaufAlter() {
		return kaufAlter;
	}

	public void setKaufAlter(String kaufAlter) {
		this.kaufAlter = kaufAlter;
	}

	private int berechneGefahreneKm() {

		if (getKmAktuell() > getKmKauf()) {
			return getKmAktuell() - getKmKauf();
		} else {
			return -1;
		}

	}
	
	private void berechneAutoAlter(){
		//Date datumHeute = 
		
		
		
	}

}
