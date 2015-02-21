package de.gw.auto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







import de.gw.auto.dao.AutoDAO;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Settings;

@Service
public class AutoService {

	@Autowired
	AutoDAO autoDao;
	
	public Settings addAuto(Settings setting, Auto auto){
		autoDao.carIntoDatabase(setting, auto);
		setting.addAuto(auto);
		return setting;
	}
	
	public Auto find(String kennzeichen) {
		return autoDao.find(kennzeichen);
	}
	
	public Auto findByID(int id) {
		return autoDao.findById(id);

	}
	
	public List<Auto> findByBenutzer(Benutzer benutzer){
		return autoDao.findByBenutzer(benutzer);
	}
	
	public Auto updateAuto(Auto a){
		return autoDao.updateAuto(a.getId(), a.getBenzinarten(), a.getErstZulassung(), a.getKauf(), a.getKmKauf(), a.getKmAktuell());
	}
}
