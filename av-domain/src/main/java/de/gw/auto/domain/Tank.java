package de.gw.auto.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "tank_gen", sequenceName = "tank_id_seq")
public class Tank implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "tank_gen")
	private int id;
	private String beschreibung;

	public Tank() {

	}

	public Tank(String beschreibung) {
		super();
		if (beschreibung == null || beschreibung == "") {
			this.beschreibung = beschreibung;
		}
	}

	public Tank(int id, String beschreibung) {
		super();
		this.id = id;
		this.beschreibung = beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getBeschreibung() {
		return this.beschreibung;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
