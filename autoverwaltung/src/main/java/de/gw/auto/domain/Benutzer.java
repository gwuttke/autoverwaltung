package de.gw.auto.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@SequenceGenerator(name = "benutzer_seq", sequenceName = "benutzer_id_seq")
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"name", "passwort"}))
public class Benutzer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "benutzer_seq")
	private int id;
	private String name;
	private String passwort;
	@ManyToMany
	private List<Auto> autos;
	
	public Benutzer() {
		// for Hibernate
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Benutzer(String name, String passwort) {
		super();
		this.name = name;
		this.passwort = passwort;
	}

	
	public List<Auto> getAutos() {
		return autos;
	}


	public void setAutos(List<Auto> autos) {
		this.autos = autos;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

}
