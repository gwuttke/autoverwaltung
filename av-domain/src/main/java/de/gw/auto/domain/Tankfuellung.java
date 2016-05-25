package de.gw.auto.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Comparator;

import org.hibernate.cfg.SetSimpleValueTypeSecondPass;

public class Tankfuellung extends Tanken implements Comparator<Tanken> {
	private Tankfuellung vorTankfuellung;

	public Tankfuellung(Tankfuellung tankfuellung) {
		super(tankfuellung);
		this.vorTankfuellung = tankfuellung.vorTankfuellung;
	}

	public Tankfuellung(Tanken tanken, Tankfuellung vorTankfuellung) {
		super(tanken);
		this.vorTankfuellung = vorTankfuellung;
	}

	public int getGefahreneKm() {
		if (vorTankfuellung == null) {
			return this.getKmStand() - this.getAuto().getKmStart();
		}
		return this.getKmStand() - vorTankfuellung.getKmStand();
	}

	public BigDecimal getVerbrauch100Km() {
		/*BigDecimal verbrauch;
		if (this.vorTankfuellung.getTank().equals(Tank.VOLL)) {
			verbrauch = new BigDecimal((this.getLiter().doubleValue() / this.getGefahreneKm()) * 100);
		} else {
			verbrauch = getVerbrauch100Km(this.getLiter().doubleValue(), this.getGefahreneKm(), this.vorTankfuellung,
					false);
		}

		return verbrauch;
		*/
		return new BigDecimal((this.getLiter().doubleValue() / this.getGefahreneKm()) * 100);
	}

	private BigDecimal getVerbrauch100Km(double liter, int gefahreneKm, Tankfuellung tf, boolean isLastFull) {
		if (isLastFull == false && tf != null) {
			liter += tf.getLiter().doubleValue();
			gefahreneKm += tf.getGefahreneKm();
			return getVerbrauch100Km(liter, gefahreneKm, tf.vorTankfuellung, tf.getTank().equals(Tank.VOLL));
		}
		return new BigDecimal((liter / gefahreneKm) * 100);

	}

	@Override
	public int compare(Tanken o1, Tanken o2) {
		return o2.getKmStand() - o1.getKmStand();
	}
}
