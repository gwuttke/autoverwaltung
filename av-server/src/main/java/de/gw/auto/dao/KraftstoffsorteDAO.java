package de.gw.auto.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Kraftstoff;
import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.repository.KraftstoffRepository;
import de.gw.auto.repository.KraftstoffsorteRepository;

@Service
public class KraftstoffsorteDAO extends DefaultDao {
	@Autowired
	private KraftstoffsorteRepository kraftstoffsorteRepository;

	public KraftstoffsorteDAO() {
	}

	public List<Kraftstoffsorte> getKraftstoffsorteAlphabetisch() {
		return kraftstoffsorteRepository.findAll(sortByNameAsc());
	}
	
	public List<Kraftstoffsorte> getKraftstoffsorteAlphabetischAbsteigend() {
		return kraftstoffsorteRepository.findAll(sortByNameDesc());
	}

	public Kraftstoffsorte searchById(int id) {
		return kraftstoffsorteRepository.findOne(id);
	}
}
