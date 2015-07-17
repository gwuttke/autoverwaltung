package de.gw.auto.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.dao.KraftstoffDAO;
import de.gw.auto.dao.LandDao;
import de.gw.auto.dao.TankDAO;
import de.gw.auto.domain.Kraftstoff;
import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Tank;

@Service
public class StammdatenService {
	@Autowired
	private LandDao landDao;

	@Autowired
	private TankDAO tankDao;

	@Autowired
	private KraftstoffDAO kraftstoffDao;

	public List<Land> getLaender() {
		return landDao.getLaenderAlphabetisch();
	}

	public List<Tank> getTankstaende() {
		return tankDao.getTankAlphabetisch();
	}

	public List<Kraftstoff> getKrftstoffarten() {
		return kraftstoffDao.getKraftstoffAlphabetisch();
	}
}
