package de.gw.auto.domain;

import java.math.BigDecimal;

public class Tankfuellung extends Tanken {
	private Tankfuellung vorTankfuellung;

	
	public Tankfuellung(Tankfuellung tankfuellung){
		super(tankfuellung);
		this.vorTankfuellung = tankfuellung.vorTankfuellung;
	}
	
	public Tankfuellung(Tanken tanken, Tankfuellung vorTankfuellung) {
		super(tanken);
		this.vorTankfuellung = vorTankfuellung;
	}

	public int getGefahreneKm() {
		return this.getKmStand() - vorTankfuellung.getKmStand();
	}

	public Double getVerbrauch100Km() {
		return (this.getLiter().doubleValue() / this.getGefahreneKm()) * 100;
	}
}
