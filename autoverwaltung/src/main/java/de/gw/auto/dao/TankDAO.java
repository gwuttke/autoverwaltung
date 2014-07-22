package de.gw.auto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tank;
import de.gw.auto.hibernate.DatenAbrufen;

public class TankDAO {
	
	private Settings settins;

	private List<Tank> tankfuellungList = new ArrayList<Tank>();

	public TankDAO(Settings settins) {
		super();
		this.settins = settins;
	}


	public List<Tank> getTankList() {
		if (tankfuellungList.isEmpty() == true){
			try {
				setTankList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			getTankList();
		}
		
		return tankfuellungList;
	}
	
	
	public Tank getTank(int id){
		for(Tank ta : tankfuellungList){
			if(ta.getId() == id){
				return ta;
			}
		}
		return null;
	}

	public void setTankList() throws SQLException {
		tankfuellungList = new DatenAbrufen().getBefuellung();
	}
	

}