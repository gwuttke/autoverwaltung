package de.gw.auto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Tanken;

public interface TankenRepository extends JpaRepository<Tanken, Integer>{
public List<Tanken> findByAutoOrderByKmStandDesc(Auto auto);
}
