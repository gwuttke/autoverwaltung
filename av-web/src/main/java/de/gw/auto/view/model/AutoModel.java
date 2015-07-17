package de.gw.auto.view.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import de.gw.auto.domain.Kraftstoff;
import de.gw.auto.domain.Kraftstoffsorte;

public class AutoModel {
	private String kfz;

	private int kmKauf;

	private Date kauf;

	private Date erstZulassung;

	private List<Kraftstoff> kraftstoffarten = new ArrayList<Kraftstoff>();

	private Kraftstoff userKraftstoffart;

	private int kmAktuell;

	private int kmStart;

	public int getKmStart() {
		return kmStart;
	}

	public void setKmStart(int kmStart) {
		this.kmStart = kmStart;
	}

	public Kraftstoff getUserKraftstoffart() {
		return userKraftstoffart;
	}

	public void setUserKraftstoffart(Kraftstoff userKraftstoffart) {
		this.userKraftstoffart = userKraftstoffart;
	}

	public List<Kraftstoff> getKraftstoffarten() {
		return kraftstoffarten;
	}

	public void setKraftstoffarten(List<Kraftstoff> kraftstoffarten) {
		this.kraftstoffarten = kraftstoffarten;
	}

	public String getKfz() {
		return kfz;
	}

	public AutoModel() {
	}

	public void setKfz(String kfz) {
		this.kfz = kfz;
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

	public void setKauf(String kauf) throws ParseException{
		this.kauf =stringToDate(kauf);
	}

	private static Date stringToDate(String dateString) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd.mm.yyyy",Locale.GERMANY);
		return format.parse(dateString);
	}
	
	/*public void setKauf(Date kauf) {
		this.kauf = kauf;
	}
*/
	public Date getErstZulassung() {
		return erstZulassung;
	}

	public void setErstZulassung(String erstZulassung) throws ParseException {
		this.erstZulassung = stringToDate(erstZulassung);
	}

	public int getKmAktuell() {
		return kmAktuell;
	}

	public void setKmAktuell(int kmAktuell) {
		this.kmAktuell = kmAktuell;
	}
}
