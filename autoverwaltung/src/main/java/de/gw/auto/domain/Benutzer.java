package de.gw.auto.domain;


public class Benutzer {

	private String name;
	private String passwort;

	public Benutzer(String name, String passwort) {
		super();
		this.name = name;
		this.passwort = passwort;
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
