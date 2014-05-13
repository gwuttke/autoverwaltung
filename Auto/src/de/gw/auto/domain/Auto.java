package de.gw.auto.domain;

import java.io.Serializable;
import java.sql.Date;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.engine.internal.Cascade;

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
	@OneToMany(mappedBy = "auto", cascade=CascadeType.ALL)
	private Set<Benzinart> benzinarten = new HashSet<Benzinart>();
	private int kmAktuell; 


	public Auto(String kfz, int kmKauf, Date kauf, Date erstZulassung,
			Set<Benzinart> benzinarten, int kmAktuell) {
		super();
		this.kfz = kfz;
		this.kmKauf = kmKauf;
		this.kauf = kauf;
		this.erstZulassung = erstZulassung;
		this.benzinarten = benzinarten;
		this.kmAktuell = kmAktuell;
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

	
	@Override
	public String toString() {
		return MessageFormat
				.format("{0}: {1} : {2} : {3} : {4} : {5} : {6} : {7}",
						new Object[] {getClass().getSimpleName(), id, kfz, kmKauf, kauf, erstZulassung,
								benzinarten, kmAktuell });

	}
}
