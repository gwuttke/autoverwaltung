package de.gw.auto.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.repository.AutoRepository;
import de.gw.auto.repository.SonstigeAusgabenRepository;
import de.gw.auto.service.RegisteredUser;

@Service
public class SonstigeAusgabenDao {

	@Autowired
	private SonstigeAusgabenRepository sonstigeAusgabenRepository;

	@Autowired
	private AutoRepository autoRepository;

	
	protected SonstigeAusgabenDao() {
	}

	public SonstigeAusgaben save(SonstigeAusgaben sa) {
		return sonstigeAusgabenRepository.save(sa);
	}

	public void save(SonstigeAusgaben sa, Auto auto) {
		auto.add(sa);
		autoRepository.save(auto);
	}

	public List<SonstigeAusgaben> get(RegisteredUser registeredUser) {
		return sonstigeAusgabenRepository.findByAuto(registeredUser.getCurrentAuto());
	}

}
