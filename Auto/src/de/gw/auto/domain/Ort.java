package de.gw.auto.domain;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "ort_gen", sequenceName = "ort_id_seq")
public class Ort implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ort_gen")
	private int id;
	private String ort;
	@ManyToOne
	@JoinColumn
	private Land land;

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return MessageFormat.format("{0}: {1} : {2}", new Object[] { getClass().getSimpleName(), id, ort });
	}
}
