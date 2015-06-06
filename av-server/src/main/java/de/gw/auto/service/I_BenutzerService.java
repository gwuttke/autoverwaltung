package de.gw.auto.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface I_BenutzerService extends UserDetailsService{

	public abstract void registry(String name, String vorname,
			String benutzername, String passwort, String eMail);

	public abstract UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException;

	RegisteredUser login(String benutzername, String eMail, String passwort)
			throws UsernameNotFoundException;
}