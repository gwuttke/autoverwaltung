package de.gw.auto.gui.model;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class NumberRenderer extends FormatRenderer {

	/*
	 * Use the specified number formatter and right align the text
	 */
	public NumberRenderer(NumberFormat formatter) {
		super(formatter);
		setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public static NumberRenderer getKilometerRenderer() {
		DecimalFormat integerFormat = (DecimalFormat) NumberFormat
				.getIntegerInstance();
		integerFormat.applyPattern("######' Km'");
		return new NumberRenderer(integerFormat);
	}

	/*
	 * Use the default currency formatter for the default locale
	 */
	public static NumberRenderer getCurrencyRenderer(int digits) {
		DecimalFormat nformater = (DecimalFormat) NumberFormat
				.getCurrencyInstance();
		nformater.setMinimumFractionDigits(digits);
		nformater.setMaximumFractionDigits(digits);

		return new NumberRenderer(nformater);
	}
	
	public static String getCurrencyRenderer(int digits, Object aNumber){
		DecimalFormat nformater = (DecimalFormat) NumberFormat
				.getCurrencyInstance();
		nformater.setMinimumFractionDigits(digits);
		nformater.setMaximumFractionDigits(digits);
		return nformater.format(aNumber);

	}

	/*
	 * Use the default integer formatter for the default locale
	 */
	public static NumberRenderer getIntegerRenderer() {
		return new NumberRenderer(NumberFormat.getIntegerInstance());
	}

	public static NumberRenderer getLiterRenderer() {
		DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getInstance();
		numberFormat.applyPattern("###0.00' L'");
		return new NumberRenderer(numberFormat);
	}

	/*
	 * Use the default percent formatter for the default locale
	 */
	public static NumberRenderer getPercentRenderer() {
		return new NumberRenderer(NumberFormat.getPercentInstance());
	}
}
