package de.gw.auto.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.repository.SonstigeAusgabenRepository;
import de.gw.auto.service.RegisteredUser;

@Service
public class SonstigeAusgabenDao {

	@Autowired
	private SonstigeAusgabenRepository sonstigeAusgabenRepository;

	protected SonstigeAusgabenDao() {
	}


	public List<SonstigeAusgaben> intoDatabase(SonstigeAusgaben sa) {
		sonstigeAusgabenRepository.save(sa);

		return getSonstigeAusgabenList(sa.getAuto().getUsers(), sa);
	}

	public List<SonstigeAusgaben> getSonstigeAusgabenList(RegisteredUser registeredUser) {
		return sonstigeAusgabenRepository.findByAuto(registeredUser.getCurrentAuto());
	}
	
	private List<SonstigeAusgaben> getSonstigeAusgabenList(List<Benutzer> users, SonstigeAusgaben sa) {
		for(Benutzer u : users){
			if(u.getAutos().contains(sa.getAuto())){
				return sonstigeAusgabenRepository.findByAuto(sa.getAuto());
			}
		}
		return null;
	}
} 
