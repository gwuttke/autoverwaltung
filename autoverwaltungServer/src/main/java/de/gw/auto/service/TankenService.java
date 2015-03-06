package de.gw.auto.service;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;
import de.gw.auto.domain.Tankfuellung;

@Service
public class TankenService {

	private List<Tankfuellung> tankenList;

	@Autowired
	private TankenDao tankenDao;

	private Settings setting = null;

	protected TankenService() {
	}

	public void init(Settings setting) {
		this.setting = setting;
		this.tankenDao.init(setting);
		tankenList = tankenDao.getTankenList();
	}

	public Vector<Tanken> loadTankungen(int i) {
		return new Vector<Tanken>(tankenList);
	}

	public List<Tankfuellung> getPrintList() {
		return tankenList;
	}

	/**
	 * Folgende Datenstrucktur:
	 * Datum;Benzinart;Km-Stand;Ort;Land;Tankfüllstand
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
		Tanken tOld = null;
		Tanken tVoll = null;
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

	public List<Tankfuellung> addTankfuellung(Tanken tanken) {
		this.tankenList = tankenDao.tankenIntoDatabase(tanken, this.setting);
		return tankenList;
	}

	public List<Tankfuellung> updateTankfuellung(Tanken tanken) {
		this.tankenList = tankenDao.tankenUpdate(tanken, this.setting);
		return tankenList;
	}

}
