package de.gw.auto.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import de.gw.auto.dao.Berechnung;

@Entity
@SequenceGenerator(name = "tanken_gen", sequenceName = "tanken_id_seq")
public class Tanken implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "tanken_gen")
	private int id;
	private int kmStand;
	@ManyToOne
	@JoinColumn
	private Land land;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Ort ort;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Tank tank;
	private BigDecimal kosten;
	@ManyToOne
	@JoinColumn
	private Auto auto;
	private Date datum;
	private BigDecimal liter;
	private BigDecimal preisProLiter;
	@OneToOne
	@JoinColumn
	private Benzinart benzinArt;

	
	public Tanken() {
		super();
	}

	public Tanken(int kmStand, Land land, Ort ort, Tank tank,
			BigDecimal kosten, Auto auto, Date datum, BigDecimal liter,
			BigDecimal preisProLiter, Benzinart benzinArt) {
		super();
		this.kmStand = kmStand;
		this.land = land;
		this.ort = ort;
		this.tank = tank;
		this.kosten = kosten;
		this.auto = auto;
		this.datum = datum;
		this.liter = liter;
		this.preisProLiter = preisProLiter;
		this.benzinArt = benzinArt;
	}

	public BigDecimal getPreisProLiter() {

		return preisProLiter;
	}

	public void setPreisProLiter(BigDecimal preisProLiter) {
		this.preisProLiter = preisProLiter;
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	public int getKmStand() {
		return kmStand;
	}

	public BigDecimal getKosten() {
		return kosten;

	}

	public void setKosten(BigDecimal kosten) {
		this.kosten = kosten;
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

	public BigDecimal getLiter() {
		return liter;
	}

	public BigDecimal getLiterString() {
		return liter;
	}

	public void setLiter(BigDecimal liter) {
		this.liter = liter;
	}

	public Benzinart getBenzinArt() {
		return benzinArt;
	}

	public void setBenzinArt(Benzinart benzinArt) {
		this.benzinArt = benzinArt;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public Ort getOrt() {
		return ort;
	}

	public void setOrt(Ort ort) {
		this.ort = ort;
	}

	public BigDecimal getKostenString() {
		return kosten;

	}

	@Override
	public String toString() {
		return MessageFormat
				.format("{0}: {1} : {2} : {3} : {4} : {5} : {6} : {7} : {8} : {9} : {10} : {11} : {12}",
						new Object[] { getClass().getSimpleName(), id, 
						kmStand,land.getName(), ort.getOrt(),tank.getBeschreibung(), kosten.toString(),
						auto,
						datum.toString(), liter.toString(), preisProLiter.toString(),
						benzinArt.toString() });
	}

}
