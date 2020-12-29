package de.gw.auto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Tanken;

@Repository
public interface TankenRepository extends JpaRepository<Tanken, Long>,
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
	
	@Query("SELECT AVG(t.preisProLiter) FROM Tanken t WHERE t.auto = :auto and (t.datum BETWEEN :begin and :end)")
	public Double findAvgByPreisProLiter(@Param("auto") Auto auto, @Param("begin") Date startDate,@Param("end") Date endDate);

	@Query("SELECT AVG(t.preisProLiter) FROM Tanken t WHERE t.auto = :auto")
	public Double findAvgByPreisProLiter(@Param("auto") Auto auto);

}
