package de.gw.auto.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.dao.SonstigeAusgabenDao;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.domain.Tanken;
import de.gw.auto.service.RegisteredUser;

@Service
public class SonstigeAusgabenService {
	
	@Autowired
	SonstigeAusgabenDao sonstigeAusgabenDao;
	
	protected SonstigeAusgabenService(){
	}
	
	public List<SonstigeAusgaben> get(RegisteredUser registeredUser) {
		return sonstigeAusgabenDao.get(registeredUser);
	}
	
	public void save(SonstigeAusgaben sonstigeAusgaben, RegisteredUser registeredUser) {
		sonstigeAusgabenDao.save(sonstigeAusgaben, registeredUser.getCurrentAuto());
	}
	

}
