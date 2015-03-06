package de.gw.auto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.SonstigeAusgaben;

public interface SonstigeAusgabenRepository extends JpaRepository<SonstigeAusgaben, Integer>{
	public List<SonstigeAusgaben> findByAuto(Auto auto);
}
