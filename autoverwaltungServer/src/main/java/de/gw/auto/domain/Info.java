package de.gw.auto.domain;

import java.math.BigDecimal;

public class Info {

	private String name;
	private BigDecimal gesammt = BigDecimal.ZERO;
	private BigDecimal diesesJahr = BigDecimal.ZERO;
	private BigDecimal vorjahr = BigDecimal.ZERO;
	
	public Info(String name) {
		this.name = name;
	}

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
	
	public Info add(Info info){
		Info result = new Info("result");
		result.gesammt = this.gesammt.add(info.gesammt); 
		result.diesesJahr = this.diesesJahr.add(info.diesesJahr);
		result.vorjahr = this.vorjahr.add(info.vorjahr);
		return result;
	}

}