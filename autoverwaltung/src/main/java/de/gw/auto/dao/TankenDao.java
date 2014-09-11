package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Tankfuellung;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.hibernate.UpdateDaten;

public class TankenDao {

	private List<Tankfuellung> tankfuellungList = new ArrayList<Tankfuellung>();
	private List<Tanken> tankenList = new ArrayList<Tanken>();
	private Tank voll = new TankDAO().getVoll();

	public List<Tankfuellung> getTankenList() {
		return tankfuellungList;
	}

	public TankenDao(Settings setting) {
		setTankenList(setting);
	}

	public void setTankenList(Settings setting) {
		tankenList = new DatenAbrufen().getTankfuellungen(setting);
		Collections.sort(tankenList, new Tanken());
		
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
			tfuellung
					.setVerbrauch100Km(Berechnung.getVerbrachPro100Km(t, tVoll));

			tVor = t;
			if (isVoll(t)) {
				tVoll = t;
			}
			tankfuellungList.add(tfuellung);
			index++;
		}
	}

	public TankenDao tankenIntoDatabase(Tanken tanken, Settings setting) {
		UpdateDaten update = new UpdateDaten();
		Auto auto = setting.getAktuellAuto();

		// update.addTanken(tanken);
		auto.addTanken(tanken);
		setting.setAktuellAuto(auto);
		update.updateAuto(auto);
		setTankenList(setting);
		return this;

	}
	
	public boolean isVoll(Tanken t){
		if (t.getTank().getId() == voll.getId()){
			return true;
		}
		return false;
	}

	public TankenDao tankenUpdate(Tanken tanken, Settings setting) {
		UpdateDaten update = new UpdateDaten();
		Auto auto = setting.getAktuellAuto();

		// update.addTanken(tanken);
		auto.updateTanken(tanken);
		setting.setAktuellAuto(auto);
		update.updateAuto(auto);
		setTankenList(setting);
		return this;

	}

	public Tanken search(int id) {
		for (Tanken t : this.tankenList) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
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
