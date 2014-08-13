package de.gw.auto.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Settings;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.hibernate.UpdateDaten;

public class AutoDAO extends DatenAbrufen {

	Settings setting;

	UpdateDaten update = new UpdateDaten();

	private List<Auto> autoList = new ArrayList<Auto>();
	Auto a = new Auto();

	public AutoDAO(Settings setting) {
		this.setting = setting;
	}

	public int CarIntoDatabase(Benutzer benutzer, String kfz, int kaufKm,
			java.util.Date kaufDatum, java.util.Date erstZulassung,
			Set<Benzinart> benzinarten, int kmAktuell) {
		java.sql.Date erstSqlDate = new java.sql.Date(erstZulassung.getTime());
		java.sql.Date kaufSqlDate = new java.sql.Date(kaufDatum.getTime());

		Auto a = new Auto(kfz, kaufKm, kaufSqlDate, erstSqlDate, benzinarten,
				kmAktuell);
		
		update.addAuto(a);
		benutzer.addAuto(a);
		update.updateBenutzer(benutzer);

		return 0;
	}

	public void updateAuto(int autoID, Set<Benzinart> benzinarten,
			Date eZulassung, Date kauf, int anfKm, int aktuKm) {
		Auto a = findById(autoID);
		if (a != null) {
			if (benzinarten != null) {
				a.setBenzinarten(benzinarten);
			}
			if (eZulassung != null) {
				a.setErstZulassung((java.sql.Date) eZulassung);
			}
			if (kauf != null) {
				a.setKauf((java.sql.Date) kauf);
			}
			if (anfKm > 0) {
				a.setKmKauf(anfKm);
			}
			if (aktuKm > 0 && aktuKm > a.getKmKauf()) {
				a.setKmAktuell(aktuKm);
			}

		}
	}

	public void setAutoList(Settings setting) {

		autoList.add(a);
	}

	public List<Auto> getAutoList() {
		if (autoList.isEmpty()) {
			setAutoList(setting);
			getAutoList();
		}
		return this.autoList;
	}

	public Auto find(String kennzeichen) {
		for (Auto a : this.autoList) {
			if (a.getKfz().equals(kennzeichen)) {
				return a;
			}
		}
		return null;
	}

	public Auto findById(int id) {
		for (Auto a : this.autoList) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}

}
