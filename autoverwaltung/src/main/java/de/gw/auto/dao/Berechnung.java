package de.gw.auto.dao;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.gw.auto.Connstans;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Datum;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Vergleich;

public class Berechnung {
	private double b;
	private Map<String, Info> ausgabenBerechnungen = new HashMap<String, Info>();
	
	

	public Map<String, Info> getAusgabenBerechnungen() {
		return ausgabenBerechnungen;
	}
	
	public void addAusgabenBerechnungen(String bezeichnung, Info info){
		this.ausgabenBerechnungen.put(bezeichnung, info);
	}

	public String getRound(BigDecimal value, int nachkommata) {
		return round(value, nachkommata);

	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}
	
	public static int getInsgGefahreneKm(Auto auto){
		return auto.getKmAktuell() - auto.getKmKauf();
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

	public Set<Info> tankenAusgabe(TankenDao tDao) {

		Info tiKosten = new Info();
		Info tiMaxPreisProLiter = new Info();
		Info tiMinPreisProLiter = new Info();
		Info tiAnzahlLiter = new Info();

		Set<Info> setTInfo = new HashSet<Info>();
		Datum datum = new Datum();

		tiKosten.setName(Connstans.KOSTEN);
		tiMaxPreisProLiter.setName(Connstans.MAX_PREIS);
		tiMinPreisProLiter.setName(Connstans.MIN_PREIS);
		tiAnzahlLiter.setName(Connstans.ANZAHL_LITER);

		for (Tanken t : tDao.getTankenList()) {

			datum.setDate(t.getDatum());
			// Jahr, wo getankt wurde
			int jahr = datum.getDate().get(Calendar.YEAR);
			// aktuellen Jahr
			int nowJahr = datum.getNow().get(Calendar.YEAR);

			// Gesammt berechnung
			tiKosten.setGesammt(tiKosten.getGesammt().add(t.getKosten()));
			tiMaxPreisProLiter.setGesammt(new Vergleich(t
					.getPreisProLiter()).max());
			tiMinPreisProLiter.setGesammt(new Vergleich(t
					.getPreisProLiter()).min());
			tiAnzahlLiter.setGesammt(tiAnzahlLiter.getGesammt().add(
					t.getLiter()));

			if (nowJahr == jahr) {
				tiKosten.setDiesesJahr(tiKosten.getDiesesJahr().add(
						t.getKosten()));
				tiMaxPreisProLiter.setDiesesJahr(new Vergleich(t
						.getPreisProLiter()).max());
				tiMinPreisProLiter.setDiesesJahr(new Vergleich(t
						.getPreisProLiter()).min());
				tiAnzahlLiter.setDiesesJahr(tiAnzahlLiter.getDiesesJahr().add(
						t.getLiter()));

			} else if (nowJahr - 1 == jahr) {

				tiKosten.setVorjahr(tiKosten.getVorjahr().add(t.getKosten()));
				tiMaxPreisProLiter.setVorjahr(new Vergleich(t
						.getPreisProLiter()).max());
				tiMinPreisProLiter.setVorjahr(new Vergleich(t
						.getPreisProLiter()).min());
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
