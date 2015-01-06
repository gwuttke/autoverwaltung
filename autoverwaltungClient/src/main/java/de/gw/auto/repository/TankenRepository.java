package de.gw.auto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.gw.auto.domain.Tanken;

public interface TankenRepository extends JpaRepository<Tanken, Integer>{

}
