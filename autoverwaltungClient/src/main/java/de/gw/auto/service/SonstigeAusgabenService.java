package de.gw.auto.service;

import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.domain.Tanken;

public class SonstigeAusgabenService {
	
	SonstigeAusgabenDao sonstigeAusgabenDao = null;

	Settings setting = null;

	public SonstigeAusgabenService(Settings setting) {
		super();
		this.setting = setting;
		sonstigeAusgabenDao = new SonstigeAusgabenDao(this.setting);
	}

	public Object[][] loadSonstigeAusgaben() {
		Object[][] o = new Object[0][0];
		int index = 0;
		SonstigeAusgabenDao sonstigeAusgabenDao = new SonstigeAusgabenDao(
				this.setting);
		int entrySize = sonstigeAusgabenDao.getSonstigeAusgabenList().size();

		if (entrySize > 0) {
			o = new Object[entrySize][4];

			for (SonstigeAusgaben sa : sonstigeAusgabenDao
					.getSonstigeAusgabenList()) {
				o[index][0] = sa.getDatum();
				o[index][1] = sa.getKommentar();
				o[index][2] = sa.getKmStand();
				o[index][3] = sa.getKosten();
				index++;
			}
		}
		return o;
	}
	
	public SonstigeAusgabenDao addSonstigeAusgaben(SonstigeAusgaben sonstigeAusgaben){
		this.sonstigeAusgabenDao = sonstigeAusgabenDao.intoDatabase(sonstigeAusgaben);
		return this.sonstigeAusgabenDao;
	}
	

}
