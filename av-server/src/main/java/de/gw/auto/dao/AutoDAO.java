package de.gw.auto.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Kraftstoff;
import de.gw.auto.domain.Kraftstoffsorte;
import de.gw.auto.repository.AutoRepository;
import de.gw.auto.repository.UserRepository;
import de.gw.auto.service.RegisteredUser;

@Service
public class AutoDAO {
	
	@Autowired
	private AutoRepository autoRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	protected AutoDAO(){
		
	}

	public Benutzer save(Benutzer benutzer, Auto a) {
		
		benutzer = userRepository.findOne(benutzer.getId());
			
		benutzer.addAuto(a);
		benutzer.setCurrentAuto(a);
		userRepository.save(benutzer);
		return benutzer;
	}

	public Auto updateAuto(int autoID, Kraftstoff kraftstoff,
			Date eZulassung, Date kauf, int anfKm, int aktuKm) {
		Auto a = findById(autoID);
		
		if (a != null) {
			if (kraftstoff != null) {
				a.setKraftstoff(kraftstoff);
			}
			if (eZulassung != null) {
				a.setErstZulassung((java.sql.Date) eZulassung);
			}
			if (kauf != null) {
				a.setKauf((java.sql.Date) kauf);
			}
			if (anfKm > 0) {
				a.setKmKauf(anfKm);
			}
			if (aktuKm > 0 && aktuKm > a.getKmKauf()) {
				a.setKmAktuell(aktuKm);
			}

		}
		
		return autoRepository.save(a);
	}

	public Auto find(String kennzeichen) {
		return autoRepository.findByKfz(kennzeichen);
	}

	public Auto findById(int id) {
		return autoRepository.findOne(id);
	}
	
	public List<Auto> findByBenutzer(List<Benutzer> users){
		return autoRepository.findByUsers(users);
	}

}
