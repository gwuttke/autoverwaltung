package de.gw.auto.dao;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Tanken;
import de.gw.auto.repository.AutoRepository;
import de.gw.auto.repository.TankenRepository;
import de.gw.auto.service.RegisteredUser;
import de.gw.auto.service.helper.DateHelper;

@Service
public class TankenDao {
	@Autowired
	private TankenRepository tankenRepository;

	@Autowired
	public TankDAO tankDao;

	@Autowired
	private AutoRepository autoRepository;

	protected TankenDao() {
	}

	/**
	 * 
	 * @param auto
	 * @return gibt den Durchschittspreis pro Liter eines autos in einem
	 *  bestimmten Jahr aus liefert -1 wenn die anzahl der Tankf�llungen
	 *  0 ergeben
	 */
	public Double getAvgPreisProLiter(int year, Auto auto) {
		DateTime[] dateOfYear = DateHelper.getHoleYear(year);
		Double avg = tankenRepository.findAvgByPreisProLiter(auto, dateOfYear[0].toDate(), dateOfYear[1].toDate());
		return avg == null?-1d:avg;
	}

	public Tanken getMinPreisProLiter(int year, Auto auto) {
		DateTime[] dateOfYear = DateHelper.getHoleYear(year);
		return tankenRepository.findFirst1ByAutoAndDatumBetweenOrderByPreisProLiterAsc(auto, dateOfYear[0].toDate(),
				dateOfYear[1].toDate());
	}

	public Tanken getMaxPreisProLiter(int year, Auto auto) {
		DateTime[] dateOfYear = DateHelper.getHoleYear(year);
		return tankenRepository.findFirst1ByAutoAndDatumBetweenOrderByPreisProLiterDesc(auto, dateOfYear[0].toDate(),
				dateOfYear[1].toDate());
	}

	public Tanken getMaxPreisProLiter(Auto auto) {
		return tankenRepository.findFirst1ByAutoOrderByPreisProLiterDesc(auto);
	}

	public Tanken getMinPreisProLiter(Auto auto) {
		return tankenRepository.findFirst1ByAutoOrderByPreisProLiterAsc(auto);
	}

	/**
	 * 
	 * @param auto
	 * @return gibt den Durchschittspreis pro Liter eines autos aus liefert -1
	 *         wenn die anzahl der Tankf�llungen 0 ergeben
	 */
	public Double getAvgPreisProLiter(Auto auto) {
		Double avg = tankenRepository.findAvgByPreisProLiter(auto);
		return avg == null?-1d:avg;
	}

	public List<Tanken> getTankungen(Auto auto) {
		return tankenRepository.findByAutoOrderByKmStandAsc(auto);
	}

	/**
	 * 
	 * @param year
	 * @param auto
	 * @return An List of Tankungen form a Car at one year
	 */
	public List<Tanken> getTankungen(Auto auto, int year) {
		DateTime[] dateOfYear = DateHelper.getHoleYear(year);
		return tankenRepository.findByAutoAndDatumBetween(auto, dateOfYear[0].toDate(), dateOfYear[1].toDate());
	}

	public void save(Tanken tanken, Auto auto) {
		auto.add(tanken);
		autoRepository.save(auto);
	}

	public Auto tankenUpdate(Tanken tanken, RegisteredUser registedUser) {
		Auto auto = registedUser.getCurrentAuto();
		tankenRepository.save(tanken);
		auto.updateTanken(tanken);
		registedUser.setCurrentAuto(auto);
		return autoRepository.save(auto);
	}

	public Tanken search(long id) {
		return tankenRepository.findOne(id);
	}
	
	public void delete(long id) {
		tankenRepository.delete(id);
	}

}
