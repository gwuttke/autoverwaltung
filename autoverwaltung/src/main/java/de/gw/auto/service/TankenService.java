package de.gw.auto.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import de.gw.auto.dao.Berechnung;
import de.gw.auto.dao.TankenDao;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;

public class TankenService {

	private TankenDao tankenDao = null;

	private Settings setting = null;

	public TankenService(Settings setting) {
		super();
		this.setting = setting;
		tankenDao = new TankenDao(this.setting);
	}
	
	public Vector<Tanken> loadTankungen(int i){
		return new Vector<Tanken>(tankenDao.getTankenList());
	}

	public Object[][] loadTankungen() {
		/**
		 * Folgende Datenstrucktur:
		 * Datum;Benzinart;Km-Stand;Ort;Land;Tankfüllstand
		 * ;Liter;PreisProLitter;Kosten
		 * 
		 * @return Object[][] wenn keine Daten vorhanden dann null
		 */

		List<Tanken> tankungen = tankenDao.getTankenList();

		if (tankungen == null) {
			return null;
		}

		Object[][] o;
		int index = 0;
		Tanken tOld = null;
		Tanken tVoll = null;
		o = new Object[tankungen.size()][11];
		for (Tanken t : tankungen) {

			o[index][0] = t.getDatum();
			o[index][1] = t.getBenzinArt();
			o[index][2] = t.getKmStand();
			if (index > 0) {
				o[index][3] = t.getKmStand() - tOld.getKmStand();

			} else {
				o[index][3] = t.getKmStand()
						- setting.getAktuellAuto().getKmKauf();
			}
			if (tVoll != null) {
				o[index][4] = Berechnung.getVerbrachPro100Km(t, tVoll);
			} else {
				o[index][4] = BigDecimal.ZERO;
			}
			o[index][5] = t.getOrt();
			o[index][6] = t.getLand();
			o[index][7] = t.getTank();
			o[index][8] = t.getLiter();
			o[index][9] = t.getPreisProLiter();
			o[index][10] = t.getKosten();

			tOld = t;

			if (t.getTank().getId() == 4) {
				tVoll = t;
			}

			index++;
		}
		return o;
	}

	public Tanken search(Tanken tanken) {
		return this.tankenDao.like(tanken); 
	}

	public TankenDao addTankfuellung(Tanken tanken) {
		this.tankenDao = tankenDao.tankenIntoDatabase(tanken, this.setting);
		return tankenDao;
	}
	
	public TankenDao updateTankfuellung(Tanken tanken){
		this.tankenDao = tankenDao.tankenUpdate(tanken, this.setting);
		return tankenDao;
	}

}
