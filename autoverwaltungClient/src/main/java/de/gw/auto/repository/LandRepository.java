package de.gw.auto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.gw.auto.domain.Land;

public interface LandRepository extends JpaRepository<Land, Integer> {

}
