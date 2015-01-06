package de.gw.auto.domain;

import java.math.BigDecimal;

public class Tankfuellung extends Tanken {
	private int gefahreneKm;
	private BigDecimal verbrauch100Km;
	
	public Tankfuellung(Tanken tanken) {
	super(tanken);
		
	}

	public int getGefahreneKm() {
		return gefahreneKm;
	}

	public void setGefahreneKm(int gefahreneKm) {
		this.gefahreneKm = gefahreneKm;
	}

	public BigDecimal getVerbrauch100Km() {
		return verbrauch100Km;
	}

	public void setVerbrauch100Km(BigDecimal verbrauch100Km) {
		this.verbrauch100Km = verbrauch100Km;
	}

}
