package de.gw.auto.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "role_gen", sequenceName = "role_id_seq")
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "role_gen")
	private double id;
	
	@OneToMany
	private List<Autoritaet> autoritaet;

	private String name;

	public Role() {
		super();
	}

	public void addAutoritaet(final Autoritaet autoritaet){
		this.autoritaet.add(autoritaet);
	}
	
	public List<Autoritaet> getAutoritaet() {
		return autoritaet;
	}


	public void setAutoritaet(List<Autoritaet> autoritaet) {
		this.autoritaet = autoritaet;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getId() {
		return id;
	}

}
