package de.gw.auto.gui.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.SwingConstants;

public class NumberRenderer extends FormatRenderer {
		
	/*
	 * Use the specified number formatter and right align the text
	 */
	public NumberRenderer(NumberFormat formatter) {
		super(formatter);
		setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public static NumberRenderer getKilometerRenderer(){
		DecimalFormat integerFormat = (DecimalFormat) NumberFormat.getIntegerInstance();
		integerFormat.applyPattern("000000 Km");
		return new NumberRenderer(integerFormat);
	}	
	
	/*
	 * Use the default currency formatter for the default locale
	 */
	public static NumberRenderer getCurrencyRenderer() {
		return new NumberRenderer(NumberFormat.getCurrencyInstance());
	}

	/*
	 * Use the default integer formatter for the default locale
	 */
	public static NumberRenderer getIntegerRenderer() {
		return new NumberRenderer(NumberFormat.getIntegerInstance());
	}

	/*
	 * Use the default percent formatter for the default locale
	 */
	public static NumberRenderer getPercentRenderer() {
		return new NumberRenderer(NumberFormat.getPercentInstance());
	}
}
