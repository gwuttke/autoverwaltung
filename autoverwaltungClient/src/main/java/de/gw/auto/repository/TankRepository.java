package de.gw.auto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.gw.auto.domain.Tank;

public interface TankRepository extends JpaRepository<Tank, Integer>{

	public Tank findByBeschreibung(String beschreibung);
}
