package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Tankfuellung;
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

	private List<Tankfuellung> tankfuellungList = new ArrayList<Tankfuellung>();

	private List<Tanken> tankenList = new ArrayList<Tanken>();

	private Tank voll;

	protected TankenDao() {
	}

	@Deprecated
	public List<Tankfuellung> getTankenList() {
		return tankfuellungList;
	}

	@Deprecated
	public void init(RegisteredUser registeredUser) {
		voll = tankDao.getVoll();
		setTankenList(registeredUser);
	}

	public Tanken getMinPreisProLiter(int year, Auto auto) {
		DateTime[] dateOfYear = DateHelper.getHoleYear(year);
		return tankenRepository
				.findFirst1ByAutoAndDatumBetweenOrderByPreisProLiterDesc(auto,
						dateOfYear[0].toDate(), dateOfYear[1].toDate());
	}

	public Tanken getMaxPreisProLiter(int year, Auto auto) {
		DateTime[] dateOfYear = DateHelper.getHoleYear(year);
		return tankenRepository
				.findFirst1ByAutoAndDatumBetweenOrderByPreisProLiterAsc(auto,
						dateOfYear[0].toDate(), dateOfYear[1].toDate());
	}

	public Tanken getMaxPreisProLiter(Auto auto) {
		return tankenRepository.findFirst1ByAutoOrderByPreisProLiterAsc(auto);
	}

	public Tanken getMinPreisProLiter(Auto auto) {
		return tankenRepository.findFirst1ByAutoOrderByPreisProLiterDesc(auto);
	}

	public List<Tanken> getTankungen(Auto auto) {
		return tankenRepository.findByAutoOrderByKmStandAsc(auto);
	}

	/**
	 * 
	 * @param year
	 * @param auto
	 * @return An List of Tankungen form an Car at one year
	 */
	public List<Tanken> getTankungen(Auto auto, int year) {
		DateTime[] dateOfYear = DateHelper.getHoleYear(year);
		return tankenRepository.findByAutoAndDatumBetween(auto, dateOfYear[0].toDate(),
				dateOfYear[1].toDate());
	}

	@Deprecated
	public void setTankenList(RegisteredUser registedUser) {
		Auto aktuellesAuto = registedUser.getCurrentAuto();
		tankenList = tankenRepository
				.findByAutoOrderByKmStandAsc(aktuellesAuto);
		Tanken tVoll = null;
		Tankfuellung tVorFuellung = null;
		for (Tanken t : tankenList) {
			Tankfuellung tfuellung = new Tankfuellung(t, tVorFuellung);
			tankfuellungList.add(tfuellung);
		}
	}

	/**
	 * @deprecated
	 * @since 1.1
	 * @return
	 */
	@Deprecated
	public double giveAVGPreis() {
		double preis = 0d;
		int count = 0;
		for (Tankfuellung t : tankfuellungList) {
			preis = preis + new Double(t.getPreisProLiter().toString());
		}
		return count == 0 ? 0 : preis / count;
	}

	public void save(Tanken tanken, Auto auto) {
		auto.addTanken(tanken);
		autoRepository.save(auto);
	}

	public boolean isVoll(Tanken t) {
		if (t.getTank().getId() == voll.getId()) {
			return true;
		}
		return false;
	}

	public List<Tankfuellung> tankenUpdate(Tanken tanken,
			RegisteredUser registedUser) {
		Auto auto = registedUser.getCurrentAuto();
		tankenRepository.save(tanken);
		auto.updateTanken(tanken);
		registedUser.setCurrentAuto(auto);
		autoRepository.save(auto);
		setTankenList(registedUser);
		return getTankenList();
	}

	public Tanken search(int id) {
		return tankenRepository.findOne(id);
	}

	public Tanken like(Tanken tanken) {
		for (Tanken t : this.tankenList) {
			if (t.like(tanken)) {
				return t;
			}
		}
		return null;
	}
}
