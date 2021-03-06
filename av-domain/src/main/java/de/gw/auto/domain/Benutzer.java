package de.gw.auto.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@SequenceGenerator(name = "benutzer_seq", sequenceName = "benutzer_id_seq")
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(name = "ui_name_passwort", columnNames = {
				"username", "password" }),
		@UniqueConstraint(name = "ui_name_email_vorname", columnNames = {
				"name", "vorname", "eMail" }) })
public class Benutzer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private String vorname;
	
	@Column(name="username")
	private String benutzername;
	
	@Column(name="password")
	private String passwort;

	@Column(name = "email", unique = true)
	private String eMail;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "auto_benutzer", joinColumns = { @JoinColumn(name = "users_id") }, inverseJoinColumns = { @JoinColumn(name = "Auto_id") })
	private List<Auto> autos = new ArrayList<Auto>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = true, name = "currentautoid")
	private Auto currentAuto;

	@OneToMany
	@JoinTable(name = "benutzer_role")
	private List<Role> roles;

	protected Benutzer() {
		super();
	}

	public List<Role> getRoles() {
		return roles;
	}

	public Benutzer(String name, String vorname, String benutzername,
			String passwort, String eMail, Role role) {
		this(benutzername, passwort);
		this.name = name;
		this.vorname = vorname;
		this.eMail = eMail;
		this.roles = new ArrayList<Role>();
		roles.add(role);
	}

	public Benutzer(int id, String name, String vorname, String benutzername,
			String passwort, String eMail, List<Auto> autos, Auto currentAuto,
			List<Role> roles) {
		this(name, vorname, benutzername, passwort, eMail, null);
		this.id = id;
		this.autos = autos;
		this.currentAuto = currentAuto;
		this.roles = roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Benutzer(String benutzername, String passwort) {
		this();
		this.benutzername = benutzername;
		this.passwort = passwort;
	}

	public Auto getCurrentAuto() {
		return currentAuto;
	}

	public void setCurrentAuto(Auto currentAuto) {
		this.currentAuto = currentAuto;
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

	public void setVorname(String vorname) {
		this.vorname = vorname;
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
