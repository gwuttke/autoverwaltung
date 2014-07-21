package de.gw.auto.hibernate;

import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;

public class DatenAbrufen extends DatenbankZugriff {

	private List<Benzinart> benzinarten = new ArrayList<Benzinart>();
	private List<Land> laender = new ArrayList<Land>();	
	private List<Ort> orte = new ArrayList<Ort>();
	private List<Auto> autos = new ArrayList<Auto>();
	
	private static final String FROM = "FROM "; 
	private static final String WHERE =" WHERE ";
	
	public DatenAbrufen() {
		super();
		load();
	}
	
	public List<Land> getLaender() {
		try {
			giveLaender();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return laender;
	}

	public List<Ort> getOrte() {
		try {
			giveOrte();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orte;
	}

	public List<Auto> getAutos(Benutzer benutzer) {
		try {
			giveAutosByBenutzer(benutzer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autos;
	}

	private void load(){
		try {
			giveLaender();
			giveBenzinarten();
		} catch (Exception e) {
			System.err.println("Daten konten nicht geladen werden");
			e.printStackTrace();
		}
	}
	
	private void giveLaender() throws Exception {
		laender = (List<Land>) this.select(FROM + "Land"); 

	}
	
	private void giveOrte() throws Exception {
		orte = (List<Ort>) this.select(FROM + "Ort");

	}

	private void giveBenzinarten() throws Exception {
		benzinarten = (List<Benzinart>) this.select(FROM + "Benzinart");
	}
	
	private void giveAutosByBenutzer(Benutzer benutzer) throws Exception{
		autos = (List<Auto>) this.select(FROM + "Auto" + WHERE + "benutzer =" + benutzer);
	}

	public List<Benzinart> getBenzinarten() {
		try {
			giveBenzinarten();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return benzinarten;
	}	

}
