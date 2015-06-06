package de.gw.auto.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;

public interface UserRepository extends JpaRepository<Benutzer,Integer>{
	public Benutzer findByBenutzernameOrEMailAndPasswort(String benutzername, String eMail,String passwort);
	
	public Benutzer findByBenutzername(String benutzername);
	
	public Benutzer findByEMail(String eMail);
	
}
