package de.gw.auto.dao;

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
		setSonstigeAusgabenList(this.setting);
	}

	public SonstigeAusgabenDao intoDatabase(SonstigeAusgaben sa) {
		update.addSonstigeAusgaben(sa);
		return updateListe();
		
		
	}
	
	public List<SonstigeAusgaben> getSonstigeAusgabenList() {
		return sonstigeAusgabenList;
	}

	public void setSonstigeAusgabenList(Settings setting) {
		sonstigeAusgabenList = new DatenAbrufen().getSonstigeAusgabens(setting);
		
	}

	public SonstigeAusgabenDao updateListe() {
		setSonstigeAusgabenList(setting);
		return this;
	}

}
