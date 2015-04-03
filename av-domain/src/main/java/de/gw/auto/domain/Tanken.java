package de.gw.auto.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "tanken_gen", sequenceName = "tanken_id_seq")
public class Tanken implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "tanken_gen")
	private int id;
	@Column(name = "kmstand")
	private int kmStand;
	@ManyToOne
	@JoinColumn
	private Land land;
	@ManyToOne
	@JoinColumn
	private Ort ort;
	@ManyToOne
	@JoinColumn
	private Tank tank;
	private BigDecimal kosten;
	private Date datum;
	private BigDecimal liter;
	@Column(name = "preisproliter", precision = 10, scale = 3)
	private BigDecimal preisProLiter;
	@OneToOne
	@JoinColumn(name = "benzinart_id")
	private Benzinart benzinArt;
	@ManyToOne
	@JoinColumn(name = "auto_id")
	private Auto auto;

	public int getId() {
		return id;
	}

	public Tanken() {
		super();
	}

	public Tanken(int kmStand, Land land, Ort ort, Tank tank,
			BigDecimal kosten, Auto auto, Date datum, BigDecimal liter,
			BigDecimal preisProLiter, Benzinart benzinArt) {
		this();
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

	protected Tanken(int id, int kmStand, Land land, Ort ort, Tank tank,
			BigDecimal kosten, Auto auto, Date datum, BigDecimal liter,
			BigDecimal preisProLiter, Benzinart benzinArt) {
		this(kmStand, land, ort, tank, kosten, auto, datum, liter,
				preisProLiter, benzinArt);
		this.id = id;
	}

	protected Tanken(Tanken t) {
		this(t.id, t.kmStand, t.land, t.ort, t.tank, t.kosten, t.auto, t.datum,
				t.liter, t.preisProLiter, t.benzinArt);

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

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	@Override
	public String toString() {
		return MessageFormat
				.format("{0}: {1} : {2} : {3} : {4} : {5} : {6} : {7} : {8} : {9} : {10} : {11}",
						new Object[] { getClass().getSimpleName(), id, kmStand,
								land.getName(), ort.getOrt(),
								tank.getBeschreibung(), kosten.toString(),
								datum.toString(), liter.toString(),
								preisProLiter.toString(), benzinArt.toString() });
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auto == null) ? 0 : auto.hashCode());
		result = prime * result
				+ ((benzinArt == null) ? 0 : benzinArt.hashCode());
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + id;
		result = prime * result + kmStand;
		result = prime * result + ((kosten == null) ? 0 : kosten.hashCode());
		result = prime * result + ((land == null) ? 0 : land.hashCode());
		result = prime * result + ((liter == null) ? 0 : liter.hashCode());
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
		result = prime * result
				+ ((preisProLiter == null) ? 0 : preisProLiter.hashCode());
		result = prime * result + ((tank == null) ? 0 : tank.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tanken other = (Tanken) obj;
		if (auto == null) {
			if (other.auto != null)
				return false;
		} else if (!auto.equals(other.auto))
			return false;
		if (benzinArt == null) {
			if (other.benzinArt != null)
				return false;
		} else if (!benzinArt.equals(other.benzinArt))
			return false;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (id != other.id)
			return false;
		if (kmStand != other.kmStand)
			return false;
		if (kosten == null) {
			if (other.kosten != null)
				return false;
		} else if (!kosten.equals(other.kosten))
			return false;
		if (land == null) {
			if (other.land != null)
				return false;
		} else if (!land.equals(other.land))
			return false;
		if (liter == null) {
			if (other.liter != null)
				return false;
		} else if (!liter.equals(other.liter))
			return false;
		if (ort == null) {
			if (other.ort != null)
				return false;
		} else if (!ort.equals(other.ort))
			return false;
		if (preisProLiter == null) {
			if (other.preisProLiter != null)
				return false;
		} else if (!preisProLiter.equals(other.preisProLiter))
			return false;
		if (tank == null) {
			if (other.tank != null)
				return false;
		} else if (!tank.equals(other.tank))
			return false;
		return true;
	}

	public boolean like(Tanken tanken) {
		if (this.auto.getId() == tanken.auto.getId()) {
			if (this.datum.getTime() == tanken.datum.getTime()) {
				if (this.getKmStand() == tanken.getKmStand()) {
					return true;
				}
			}
		}
		return false;
	}

	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
	}

}
