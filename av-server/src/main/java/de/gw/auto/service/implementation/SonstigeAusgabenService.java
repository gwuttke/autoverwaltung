package de.gw.auto.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.service.RegisteredUser;
@Controller
public class SonstigeAusgabenService {
	
	@Autowired
	SonstigeAusgabenDao sonstigeAusgabenDao;
	
	List<SonstigeAusgaben> sonstigeAusgabenList = null;



	protected SonstigeAusgabenService(){
		
	}
	
	public Object[][] loadSonstigeAusgaben(RegisteredUser registeredUser) {
		Object[][] o = new Object[0][0];
		int index = 0;
		
		int entrySize = sonstigeAusgabenDao.getSonstigeAusgabenList(registeredUser).size();

		if (entrySize > 0) {
			o = new Object[entrySize][4];

			for (SonstigeAusgaben sa : sonstigeAusgabenDao
					.getSonstigeAusgabenList(registeredUser)) {
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
