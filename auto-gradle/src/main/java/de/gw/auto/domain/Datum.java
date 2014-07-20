package de.gw.auto.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Months;

public class Datum {
	// private GregorianCalendar past = new GregorianCalendar(this.getPYear(),
	// this.getPMonth(), this.getPDay());
	private Calendar now = new GregorianCalendar();
	private Calendar date;

	public Datum() {
	}

	public Datum(Date datum) {
		date.setTime(datum);
	}

	public Datum(Calendar date) {
		this.date = date;
	}

	public Datum(int year, int month, int day) {
		date.set(year, month, day);
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public void setDate(Date datum) {
		date.setTime(datum);
	}

	public Calendar getNow() {
		return now;
	}

	public String getDifference(GregorianCalendar past) {
		return this.velueOfDiff(past);
	}

	private String velueOfDiff(GregorianCalendar past) {
		GregorianCalendar today = new GregorianCalendar();

		Months months = Months.monthsBetween(new DateTime(past), new DateTime(today));
		
		

		int jahre = months.getMonths() / 12;
		int monate = months.getMonths() % 12;

		long difference = today.getTimeInMillis() - past.getTimeInMillis();
		// ms into secents
		int dYears = -1;
		int dMonths = -1;
		final GregorianCalendar diff = new GregorianCalendar();
		diff.setTimeInMillis(difference);

		String strDiff = jahre + " Jahre und "
				+ monate + " Monate.";

		return strDiff;
	}

}
