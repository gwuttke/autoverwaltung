package de.gw.auto.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Role;
import de.gw.auto.repository.RoleRepository;
import de.gw.auto.repository.UserRepository;
import de.gw.auto.service.RegisteredUser;

@Service
public class BenutzerDAO {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public Benutzer logInBenutzer(Benutzer loginBenutzer) {
		return userRepository.findByBenutzernameOrEMailAndPasswort(
				loginBenutzer.getBenutzername(), loginBenutzer.geteMail(),
				loginBenutzer.getPasswort());
	}

	public void registry(String name, String vorname, String benutzername,
			String passwort, String eMail) {
		Role userRole = roleRepository.findByName("USER");
		Benutzer benutzer = new Benutzer(name, vorname, benutzername, passwort,
				eMail, userRole);
		userRepository.save(benutzer);
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Benutzer user = userRepository.findByEMail(username);
		if (user == null) {
			throw new UsernameNotFoundException("UserName " + username
					+ " not found");
		}
		return new RegisteredUser(user);
	}
}
