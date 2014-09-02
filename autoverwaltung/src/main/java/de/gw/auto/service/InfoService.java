package de.gw.auto.service;

import java.util.ArrayList;
import java.util.List;

import de.gw.auto.Constans;
import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.dao.SonstigeAusgabenInfo;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.dao.TankenInfo;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Settings;

public class InfoService {

	Settings setting = null;

	public InfoService(Settings settings){
		this.setting = settings;
	}

	public Object[][] loadInfos() {
		
		TankenDao tankenDao = new TankenDao(this.setting);
		SonstigeAusgabenDao sonstigeAusgabenDao = new SonstigeAusgabenDao(this.setting);
		Object[][] o = new Object[0][0];
		int index = 0;

		TankenInfo tankenInfo = new TankenInfo(tankenDao, setting);
		SonstigeAusgabenInfo sonstigeAusgabenInfo = new SonstigeAusgabenInfo(
				sonstigeAusgabenDao);

		List<Info> tankInfos = tankenInfo.getTankenInfos();
		List<Info> sonstigeAusgabenInfos = sonstigeAusgabenInfo
				.getSonstigeAusgabenInfos();
		List<Info> allInfos = new ArrayList<Info>();

		Info allKosten = new Info(Constans.GESAMT_KOSTEN);
		try {
			allKosten = tankenInfo.searchInfo(Constans.TANKEN_KOSTEN);
			allKosten = allKosten.add(sonstigeAusgabenInfo
					.searchInfo(Constans.SONSTIGEAUSGABEN_KOSTEN));
		} catch (NullPointerException ex) {
			allKosten = new Info(Constans.GESAMT_KOSTEN);
		} finally {
			allKosten.setName(Constans.GESAMT_KOSTEN);
		}

		allInfos.addAll(tankInfos);
		allInfos.addAll(sonstigeAusgabenInfos);
		allInfos.add(allKosten);

		if (tankInfos.size() == 0) {
			return o;
		}

		o = new Object[allInfos.size()][4];

		for (Info info : allInfos) {
			o[index][0] = info.getName();
			o[index][1] = info.getDiesesJahr();
			o[index][2] = info.getVorjahr();
			o[index][3] = info.getGesammt();
			index++;
		}
		return o;
	}

}
