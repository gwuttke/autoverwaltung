package de.gw.auto.dao;

import java.util.GregorianCalendar;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Datum;

public class AutoAlter {
	private String autoAlter;
	private String besitzAlter;
	private static Datum datum = new Datum();

	public AutoAlter(Auto auto) {
		berechneAutoAlter(auto);
	}

	public String getAutoAlter() {
		return autoAlter;
	}

	public String getBesitzAlter() {
		return besitzAlter;
	}

	private void berechneAutoAlter(Auto auto) {

		if ((auto.getKauf().getTime() > 0)
				&& (auto.getErstZulassung().getTime() > 0)) {

			GregorianCalendar seitKauf = new GregorianCalendar();
			GregorianCalendar seitErstZulassung = new GregorianCalendar();

			seitErstZulassung.setTimeInMillis(auto.getErstZulassung().getTime());
			seitKauf.setTimeInMillis(auto.getKauf().getTime());

			this.autoAlter = datum.getDifference(seitErstZulassung);

			this.besitzAlter = datum.getDifference(seitKauf);
		} else {
			this.autoAlter = "Keine Angabe Möglich";
			this.besitzAlter = "Keine Angabe Möglich";

		}
	}

}
