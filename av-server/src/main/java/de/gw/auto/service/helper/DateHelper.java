package de.gw.auto.service.helper;

import org.joda.time.DateTime;
import org.springframework.format.datetime.joda.JodaDateTimeFormatAnnotationFormatterFactory;

public class DateHelper {
	
	/**
	 * 
	 * @param year
	 * @return An Array of Joda DateTime first element is 01.01.<year> second one is 31.12.<year>
	 */
	public static DateTime[] getHoleYear(int year){
		DateTime[] holeYear = new DateTime[2];
		holeYear[0]=new DateTime(year, 1, 1, 0, 0);
		holeYear[1]=new DateTime(year, 12, 31, 0, 0);
		
		return holeYear;
	}
	
}
