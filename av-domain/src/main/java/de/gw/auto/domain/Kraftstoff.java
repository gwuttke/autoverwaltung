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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.engine.internal.Cascade;

@Entity
@Table(name = "Kraftstoff")
@SequenceGenerator(name = "kraftstoff_gen", sequenceName = "kraftstoff_id_seq")
public class Kraftstoff implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "kraftstoff_gen")
	private Integer id;

	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="kraftstoff_kraftstoffsorte")
	private Set<Kraftstoffsorte> kraftstoffsorten = new HashSet<Kraftstoffsorte>();

	
	public Set<Kraftstoffsorte> getKraftstoffsorten() {
		return kraftstoffsorten;
	}

	public void setKraftstoffsorten(Set<Kraftstoffsorte> kraftstoffsorten) {
		this.kraftstoffsorten = kraftstoffsorten;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
