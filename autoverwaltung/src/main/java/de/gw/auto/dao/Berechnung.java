package de.gw.auto.dao;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.gw.auto.Constans;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Datum;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Tanken;
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

		Info tiKosten = new Info(Constans.KOSTEN);
		Info tiMaxPreisProLiter = new Info(Constans.MAX_PREIS);
		Info tiMinPreisProLiter = new Info(Constans.MIN_PREIS);
		Info tiAnzahlLiter = new Info(Constans.ANZAHL_LITER);
		Info tiAvgPreisProLiter = new Info(Constans.AVG_Preis);
		Info tiSummeKm = new Info(Constans.GEFAHRENE_KM);

		Set<Info> setTInfo = new HashSet<Info>();
		Set<BigDecimal> preiseGesamt = new HashSet<BigDecimal>();
		Set<BigDecimal> preiseDiesesJahr = new HashSet<BigDecimal>();
		Set<BigDecimal> preiseLetztesJahr = new HashSet<BigDecimal>();
		Datum datum = new Datum();

		int index = 0;
		for (Tanken t : tDao.getTankenList()) {
			Tanken tVorher = null;;
			if(index<0){
				tVorher = t;
			}
			

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
			tiSummeKm.setGesammt(new BigDecimal(tDao.getAuto().getKmAktuell() - tDao.getAuto().getKmKauf()));
			preiseGesamt.add(t.getPreisProLiter());
			
			if (nowJahr == jahr) {
				tiKosten.setDiesesJahr(tiKosten.getDiesesJahr().add(
						t.getKosten()));
				tiMaxPreisProLiter.setDiesesJahr(new Vergleich(t
						.getPreisProLiter()).max());
				tiMinPreisProLiter.setDiesesJahr(new Vergleich(t
						.getPreisProLiter()).min());
				tiAnzahlLiter.setDiesesJahr(tiAnzahlLiter.getDiesesJahr().add(
						t.getLiter()));
				tiSummeKm.setDiesesJahr(tiSummeKm.getDiesesJahr().add(new BigDecimal(t.getKmStand())));
				preiseDiesesJahr.add(t.getPreisProLiter());

			} else if (nowJahr - 1 == jahr) {

				tiKosten.setVorjahr(tiKosten.getVorjahr().add(t.getKosten()));
				tiMaxPreisProLiter.setVorjahr(new Vergleich(t
						.getPreisProLiter()).max());
				tiMinPreisProLiter.setVorjahr(new Vergleich(t
						.getPreisProLiter()).min());
				tiAnzahlLiter.setVorjahr(tiAnzahlLiter.getVorjahr().add(
						t.getLiter()));
				tiSummeKm.setVorjahr(tiSummeKm.getVorjahr().add(new BigDecimal(t.getKmStand())));
				preiseLetztesJahr.add(t.getPreisProLiter());
			}
			
			tiAvgPreisProLiter.setGesammt(findAverage(preiseGesamt));
			tiAvgPreisProLiter.setDiesesJahr(findAverage(preiseDiesesJahr));
			tiAvgPreisProLiter.setVorjahr(findAverage(preiseLetztesJahr));
			setTInfo.add(tiKosten);
			setTInfo.add(tiMinPreisProLiter);
			setTInfo.add(tiMaxPreisProLiter);
			setTInfo.add(tiAnzahlLiter);	
		}
		
		
		return setTInfo;
	}
	
	private BigDecimal findAverage(Set<BigDecimal> bigDecimals){
		BigDecimal summe = BigDecimal.ZERO;
		
		for(BigDecimal bd : bigDecimals){
			summe.add(bd);
		}
		return summe.divide(new BigDecimal(bigDecimals.size()));
	}
	private int kmDifferenz(Tanken tVorher, Tanken tAktuell){
		return tAktuell.getKmStand() - tVorher.getKmStand();
	} 
	
}
