package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Kraftstoff;
import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.repository.KraftstoffRepository;
import de.gw.auto.repository.KraftstoffsorteRepository;

@Service
public class KraftstoffDAO extends DefaultDao {
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

	public List<Kraftstoffsorte> getKraftstoffsorten(Kraftstoff kraftstoff) {
		Kraftstoff k = searchById(kraftstoff.getId());
		ArrayList<Kraftstoffsorte> kraftstoffsorten = new ArrayList<Kraftstoffsorte>(k.getKraftstoffsorten());
		kraftstoffsorten.sort(new Kraftstoffsorte());
		return kraftstoffsorten;
	}
}
