package de.gw.auto.domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.gw.auto.dao.TankenDao;

public class Berechnung {
	private double b;

	public String getRound(BigDecimal value, int nachkommata) {
		return round(value, nachkommata);

	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	private String round(BigDecimal value, int nachkommaStelle) {
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

	public Set<TankenInfo> tankenAusgabe(TankenDao tDao) {

		TankenInfo tiKosten = new TankenInfo();
		TankenInfo tiMaxPreisProLiter = new TankenInfo();
		TankenInfo tiMinPreisProLiter = new TankenInfo();
		TankenInfo tiAnzahlLiter = new TankenInfo();

		Set<TankenInfo> setTInfo = new HashSet<TankenInfo>();
		Datum datum = new Datum();

		tiKosten.setName("Kosten");
		tiMaxPreisProLiter.setName("MaxPreis");
		tiMinPreisProLiter.setName("MinPreis");
		tiAnzahlLiter.setName("AnzahlLiter");

		for (Tanken t : tDao.getTankenList()) {

			datum.setDate(t.getDatum());
			// Jahr, wo getankt wurde
			int jahr = datum.getDate().get(Calendar.YEAR);
			// aktuellen Jahr
			int nowJahr = datum.getNow().get(Calendar.YEAR);

			// Gesammt berechnung
			tiKosten.setGesammt(tiKosten.getGesammt().add(t.getKosten()));
			tiMaxPreisProLiter.setGesammt(new Vergleich(new BigDecimal(t
					.getPreisProLiter())).max());
			tiMinPreisProLiter.setGesammt(new Vergleich(new BigDecimal(t
					.getPreisProLiter())).min());
			tiAnzahlLiter.setGesammt(tiAnzahlLiter.getGesammt().add(
					t.getLiter()));

			if (nowJahr == jahr) {
				tiKosten.setDiesesJahr(tiKosten.getDiesesJahr().add(
						t.getKosten()));
				tiMaxPreisProLiter.setDiesesJahr(new Vergleich(new BigDecimal(t
						.getPreisProLiter())).max());
				tiMinPreisProLiter.setDiesesJahr(new Vergleich(new BigDecimal(t
						.getPreisProLiter())).min());
				tiAnzahlLiter.setDiesesJahr(tiAnzahlLiter.getDiesesJahr().add(
						t.getLiter()));

			} else if (nowJahr - 1 == jahr) {

				tiKosten.setVorjahr(tiKosten.getVorjahr().add(t.getKosten()));
				tiMaxPreisProLiter.setVorjahr(new Vergleich(new BigDecimal(t
						.getPreisProLiter())).max());
				tiMinPreisProLiter.setVorjahr(new Vergleich(new BigDecimal(t
						.getPreisProLiter())).min());
				tiAnzahlLiter.setVorjahr(tiAnzahlLiter.getVorjahr().add(
						t.getLiter()));
			}
		}
		setTInfo.add(tiKosten);
		setTInfo.add(tiMinPreisProLiter);
		setTInfo.add(tiMaxPreisProLiter);
		setTInfo.add(tiAnzahlLiter);
		
		return setTInfo;
	}
}
