package de.gw.auto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.domain.Tanken;
@Controller
public class SonstigeAusgabenService {
	
	@Autowired
	SonstigeAusgabenDao sonstigeAusgabenDao;
	
	List<SonstigeAusgaben> sonstigeAusgabenList = null;

	Settings setting = null;

	protected SonstigeAusgabenService(){
		
	}
	public void init(Settings setting) {
		this.setting = setting;
		sonstigeAusgabenDao.init(this.setting);
	}

	public Object[][] loadSonstigeAusgaben() {
		Object[][] o = new Object[0][0];
		int index = 0;
		
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
	
	public List<SonstigeAusgaben> addSonstigeAusgaben(SonstigeAusgaben sonstigeAusgaben){
		this.sonstigeAusgabenList = sonstigeAusgabenDao.intoDatabase(sonstigeAusgaben);
		return this.sonstigeAusgabenList;
	}
	

}
