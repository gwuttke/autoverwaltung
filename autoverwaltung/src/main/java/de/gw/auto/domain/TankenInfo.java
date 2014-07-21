package de.gw.auto.domain;

import java.math.BigDecimal;

public class TankenInfo {

	private String name;
	private BigDecimal gesammt;
	private BigDecimal diesesJahr;
	private BigDecimal vorjahr;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getGesammt() {
		return gesammt;
	}

	public void setGesammt(BigDecimal gesammt) {
		this.gesammt = gesammt;
	}

	public BigDecimal getDiesesJahr() {
		return diesesJahr;
	}

	public void setDiesesJahr(BigDecimal diesesJahr) {
		this.diesesJahr = diesesJahr;
	}

	public BigDecimal getVorjahr() {
		return vorjahr;
	}

	public void setVorjahr(BigDecimal vorjahr) {
		this.vorjahr = vorjahr;
	}

	public Vergleich Vergleich(BigDecimal zahl) {
		Vergleich v = new Vergleich(zahl);
		return v;
	}

}