package de.gw.auto.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.dao.TankenDao;
import de.gw.auto.dao.TankenInfo;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Tankfuellung;
import de.gw.auto.service.RegisteredUser;

@Service
public class TankenService extends TankenInfo {

	@Autowired
	private TankenDao tankenDao;

	protected TankenService() {
	}

	public List<Tankfuellung> getTankfuellungen(Auto auto) {
		List<Tanken> tanken = tankenDao.getTankungen(auto);
		List<Tankfuellung> tankfuellungen = new ArrayList<Tankfuellung>();
		Tankfuellung tfuellung = null;
		for (Tanken t : tanken) {
			Tankfuellung newTankfuellung = new Tankfuellung(t, tfuellung);
			tankfuellungen.add(newTankfuellung);
			tfuellung = newTankfuellung;
		}
		return tankfuellungen;
	}

	public void save(Tanken tanken, RegisteredUser registeredUser) {
		tankenDao.save(tanken, registeredUser.getCurrentAuto());
	}

	public List<Tankfuellung> updateTankfuellung(Tanken tanken,
			RegisteredUser registeredUser) {
		tankenDao.tankenUpdate(tanken, registeredUser);
		return getTankfuellungen(registeredUser.getCurrentAuto());
	}
	
	public void delete(long id) {
		tankenDao.delete(id);
	}
}
