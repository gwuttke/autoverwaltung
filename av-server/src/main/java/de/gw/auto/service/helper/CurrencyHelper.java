package de.gw.auto.service.helper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyHelper {
	public static String getCurrencyByGermany(BigDecimal input,
			int nachkommastellen) {
		return getCurrencyByLocation(input, nachkommastellen, Locale.GERMANY);
	}

	protected static String getCurrencyByLocation(BigDecimal input,
			int nachkommastellen, Locale locale) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(nachkommastellen);
		nf.setMinimumFractionDigits(nachkommastellen);
		return nf.format(input.doubleValue());
	}
}
