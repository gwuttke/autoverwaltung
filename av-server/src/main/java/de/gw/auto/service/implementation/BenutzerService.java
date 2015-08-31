package de.gw.auto.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.gw.auto.dao.BenutzerDAO;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.repository.UserRepository;
import de.gw.auto.service.I_BenutzerService;
import de.gw.auto.service.RegisteredUser;


@Service
@Transactional
public class BenutzerService implements I_BenutzerService {
	

	@Autowired
	private BenutzerDAO benutzerDAO;
	
	public BenutzerService() {
		super();
		// TODO Auto-generated constructor stub 
	}

	/* (non-Javadoc)
	 * @see de.gw.auto.service.I_BenutzerService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public RegisteredUser login(String benutzername,String eMail, String passwort) throws UsernameNotFoundException{
		
		Benutzer user = new Benutzer(benutzername, passwort);
		user.seteMail(eMail);
		
		user = benutzerDAO.logInBenutzer(user);
		
		if (user == null) {
			throw new UsernameNotFoundException(
					"Benutzername oder Passwort ist Falsch, "
							+ "bitte registrieren Sie sich wenn Sie noch kein Konto haben",
					new IllegalArgumentException());
		}
		System.out.println("LogIn!!");
		return new RegisteredUser(user);

	}

	/* (non-Javadoc)
	 * @see de.gw.auto.service.I_BenutzerService#registry(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void registry(String name, String vorname, String benutzername,
			String passwort, String eMail) {
				
		benutzerDAO.registry(name, vorname, benutzername, passwort, eMail);

	}

	/* (non-Javadoc)
	 * @see de.gw.auto.service.I_BenutzerService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
			
		return benutzerDAO.loadUserByUsername(username);
	}
}
