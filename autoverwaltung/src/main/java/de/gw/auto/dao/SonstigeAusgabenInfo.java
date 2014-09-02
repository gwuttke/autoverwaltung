package de.gw.auto.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.gw.auto.Constans;
import de.gw.auto.domain.Datum;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.SonstigeAusgaben;

public class SonstigeAusgabenInfo {

	private List<Info> sonstigeAusgabenInfos = new ArrayList<Info>();
	private Datum datum = new Datum();

	// aktuelles Jahr
	private final int nowJahr = datum.getNow().get(Calendar.YEAR);
	
	public SonstigeAusgabenInfo(SonstigeAusgabenDao sADao) {
		load(sADao);
	}
	
	public List<Info> getSonstigeAusgabenInfos() {
		return sonstigeAusgabenInfos;
	}

	private void load(SonstigeAusgabenDao sADao) {
		List<SonstigeAusgaben> sAs = sADao.getSonstigeAusgabenList();

		Info sAIKosten = new Info(Constans.SONSTIGEAUSGABEN_KOSTEN);

		if (sAs != null) {
			for (SonstigeAusgaben sa : sAs) {
				datum.setDate(sa.getDatum());
				int jahr = datum.getDate().get(Calendar.YEAR);

				BigDecimal kosten = sa.getKosten();

				sAIKosten.setGesammt(sAIKosten.getGesammt().add(kosten));

				if (jahr == nowJahr) {
					sAIKosten.setDiesesJahr(sAIKosten.getDiesesJahr().add(
							kosten));
				} else if (jahr == nowJahr - 1) {
					sAIKosten.setVorjahr(sAIKosten.getVorjahr().add(kosten));
				}
			}
		}
		sonstigeAusgabenInfos.add(sAIKosten);
	}
	
	public Info searchInfo(String name){
		for(Info info : sonstigeAusgabenInfos){
			if (name == info.getName()){
				return info;
			}
		}
		return null;
	}
	

}
