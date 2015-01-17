package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Ort;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.repository.OrtRepository;

@Service
public class OrtDao {
	
	@Autowired
	private OrtRepository ortRepository;
	
	public List<Ort> getOrtList() {
		return ortRepository.findAll();
	}

	public Ort searchOrt(int id){
		return ortRepository.findOne(id);
	}
}
