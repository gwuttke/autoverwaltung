package de.gw.auto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.gw.auto.domain.Kraftstoff;

public interface KraftstoffRepository extends JpaRepository<Kraftstoff, Integer>{

}
