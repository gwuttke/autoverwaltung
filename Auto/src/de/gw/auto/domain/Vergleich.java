package de.gw.auto.domain;

import java.math.BigDecimal;

public class Vergleich {
	private BigDecimal min;
	private BigDecimal max;
	private BigDecimal zahl;

	
	
	
	public Vergleich(BigDecimal zahl) {
		super();
		this.zahl = zahl;
	}


	public BigDecimal getZahl() {
		return zahl;
	}


	public void setZahl(BigDecimal zahl) {
		this.zahl = zahl;
	}


	public BigDecimal getMax() {
		return max;
	}


	public Vergleich() {
		super();
		
	}
	
	
	public BigDecimal min() {
		min=  min.min(max);
		return min;
	}

	public BigDecimal max() {
		max = max.max(min);
		return max;
	}

}
