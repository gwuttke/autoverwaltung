package de.gw.auto.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.dao.KraftstoffDAO;
import de.gw.auto.dao.KraftstoffsorteDAO;
import de.gw.auto.dao.LandDao;
import de.gw.auto.dao.TankDAO;
import de.gw.auto.domain.Kraftstoff;
import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Tank;
import de.gw.auto.repository.KraftstoffsorteRepository;

@Service
public class StammdatenService {
	@Autowired
	private LandDao landDao;

	@Autowired
	private TankDAO tankDao;

	@Autowired
	private KraftstoffDAO kraftstoffDao;

	@Autowired
	private KraftstoffsorteDAO kraftstoffsorteDAO;

	@Autowired
	private KraftstoffsorteRepository kraftstoffsorteRepository;

	public List<Land> getLaender() {
		return landDao.getLaenderAlphabetisch();
	}

	public Land getLand(int landId) {
		return landDao.searchLand(landId);
	}

	public List<Tank> getTankstaende() {
		return tankDao.getTankAlphabetischAbsteigend();
	}

	public Tank getTankstand(int TankId) {
		return tankDao.searchTank(TankId);
	}

	public List<Kraftstoff> getKrftstoffe() {
		return kraftstoffDao.getKraftstoffAlphabetisch();
	}

	public List<Kraftstoffsorte> getKraftstoffsorten() {
		return kraftstoffsorteDAO.getKraftstoffsorteAlphabetisch();
	}

	public Kraftstoffsorte getKraftstoffsorte(int id) {
		return kraftstoffsorteDAO.searchById(id);
	}

	public List<Kraftstoffsorte> getKraftstoffsorten(Kraftstoff kraftstoff) {
		return kraftstoffDao.getKraftstoffsorten(kraftstoff);
	}
}
