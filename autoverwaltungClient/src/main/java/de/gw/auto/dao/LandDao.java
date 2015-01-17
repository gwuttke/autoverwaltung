package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.repository.LandRepository;
import de.gw.auto.repository.OrtRepository;

@Service
public class LandDao {

	@Autowired
	private LandRepository landRepository;

	public List<Land> getLaender() {
		return landRepository.findAll();
	}

	public Land searchLand(int id) {
		return landRepository.findOne(id);
	}

	public List<Ort> getOrteByLand(Land land) {
		return landRepository.findOrtById(land.getId());
	}
}
