package de.gw.auto.domain;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "land_seq", sequenceName = "land_id_seq")
public class Land implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "land_seq")
	private int id;
	private String name;	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Laenderorte", joinColumns = { @JoinColumn(name = "idLand") }, inverseJoinColumns = { @JoinColumn(name = "idOrt") })
	private Set<Ort> orte;
	

	public Set<Ort> getOrte() {
		return orte;
	}

	public Land() {

	}

	public Land(final String name, final Set<Ort> orte) {
		this.orte = orte;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
	
		return name;
	}
	
}
