package de.gw.auto.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Kraftstoffsorte")
@SequenceGenerator(name = "kraftstoffsorte_gen", sequenceName = "kraftstoffsorte_id_seq")
public class Kraftstoffsorte implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "kraftstoffsorte_gen")
	private Integer id;
	private String name;
	/*@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "Auto_Benzinart", joinColumns = { @JoinColumn(name = "auto") }, inverseJoinColumns = { @JoinColumn(name = "benzinart") })
	*/
	//private Set<Auto> autos;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Kraftstoffsorte() {
	}

	public Kraftstoffsorte(String name) {
		super();
		if (name.equals(null)) {
			throw new IllegalArgumentException("Der Name ist nicht gueltig");
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public Set<Auto> getAutos() {
		return autos;
	}

	public void addAuto(Auto auto) {
		autos.add(auto);
	}
*/
	@Override
	public String toString() {
		return name;
	}

}
