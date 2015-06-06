package de.gw.auto.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.dao.AutoDAO;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.service.RegisteredUser;

@Service
public class AutoService {

	@Autowired
	AutoDAO autoDao;
	
	public RegisteredUser addAuto(RegisteredUser user, Auto auto){
		user = autoDao.carIntoDatabase(user, auto);
		return user;
	}
	
	public Auto find(String kennzeichen) {
		return autoDao.find(kennzeichen);
	}
	
	public Auto findByID(int id) {
		return autoDao.findById(id);

	}
	
	public List<Auto> findByBenutzer(List<Benutzer> users){
		return autoDao.findByBenutzer(users);
	}
	
	public Auto updateAuto(Auto a){
		return autoDao.updateAuto(a.getId(), a.getBenzinarten(), a.getErstZulassung(), a.getKauf(), a.getKmKauf(), a.getKmAktuell());
	}
}
