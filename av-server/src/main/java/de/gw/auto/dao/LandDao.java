package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.repository.LandRepository;

@Service
public class LandDao extends DefaultDao {
	@Autowired
	private LandRepository landRepository;

	public List<Land> getLaenderAlphabetisch() {
		return landRepository.findAll(sortByNameAsc());
	}

	public Land searchLand(int id) {
		return landRepository.findOne(id);
	}
	
	public Land saveUpdateLand(Land land){
		return landRepository.save(land);
	}

	public Land searchByName(String name){
		return landRepository.findByName(name);
	}

	public List<Ort> getOrteByLand(Land land) {
		if(land ==null){
			return null;
		}
		List<Ort> orte = null;
		if(land.getOrte().isEmpty()){
		 orte = landRepository.findOrtById(land.getId());
		}
		
		orte = new ArrayList<Ort>(land.getOrte());
		orte.sort(new Comparator<Ort>() {
			@Override
			public int compare(Ort o1, Ort o2) {
				return o1.getOrt().compareToIgnoreCase(o2.getOrt());
			}
		});
		return orte;
	}
}
