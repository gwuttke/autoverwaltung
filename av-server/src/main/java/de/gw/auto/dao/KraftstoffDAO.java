package de.gw.auto.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Kraftstoff;
import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.repository.KraftstoffRepository;
import de.gw.auto.repository.KraftstoffsorteRepository;

@Service
public class KraftstoffDAO extends DefaultDao{

	@Autowired
	private KraftstoffRepository kraftstoffRepository;

	public KraftstoffDAO() {

	}

	public List<Kraftstoff> getKraftstoffAlphabetisch() {
		return kraftstoffRepository.findAll(sortByNameAsc());
	}

	public Kraftstoff searchById(int id) {
		return kraftstoffRepository.findOne(id);
	}
}
