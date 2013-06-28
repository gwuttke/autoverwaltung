package domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Berechnung {
	private double b;

	public String getRound(double value, int nachkommata) {
		return round(value, nachkommata);

	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	private String round(double value, int nachkommaStelle) {
		DecimalFormat df = new DecimalFormat();
		switch (nachkommaStelle) {

		case 2:
			df = new DecimalFormat(",##0.00");
			return df.format(value);
		case 3:
			df = new DecimalFormat(",###0.000");
			return df.format(value);
		default:
			return "keine gültige eingabe";
		}

	}
}
