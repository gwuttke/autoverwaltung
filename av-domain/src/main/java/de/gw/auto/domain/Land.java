package de.gw.auto.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "land_seq", sequenceName = "land_id_seq")
public class Land implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "land_seq")
	private int id;
	private String name;	
	@OneToMany( cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Laenderorte", joinColumns = { @JoinColumn(name = "idland") }, inverseJoinColumns = { @JoinColumn(name = "idort") })
	private Set<Ort> orte = new HashSet<Ort>();
	

	public Set<Ort> getOrte() {
		return orte;
	}

	public Land() {

	}
	
	public void addOrt(Ort o){
		this.orte.add(o);
	}
	
	public Land(final String name){
		this();
		this.name = name;
	}

	public Land(final String name, final Set<Ort> orte) {
		this(name);
		this.orte = orte;
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
	
	public Ort getOrt(int ortId){
		for (Ort o : orte){
			if(o.getId() == ortId){
				return o;
			}
		}
		return null;
	}
	
}
