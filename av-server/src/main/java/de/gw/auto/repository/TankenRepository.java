package de.gw.auto.repository;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Tanken;

@Repository
public interface TankenRepository extends JpaRepository<Tanken, Integer>,
		JpaSpecificationExecutor<Tanken> {
	public List<Tanken> findByAutoOrderByKmStandDesc(Auto auto);

	public List<Tanken> findByAutoOrderByKmStandAsc(Auto auto);

	public List<Tanken> findByAutoAndDatumBetween(Auto auto,
			Date startDate, Date endDate);

	public Tanken findFirst1ByAutoAndDatumBetweenOrderByPreisProLiterDesc(
			Auto auto, Date startDate, Date endDate);

	public Tanken findFirst1ByAutoAndDatumBetweenOrderByPreisProLiterAsc(
			Auto auto, Date startDate, Date endDate);

	public Tanken findFirst1ByAutoOrderByPreisProLiterDesc(Auto auto);

	public Tanken findFirst1ByAutoOrderByPreisProLiterAsc(Auto auto);
}
