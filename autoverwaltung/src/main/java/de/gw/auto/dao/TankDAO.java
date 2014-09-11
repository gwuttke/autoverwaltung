package de.gw.auto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Tank;
import de.gw.auto.hibernate.DatenAbrufen;

public class TankDAO {

	private List<Tank> tankfuellungList = new ArrayList<Tank>();
	

	public TankDAO() {
		setTankList();
	}

	public List<Tank> getTankList() {
		return tankfuellungList;
	}

	public Tank searchTank(int id) {
		for (Tank ta : tankfuellungList) {
			if (ta.getId() == id) {
				return ta;
			}
		}
		return null;
	}

	public Tank searchTank(String text) {
		for (Tank ta : tankfuellungList) {
			if (ta.getBeschreibung() == text) {
				return ta;
			}
		}
		return null;
	}

	public Tank getVoll() {
		for (Tank t : this.tankfuellungList) {
			if (t.getBeschreibung().equals("1/1") || t.getBeschreibung().equals("4/4")) {
				return t;
			}
		}
		return null;
	}

	public void setTankList() {
		tankfuellungList = new DatenAbrufen().getBefuellung();
		if (tankfuellungList.isEmpty()) {
			tankfuellungList.add(new Tank("noch keine Angaben"));
		}
	}

}
