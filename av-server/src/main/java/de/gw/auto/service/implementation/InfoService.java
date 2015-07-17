package de.gw.auto.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.Constans;
import de.gw.auto.dao.SonstigeAusgabenInfo;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.dao.TankenInfo;
import de.gw.auto.domain.Info;
import de.gw.auto.service.RegisteredUser;

@Service
public class InfoService {
	
	@Autowired
	private TankenInfo tankenInfo;
	
	protected InfoService(){}
	
	public TankenInfo getTankenInfo() {
		return tankenInfo;
	}
	
	@Deprecated
	public void init(RegisteredUser registeredUser) {
		tankenDao.init(registeredUser);
		sonstigeAusgabenInfo.init(registeredUser);
		tankenInfo.init(tankenDao, registeredUser);
	
	}
	
	public Object[][] loadInfos() {
		
		
		Object[][] o = new Object[0][0];
		int index = 0;

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
