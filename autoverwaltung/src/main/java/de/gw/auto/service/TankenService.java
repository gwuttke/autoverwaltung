package de.gw.auto.service;

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
	
	public Object[][] loadTankungen() {
		/**
		 * Folgende Datenstrucktur:
		 * Datum;Benzinart;Km-Stand;Ort;Land;Tankfüllstand;Liter;PreisProLitter;Kosten
		 * @return Object[][]
		 * wenn keine Daten vorhanden dann null
		 */
		
		if (tankenDao.getTankenList() == null){
			return null;
		}
		
		Object[][] o;
		int index = 0;
		o = new Object[tankenDao.getTankenList().size()][9];
		for (Tanken t : tankenDao.getTankenList()) {

			o[index][0] = t.getDatum();
			o[index][1] = t.getBenzinArt();
			o[index][2] = t.getKmStand();
			o[index][3] = t.getOrt().getOrt();
			o[index][4] = t.getLand().getName();
			o[index][5] = t.getTank().getBeschreibung();
			o[index][6] = t.getLiter();
			o[index][7] = t.getPreisProLiter();
			o[index][8] = t.getKosten();
			index++;
		}
		return o;
	}
	
	public TankenDao addTankfuellung(Tanken tanken){
		this.tankenDao = tankenDao.tankenIntoDatabase(tanken, this.setting);
		return tankenDao;
	}

}
