package de.gw.auto.domain;

import java.io.Serializable;
import java.sql.Date;
import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "auto_seq", sequenceName = "auto_id_seq")
public class Auto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_seq")
	private int id;
	private String kfz;
	private int kmKauf;
	private Date kauf;
	private Date erstZulassung;
	@OneToMany(mappedBy = "id")
	private Set<Benzinart> benzinarten = new HashSet<Benzinart>();
	private int kmAktuell;
	private int kmGefahren;
	private String autoAlter;
	private String kaufAlter;
	private static Datum datum = new Datum();

	public Auto(String kfz, int kmKauf, Date kauf, Date erstZulassung,
			Set<Benzinart> benzinarten, int kmAktuell) {
		super();
		this.kfz = kfz;
		this.kmKauf = kmKauf;
		this.kauf = kauf;
		this.erstZulassung = erstZulassung;
		this.benzinarten = benzinarten;
		this.kmAktuell = kmAktuell;
		berechneGefahreneKm();
		berechneAutoAlter();
	}

	public Auto(Settings setting) {
		super();
		this.kfz = setting.getAuto().getKfz();
		this.id = setting.getAuto().getId();
		this.kmKauf = setting.getAuto().getKmKauf();
		this.kauf = setting.getAuto().getKauf();
		this.erstZulassung = setting.getAuto().getErstZulassung();
		this.benzinarten = setting.getAuto().getBenzinArten();
		this.kmAktuell = setting.getAuto().getKmAktuell();
	}

	public Auto() {

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

	public Set<Benzinart> getBenzinArten() {
		return benzinarten;
	}

	public void setBenzinArten(Set<Benzinart> benzinarten) {
		this.benzinarten = benzinarten;
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

	public void setAlter() {
		berechneAutoAlter();
	}

	private int berechneGefahreneKm() {

		if (getKmAktuell() > getKmKauf()) {
			return getKmAktuell() - getKmKauf();
		} else {
			return -1;
		}

	}

	@Override
	public String toString() {
		return MessageFormat
				.format("Auto: {0} : {1} : {2} : {3} : {4} : {5} : {6} : {7} : {8} : {9}",
						new Object[] { id, kfz, kmKauf, kauf, erstZulassung,
								benzinarten, kmAktuell, kmGefahren, autoAlter,
								kaufAlter });

	}

	private void berechneAutoAlter() {

		if ((this.getKauf().getTime() > 0) && (this.getErstZulassung() != null)) {

			GregorianCalendar seitKauf = new GregorianCalendar();
			GregorianCalendar seitErstZulassung = new GregorianCalendar();

			seitErstZulassung.setTimeInMillis(this.erstZulassung.getTime());
			seitKauf.setTimeInMillis(this.kauf.getTime());

			setKaufAlter(datum.getDifference(seitKauf));
			setAutoAlter(datum.getDifference(seitErstZulassung));
		} else {
			setAutoAlter("Keine Angabe Moeglich");
		}
	}
}
