package de.gw.auto.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.repository.SonstigeAusgabenRepository;

@Service
public class SonstigeAusgabenDao {

	private Settings setting;

	@Autowired
	private SonstigeAusgabenRepository sonstigeAusgabenRepository;

	protected SonstigeAusgabenDao() {
	}

	public void init(Settings setting) {
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
