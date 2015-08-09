package de.gw.auto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;

public interface LandRepository extends JpaRepository<Land, Integer> {

	List<Ort> findOrtById(int landId);
	
	Land findByName(String name);
}
