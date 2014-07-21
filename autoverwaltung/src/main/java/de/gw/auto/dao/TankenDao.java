package de.gw.auto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;
import de.gw.auto.hibernate.DatenAbrufen;

public class TankenDao {

	private List<Tanken> tankenList = new ArrayList<Tanken>();

	public List<Tanken> getTankenList() {
		if (this.tankenList.isEmpty()) {
			return null;
		}
		return tankenList;
	}

	public void setTankenList(Settings setting) throws SQLException {
		tankenList = new DatenAbrufen().getTankfuellungen(setting);
	}

}
