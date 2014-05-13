package de.gw.auto.domain;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "land_seq", sequenceName = "land_id_seq")
public class Land implements Serializable {
	@Id
	@SequenceGenerator(name = "land_seq", sequenceName = "land_id_seq")
	private int id;
	private String name;
	@OneToMany(mappedBy="land")
	private Set<Ort> orte;
	

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
	
		return MessageFormat.format("{0}: {1} : {2}", new Object[] {id, name});
	}
	
}
