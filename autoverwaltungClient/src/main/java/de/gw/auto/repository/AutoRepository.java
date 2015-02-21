package de.gw.auto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;

public interface AutoRepository extends JpaRepository<Auto, Integer> {
	public Auto findByKfz(String kfz);
	public List<Auto> findByBenutzer(Benutzer benutzer);
}
