package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Ort;
import de.gw.auto.hibernate.DatenAbrufen;

public class OrtDao {

	private static LandDao lDao = new LandDao();
	private static List<Ort> orte = new ArrayList<Ort>();
	
	
	public List<Ort> getOrtList() {
		return orte;
	}

	public static void setOrtList(){
		orte = new DatenAbrufen().getOrte();
	}
	
	public Ort searchOrt(int id){
		for(Ort o : orte){
			if (o.getId() == id){
				return o;
			}
		}
		return null;
	}
}
