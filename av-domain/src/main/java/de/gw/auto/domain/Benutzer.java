package de.gw.auto.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Email;

@Entity
// @SequenceGenerator(name = "benutzer_seq", sequenceName = "benutzer_id_seq")
@Table(name = "Benutzer", uniqueConstraints = {
		@UniqueConstraint(name = "ui_name_passwort", columnNames = {
				"benutzername", "passwort" }),
		@UniqueConstraint(name = "ui_name_email_vorname", columnNames = {
				"name", "vorname", "eMail" }) })
public class Benutzer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String vorname;
	private String benutzername;
	private String passwort;
	@Email
	@Column(name = "email", unique = true)
	private String eMail;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "auto_benutzer", joinColumns = { @JoinColumn(name = "idbenutzer") }, inverseJoinColumns = { @JoinColumn(name = "idauto") })
	private List<Auto> autos = new ArrayList<Auto>();

	protected Benutzer() {
		// for Hibernate
	}

	public Benutzer(String benutzername, String passwort) {
		this();
		this.benutzername = benutzername;
		this.passwort = passwort;
	}

	public Benutzer(String name, String vorname, String benutzername,
			String passwort, String eMail) {
		this(benutzername, passwort);
		this.name = name;
		this.vorname = vorname;
		this.eMail = eMail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Auto> getAutos() {
		return autos;
	}

	public void setAutos(List<Auto> autos) {
		this.autos = autos;
	}

	public void addAuto(Auto auto) {
		autos.add(auto);
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

	public String getVorname() {
		return vorname;
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	@Override
	public String toString() {
		return String.format("ID: %d , Name: %s %s", id, vorname, name);
	}
}
