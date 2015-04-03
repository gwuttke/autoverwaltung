package de.gw.auto.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Benzinart;
import de.gw.auto.repository.BenzinartRepository;

@Service
public class BenzinartDAO {

	@Autowired
	private BenzinartRepository benzinartRepository;

	public BenzinartDAO() {

	}

	public List<Benzinart> getBenzinartList() {
		return benzinartRepository.findAll();
	}

	public Benzinart searchBenzinartById(int id) {
		return benzinartRepository.findOne(id);
	}
}
