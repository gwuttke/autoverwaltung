package de.gw.auto.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import de.gw.auto.Constans;

@Entity
@SequenceGenerator(name = "version_gen", sequenceName = "version_id_seq")
public class Version implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "version_gen")
	private int id;
	private int nummer;
	
	private String plattform;
	
	

	public Version(int nummer, String plattform) {
		this();
		this.nummer = nummer;
		this.plattform = plattform;
	}

	public Version() {
		super();
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public String getPlattform() {
		return plattform;
	}

	public void setPlattform(String plattform) {
		this.plattform = plattform;
	}
}
