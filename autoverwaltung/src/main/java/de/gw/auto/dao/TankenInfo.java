package de.gw.auto.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.gw.auto.Constans;
import de.gw.auto.domain.Datum;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Vergleich;

public class TankenInfo {
	private List<Info> tankenInfos = new ArrayList<Info>();

	public TankenInfo(TankenDao tDao, Settings setting) {
		load(tDao, setting);
	}

	public List<Info> getTankenInfos() {
		return tankenInfos;
	}

	private void load(TankenDao tDao, Settings setting) {

		List<Tanken> tankungen = tDao.getTankenList();
		Info tiKosten = new Info(Constans.TANKEN_KOSTEN);
		Info tiMaxPreisProLiter = new Info(Constans.MAX_PREIS);
		Info tiMinPreisProLiter = new Info(Constans.MIN_PREIS);
		Info tiAnzahlLiter = new Info(Constans.ANZAHL_LITER);
		Info tiAvgPreisProLiter = new Info(Constans.AVG_Preis);
		Info tiSummeKm = new Info(Constans.GEFAHRENE_KM);

		Set<BigDecimal> preiseGesamt = new HashSet<BigDecimal>();
		Set<BigDecimal> preiseDiesesJahr = new HashSet<BigDecimal>();
		Set<BigDecimal> preiseLetztesJahr = new HashSet<BigDecimal>();
		Datum datum = new Datum();

		Vergleich v = new Vergleich();
		int index = 0;
		if (tankungen != null) {
			Tanken tVorher = null;
			for (Tanken t : tankungen) {

				if (index > 0) {
					tVorher = t;
					v.setZahl(t.getPreisProLiter());
				} else {
					v = new Vergleich(t.getPreisProLiter());
				}

				datum.setDate(t.getDatum());
				// Jahr, wo getankt wurde
				int jahr = datum.getDate().get(Calendar.YEAR);
				// aktuelles Jahr
				int nowJahr = datum.getNow().get(Calendar.YEAR);

				// Gesammt berechnung
				tiKosten.setGesammt(tiKosten.getGesammt().add(t.getKosten()));
				tiMaxPreisProLiter.setGesammt(v.max());
				tiMinPreisProLiter.setGesammt(v.min());
				tiAnzahlLiter.setGesammt(tiAnzahlLiter.getGesammt().add(
						t.getLiter()));
				tiSummeKm
						.setGesammt(new BigDecimal(Berechnung.getInsgGefahreneKm(setting.getAktuellAuto())));
				preiseGesamt.add(t.getPreisProLiter());

				if (nowJahr == jahr) {
					tiKosten.setDiesesJahr(tiKosten.getDiesesJahr().add(
							t.getKosten()));
					tiMaxPreisProLiter.setDiesesJahr(v.max());
					tiMinPreisProLiter.setDiesesJahr(v.min());
					tiAnzahlLiter.setDiesesJahr(tiAnzahlLiter.getDiesesJahr()
							.add(t.getLiter()));
					tiSummeKm.setDiesesJahr(tiSummeKm.getDiesesJahr().add(
							new BigDecimal(t.getKmStand())));
					preiseDiesesJahr.add(t.getPreisProLiter());

				} else if (nowJahr - 1 == jahr) {

					tiKosten.setVorjahr(tiKosten.getVorjahr()
							.add(t.getKosten()));
					tiMaxPreisProLiter.setVorjahr(v.max());
					tiMinPreisProLiter.setVorjahr(v.min());
					tiAnzahlLiter.setVorjahr(tiAnzahlLiter.getVorjahr().add(
							t.getLiter()));
					tiSummeKm.setVorjahr(tiSummeKm.getVorjahr().add(
							new BigDecimal(t.getKmStand())));
					preiseLetztesJahr.add(t.getPreisProLiter());
				}

				tiAvgPreisProLiter.setGesammt(Berechnung
						.findAverage(preiseGesamt));
				tiAvgPreisProLiter.setDiesesJahr(Berechnung
						.findAverage(preiseDiesesJahr));
				tiAvgPreisProLiter.setVorjahr(Berechnung
						.findAverage(preiseLetztesJahr));
				index++;
			}
		}

		tankenInfos.add(tiMinPreisProLiter);
		tankenInfos.add(tiMaxPreisProLiter);
		tankenInfos.add(tiAnzahlLiter);
		tankenInfos.add(tiSummeKm);
		tankenInfos.add(tiKosten);
	}
	
	public Info searchInfo(String name){
		for (Info info : tankenInfos){
			if (name == info.getName()){
				return info;
			}
		}
		return null;
	}

}
