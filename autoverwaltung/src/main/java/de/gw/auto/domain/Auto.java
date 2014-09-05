package de.gw.auto.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	// Wissen Notieren n:m Beziehung
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "auto_benzinart", joinColumns = { @JoinColumn(name = "idAuto") }, inverseJoinColumns = { @JoinColumn(name = "idBenzinart") })
	private Set<Benzinart> benzinarten = new HashSet<Benzinart>();
	private int kmAktuell;
	@OneToMany(mappedBy="auto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<SonstigeAusgaben> sonstigeAusgaben;
	@OneToMany(mappedBy="auto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Tanken> tankfuellungen = new HashSet<Tanken>();
	
	

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
		this();
		this.kfz = setting.getAktuellAuto().getKfz();
		this.id = setting.getAktuellAuto().getId();
		this.kmKauf = setting.getAktuellAuto().getKmKauf();
		this.kauf = setting.getAktuellAuto().getKauf();
		this.erstZulassung = setting.getAktuellAuto().getErstZulassung();
		this.benzinarten = setting.getAktuellAuto().getBenzinarten();
		this.kmAktuell = setting.getAktuellAuto().getKmAktuell();
	}

	public Auto() {
		super();
	}
		
	public Set<Tanken> getTankfuellungen() {
		return tankfuellungen;
	}

	public void setTankfuellungen(Set<Tanken> tankfuellungen) {
		this.tankfuellungen = tankfuellungen;
	}

	public Set<SonstigeAusgaben> getSonstigeAusgaben() {
		return sonstigeAusgaben;
	}

	public void setSonstigeAusgaben(Set<SonstigeAusgaben> sonstigeAusgaben) {
		this.sonstigeAusgaben = sonstigeAusgaben;
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

	public Set<Benzinart> getBenzinarten() {
		return benzinarten;
	}

	public void setBenzinarten(Set<Benzinart> benzinarten) {
		this.benzinarten = benzinarten;
	}

	public int getKmAktuell() {
		return kmAktuell;
	}

	public void setKmAktuell(int kmAktuell) {
		this.kmAktuell = kmAktuell;
	}

	public void addBenzinart(Benzinart benzinart) {
		benzinarten.add(benzinart);
	}
	
	public void addTanken(Tanken tanken){
		tankfuellungen.add(tanken);
	}
	
	public void updateTanken(Tanken tanken){
		searchAndUpdate(tanken);
	}
	
	private void searchAndUpdate(Tanken tanken){
		for (Tanken t : tankfuellungen){
			if (t.getId() == tanken.getId()){
				t = tanken;
				break;
			}
		}
	}

	@Override
	public String toString() {
		/* return MessageFormat.format(
				"{0}: {1} : {2} : {3} : {4} : {5} : {6} : {7}", new Object[] {
						getClass().getSimpleName(), id, kfz, kmKauf, kauf,
						erstZulassung, benzinarten, kmAktuell });
		*/
		return kfz;
	}
}
