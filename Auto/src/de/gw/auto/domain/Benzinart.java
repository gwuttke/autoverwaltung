package de.gw.auto.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "benzinart_gen", sequenceName = "benzinart_id_seq")
public class Benzinart implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "benzinart_gen")
	private Integer id;
	private String name;
	@ManyToOne
	@JoinColumn
	private Auto auto;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Benzinart() {
	}

	public Benzinart(String name) {
		super();
		if (name.equals(null)) {
			throw new IllegalArgumentException("Der Name ist nicht gueltig");
		}
		this.name = name;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
