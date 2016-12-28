package de.gw.auto.view.model;

public class AuswertungProJahr {

	private int jahr;

	private Double liter;
	
	private Double kosten;
	
	private Double preisProLiterAvg;
	
	private Double preisProLiterMin;
	
	private Double preisProLiterMax;

	private int km;

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}
	
	public Double getLiter() {
		return liter;
	}

	public void setLiter(Double liter) {
		this.liter = liter;
	}

	public Double getKosten() {
		return kosten;
	}

	public void setKosten(Double kosten) {
		this.kosten = kosten;
	}

	public Double getPreisProLiterAvg() {
		return preisProLiterAvg;
	}

	public void setPreisProLiterAvg(Double preisProLiterAvg) {
		this.preisProLiterAvg = preisProLiterAvg;
	}

	public Double getPreisProLiterMin() {
		return preisProLiterMin;
	}

	public void setPreisProLiterMin(Double preisProLiterMin) {
		this.preisProLiterMin = preisProLiterMin;
	}

	public Double getPreisProLiterMax() {
		return preisProLiterMax;
	}

	public void setPreisProLiterMax(Double preisProLiterMax) {
		this.preisProLiterMax = preisProLiterMax;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}
	
	public Double giveVerbrauch() {
		if(liter == null || liter == 0d){
			return 0d;
		}
		return (liter / km) *100;
	}
}
