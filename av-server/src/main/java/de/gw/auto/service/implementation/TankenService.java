package de.gw.auto.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.dao.TankenDao;
import de.gw.auto.dao.TankenInfo;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Tankfuellung;
import de.gw.auto.service.RegisteredUser;

@Service
public class TankenService extends TankenInfo {
	private List<Tankfuellung> tankenList;

	@Autowired
	private TankenDao tankenDao;

	protected TankenService() {
	}

	@Deprecated
	public void init(RegisteredUser registeredUser) {
		this.tankenDao.init(registeredUser);
		tankenList = tankenDao.getTankenList();
	}

	public List<Tankfuellung> loadTankfuellungen(Auto auto) {
		List<Tanken> tanken = tankenDao.getTankungen(auto);
		List<Tankfuellung> tankfuellungen = new ArrayList<Tankfuellung>();
		Tankfuellung tfuellung = null;
		for (Tanken t : tanken) {
			Tankfuellung newTankfuellung = new Tankfuellung(t, tfuellung);
			tankfuellungen.add(newTankfuellung);
			tfuellung = newTankfuellung;
		}
		return tankfuellungen;
	}

	public Vector<Tanken> loadTankungen(int i) {
		return new Vector<Tanken>(tankenList);
	}

	public List<Tankfuellung> getPrintList() {
		return tankenList;
	}

	/**
	 * Folgende Datenstrucktur: Datum;Benzinart;Km-Stand;Ort;Land;Tankfüllstand
	 * ;Liter;PreisProLitter;Kosten
	 * 
	 * @return Object[][] wenn keine Daten vorhanden dann null
	 */
	public Object[][] loadTankungen() {
		List<Tankfuellung> tankungen = tankenList;
		if (tankungen == null) {
			return null;
		}
		Object[][] o;
		int index = 0;
		o = new Object[tankungen.size()][11];
		for (Tankfuellung t : tankungen) {
			o[index][0] = t.getDatum();
			o[index][1] = t.getBenzinArt();
			o[index][2] = t.getKmStand();
			o[index][3] = t.getGefahreneKm();
			o[index][4] = t.getVerbrauch100Km();
			o[index][5] = t.getOrt();
			o[index][6] = t.getLand();
			o[index][7] = t.getTank();
			o[index][8] = t.getLiter();
			o[index][9] = t.getPreisProLiter();
			o[index][10] = t.getKosten();
			index++;
		}
		return o;
	}

	public Tanken search(Tanken tanken) {
		return this.tankenDao.like(tanken);
	}

	public void save(Tanken tanken, RegisteredUser registeredUser) {
		tankenDao.save(tanken, registeredUser.getCurrentAuto());
	}

	public List<Tankfuellung> updateTankfuellung(Tanken tanken,
			RegisteredUser registeredUser) {
		this.tankenList = tankenDao.tankenUpdate(tanken, registeredUser);
		return tankenList;
	}
}
