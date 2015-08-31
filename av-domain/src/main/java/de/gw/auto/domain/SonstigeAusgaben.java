package de.gw.auto.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="sonstigeausgaben")
@SequenceGenerator(name = "sonstigeAusgaben_gen", sequenceName = "sonstigeAusgaben_id_seq")
public class SonstigeAusgaben implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sonstigeAusgaben_gen")
	private int id;
	private Date datum;
	@Column(name="kmstand")
	private int kmStand;
	private String kommentar;
	private BigDecimal kosten;
	@ManyToOne
	@JoinColumn(name="auto_id")
	private Auto auto;

	
	public int getId() {
		return id;
	}
	
	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

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
}