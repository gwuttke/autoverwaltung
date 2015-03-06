package de.gw.auto.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.dao.BenutzerDAO;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Settings;
import de.gw.auto.exception.AllException;
import de.gw.auto.repository.UserRepository;

@Service
public class BenutzerService {
	
	@Resource
	private UserRepository userRepository;
	
	@Autowired
	private Settings settings;
	
	

	public Settings Login(String benutzername, String passwort)
			throws Exception {
		
		Benutzer benutzer = (Benutzer) userRepository.findByBenutzernameAndPasswort(benutzername, passwort);

		if (benutzer == null) {
			AllException
					.error("Benutzername oder Passwort ist Falsch, "
							+ "bitte registrieren Sie sich wenn Sie noch kein Konto haben",
							new IllegalArgumentException());
			return null;
		}
		
		settings.setBenutzer(benutzer);
		settings.setAutos();
		System.out.println("LogIn!!");
		return settings;

	}

	public void registry(String name, String vorname, String benutzername,
			String passwort, String eMail) {
		BenutzerDAO benutzerDAO = new BenutzerDAO();
		Benutzer benutzer = new Benutzer(name, vorname, benutzername, passwort,
				eMail);
		userRepository.save(benutzer);

	}
}
