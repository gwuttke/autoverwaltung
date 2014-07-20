package de.gw.auto.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Settings;

public class AutoDAO extends SqlServer {

	Settings setting;

	Procedures proc = new Procedures();
	private List<Auto> autoList = new ArrayList<Auto>();
	Auto a = new Auto();

	public AutoDAO(Settings setting) {
		this.setting = setting;
	}

	public int CarIntoDatabase(String kfz, int kaufKm,
			java.util.Date kaufDatum, java.util.Date erstZulassung,
			Benzinart[] benzinArten) {
		java.sql.Date erstSqlDate = new java.sql.Date(erstZulassung.getTime());
		java.sql.Date kaufSqlDate = new java.sql.Date(kaufDatum.getTime());

		proc.setAddAuto(kfz, kaufKm, kaufSqlDate, erstSqlDate, benzinArten);

		return 0;
	}

	public void updateAutoList(int autoID, Benzinart[] benzinarten,
			Date eZulassung, Date kauf, int anfKm, int aktuKm) {
		for (Auto a : this.autoList) {
			if (a.getId() == autoID) {
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
					a.setKmGefahren();
				}

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
	
	public Auto findById(int id){
		for (Auto a : this.autoList) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}

}
