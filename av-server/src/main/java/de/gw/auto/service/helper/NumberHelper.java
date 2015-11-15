package de.gw.auto.service.helper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberHelper {

	public static String getGermanBigDecimalValue(BigDecimal input, int nachkommastellen, String prefix){
		return getLocalizedBigDecimalValue(input, nachkommastellen,  prefix,Locale.GERMANY);
	}
	
	protected static String getLocalizedBigDecimalValue(BigDecimal input, int nachkommastellen, String prefix, Locale locale) {
	    final NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
	    numberFormat.setGroupingUsed(true);
	    numberFormat.setMaximumFractionDigits(nachkommastellen);
	    numberFormat.setMinimumFractionDigits(nachkommastellen);
	    return numberFormat.format(input)+" " +prefix;
	}

}
