package de.gw.auto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.hibernate.DatenAbrufen;

public class LandDao {

	private static List<Land> laender = new ArrayList<Land>();

	public List<Land> getLaender() {
		return laender;
	}

	public static void setLaender() throws SQLException {
		laender = new DatenAbrufen().getLaender();
	}
	
	public Land searchLand(int id){
		for (Land l : laender){
			if(l.getId() == id){
				return l;
			}
		}
		return null;
		
	}
}
