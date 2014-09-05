package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.hibernate.UpdateDaten;

public class TankenDao {

	private List<Tanken> tankenList = new ArrayList<Tanken>();

	public List<Tanken> getTankenList() {
		Collections.sort(tankenList, new Tanken());
		return tankenList;
	}

	public TankenDao(Settings setting) {
		setTankenList(setting);
	}

	public void setTankenList(Settings setting) {
		tankenList = new DatenAbrufen().getTankfuellungen(setting);
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
	
	public Tanken search(int id){
		for (Tanken t : this.tankenList){
			if (t.getId() == id){
				return t;
			}
		}
		return null;
	}

	public Tanken like(Tanken tanken) { 
		for (Tanken t : this.tankenList){
			if (t.like(tanken)){
				return t;
			}
		}
		return null;
	}

}
