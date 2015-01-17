package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.Settings;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.hibernate.UpdateDaten;
import de.gw.auto.repository.SonstigeAusgabenRepository;

@Service
public class SonstigeAusgabenDao {

	private Settings setting;

	@Autowired
	private SonstigeAusgabenRepository sonstigeAusgabenRepository;

	protected SonstigeAusgabenDao() {
	}

	public SonstigeAusgabenDao(Settings setting) {
		super();
		this.setting = setting;
	}

	public List<SonstigeAusgaben> intoDatabase(SonstigeAusgaben sa) {
		sonstigeAusgabenRepository.save(sa);

		return getSonstigeAusgabenList();
	}

	public List<SonstigeAusgaben> getSonstigeAusgabenList() {
		return sonstigeAusgabenRepository.findByAuto(setting.getAktuellAuto());
	}
}
