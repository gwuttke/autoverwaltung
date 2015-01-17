package de.gw.auto.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Benutzer;
import de.gw.auto.exception.AllException;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.hibernate.UpdateDaten;
import de.gw.auto.repository.UserRepository;

@Service
public class BenutzerDAO {

	@Autowired
	private UserRepository userRepository;

	public Benutzer logInBenutzer(Benutzer loginBenutzer) {

		return userRepository.findByBenutzernameAndPasswort(loginBenutzer.getBenutzername(), loginBenutzer.getPasswort());
		/*
		try {
			this.benutzer = new DatenAbrufen().getBenutzer(loginBenutzer);
		} catch (Exception e) {
			AllException.messageBox("Falscher Benutzer",
					"Dieser Benutzer ist nicht vorhanden");
		}
		*/
	}

}
