package de.gw.auto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.gw.auto.domain.Auto;

public interface AutoRepository extends JpaRepository<Auto, Integer>{

}
