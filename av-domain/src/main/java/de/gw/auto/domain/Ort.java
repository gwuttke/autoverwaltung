package de.gw.auto.domain;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "ort_gen", sequenceName = "ort_id_seq")
public class Ort implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ort_gen")
	private int id;

	private String ort;

	public Ort(String ort) {
		super();
		this.ort = ort;
	}

	public Ort() {
		super();
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
		return this.ort;
	}
}
