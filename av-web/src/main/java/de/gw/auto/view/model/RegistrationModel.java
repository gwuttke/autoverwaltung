package de.gw.auto.view.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class RegistrationModel {
	@Size(min = 2, max = 50, message = "Der Name muss angegeben werden und darf nicht länger als 50 Zeichen sein")
	private String name;

	@Size(min = 2, max = 50, message = "Der Vorname muss angegeben werden und darf nicht länger als 50 Zeichen sein")
	private String vorname;

	private String benutzername;

	@Size(min = 5, max = 50, message = "Das Passwort muss angegeben werden und muss minderstens 5 Zeichen haben")
	private String passwort;
	
	@Size(min = 5, max = 50, message = "Die Passwörter stimmen nicht überein")
	private String wiederholungPasswort;

	@Email
	private String eMail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getWiederholungPasswort() {
		return wiederholungPasswort;
	}

	public void setWiederholungPasswort(String wiederholungPasswort) {
		this.wiederholungPasswort = wiederholungPasswort;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
}
