package de.gw.auto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.gw.auto.domain.Kraftstoff;
import de.gw.auto.domain.Kraftstoffsorte;

public interface KraftstoffsorteRepository extends JpaRepository<Kraftstoffsorte, Integer>{

}
