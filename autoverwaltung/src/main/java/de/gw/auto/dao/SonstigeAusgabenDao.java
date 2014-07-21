package de.gw.auto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Settings;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.hibernate.UpdateDaten;

public class SonstigeAusgabenDao{

	private Settings setting;
	private List<SonstigeAusgaben> sonstigeAusgabenList = new ArrayList<SonstigeAusgaben>();
	private UpdateDaten update = new UpdateDaten();
	
	public SonstigeAusgabenDao(Settings setting) {
		super();
		this.setting = setting;
	}

	public void intoDatabase(SonstigeAusgaben sa) {
		update.addSonstigeAusgaben(sa);
	}
	
	public List<SonstigeAusgaben> getSonstigeAusgabenList() {
		return sonstigeAusgabenList;
	}

	public void setSonstigeAusgabenList(Settings setting) throws SQLException {
		sonstigeAusgabenList = new DatenAbrufen().getSonstigeAusgabens(setting);
		
	}

	public void updateListe() throws SQLException {
		getSonstigeAusgabenList().clear();
		setSonstigeAusgabenList(setting);
	}

}
