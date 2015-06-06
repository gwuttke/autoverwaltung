package de.gw.auto.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.Constans;
import de.gw.auto.domain.Datum;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.service.RegisteredUser;

@Service
public class SonstigeAusgabenInfo {

	@Autowired
	private SonstigeAusgabenDao sonstigeAusgabenDao;
	
	private List<Info> sonstigeAusgabenInfos = new ArrayList<Info>();
	private Datum datum = new Datum();

	// aktuelles Jahr
	private final int nowJahr = datum.getNow().get(Calendar.YEAR);

	public void init(RegisteredUser registeredUser) {
		load(registeredUser);

	}

	public List<Info> getSonstigeAusgabenInfos() {
		return sonstigeAusgabenInfos;
	}

	private void load(RegisteredUser registeredUser) {
		List<SonstigeAusgaben> sAs = sonstigeAusgabenDao.getSonstigeAusgabenList(registeredUser);

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

	public Info searchInfo(String name) {
		for (Info info : sonstigeAusgabenInfos) {
			if (name == info.getName()) {
				return info;
			}
		}
		return null;
	}

}
