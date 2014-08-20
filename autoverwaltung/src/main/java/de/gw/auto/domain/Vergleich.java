package de.gw.auto.domain;

import java.math.BigDecimal;

public class Vergleich {
	private BigDecimal min = BigDecimal.ZERO;
	private BigDecimal max = BigDecimal.ZERO;
	private BigDecimal zahl;

	
	
	
	public Vergleich(BigDecimal zahl) {
		this();
		this.zahl = zahl;
		this.max = this.zahl;
		this.min = this.max;
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
		min=  min.min(this.max);
		return min;
	}

	public BigDecimal max() {
		max = max.max(min);
		return max;
	}

}
