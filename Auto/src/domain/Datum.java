package domain;

import java.util.GregorianCalendar;

public class Datum {
	// private GregorianCalendar past = new GregorianCalendar(this.getPYear(),
		// this.getPMonth(), this.getPDay());

	public String getDifference(GregorianCalendar past){
		return this.velueOfDiff(past);
	}
	
	
	private String velueOfDiff(GregorianCalendar past) {
		GregorianCalendar today = new GregorianCalendar();

		long difference = today.getTimeInMillis() - past.getTimeInMillis();
		// ms into secents
		int dYears = -1;
		int dMonths = -1;

		dYears = (int) (difference / (1000 * 60 * 60) % 365.25);
		dMonths = (int) (difference / (1000 * 60 * 60) % 12);

		String strDiff = dYears + " Jahre und " + dMonths + " Monate.";

		return strDiff;
	}

}
