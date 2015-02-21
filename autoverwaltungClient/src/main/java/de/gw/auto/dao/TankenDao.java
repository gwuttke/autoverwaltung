package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.xml.internal.security.Init;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Tankfuellung;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.hibernate.UpdateDaten;
import de.gw.auto.repository.AutoRepository;
import de.gw.auto.repository.TankenRepository;

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

	public List<Tankfuellung> getTankenList() {

		return tankfuellungList;
	}

	/*
	public TankenDao(Settings setting) {
		
	}
*/
	
	public void init(Settings setting) {
		voll = tankDao.getVoll();
		setTankenList(setting);
		
	}
	
	public void setTankenList(Settings setting) {
		Auto aktuellesAuto = setting.getAktuellAuto();
		tankenList = tankenRepository
				.findByAutoOrderByKmStandAsc(aktuellesAuto);

		int index = 0;
		Tanken tVor = null;
		Tanken tVoll = null;
		for (Tanken t : tankenList) {
			Tankfuellung tfuellung = new Tankfuellung(t);
			if (index > 0) {
				tfuellung.setGefahreneKm(Berechnung.getGefahreneKilometer(tVor,
						t));
			} else {
				tfuellung.setGefahreneKm(Berechnung.getGefahreneKilometer(
						t.getAuto(), t));
			}
			tfuellung.setVerbrauch100Km(Berechnung
					.getVerbrachPro100Km(t, tVoll));

			tVor = t;
			if (isVoll(t)) {
				tVoll = t;
			}
			tankfuellungList.add(tfuellung);
			index++;
		}
	}

	public double giveAVGPreis() {
		double preis = 0d;
		int count = 0;
		for (Tankfuellung t : tankfuellungList) {
			preis = preis + new Double(t.getPreisProLiter().toString());
		}
		return count == 0 ? 0 : preis / count;
	}

	public List<Tankfuellung> tankenIntoDatabase(Tanken tanken, Settings setting) {
		Auto auto = setting.getAktuellAuto();

		tanken = tankenRepository.save(tanken);

		auto.addTanken(tanken);
		setting.setAktuellAuto(auto);

		autoRepository.save(auto);
		setTankenList(setting);
		return getTankenList();

	}

	public boolean isVoll(Tanken t) {
		if (t.getTank().getId() == voll.getId()) {
			return true;
		}
		return false;
	}

	public List<Tankfuellung> tankenUpdate(Tanken tanken, Settings setting) {

		Auto auto = setting.getAktuellAuto();

		tankenRepository.save(tanken);
		auto.updateTanken(tanken);
		setting.setAktuellAuto(auto);

		autoRepository.save(auto);

		setTankenList(setting);
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
