package de.gw.auto.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Tank;
import de.gw.auto.repository.TankRepository;

@Service
public class TankDAO {

	@Autowired
	private TankRepository tankRepository;

	public TankDAO() {

	}

	public List<Tank> getTankList() {
		return tankRepository.findAll();
	}

	public Tank searchTank(int id) {
		return tankRepository.findOne(id);
	}

	public Tank searchTank(String text) {
		return tankRepository.findByBeschreibung(text);
	}

	public Tank getVoll() {

		return searchTank("4/4");
	}

}
