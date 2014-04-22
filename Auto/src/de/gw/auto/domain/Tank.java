package de.gw.auto.domain;

public class Tank {

	private int id;
	private String beschreibung;

	public Tank() {

	}

	public Tank(String beschreibung, int id) {
		super();
		if (beschreibung == null || beschreibung == "") {

		}

		this.beschreibung = beschreibung;
		this.id = id;
	}



	public Tank(int id, String beschreibung) {
		super();
		this.id = id;
		this.beschreibung = beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getBeschreibung() {
		return this.beschreibung;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return beschreibung;
	}

public void setId(int id) {
	this.id = id;
}

}
