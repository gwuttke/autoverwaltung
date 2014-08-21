package de.gw.auto.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.security.auth.login.AccountException;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.domain.Benzinart;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.domain.Tank;
import de.gw.auto.domain.Tanken;

public class DatenAbrufen extends DatenbankZugriff {

	private List<Benzinart> benzinarten = new ArrayList<Benzinart>();
	private List<Land> laender = new ArrayList<Land>();
	private List<Ort> orte = new ArrayList<Ort>();
	private List<Auto> autos = new ArrayList<Auto>();
	private List<SonstigeAusgaben> sontigeAusgaben = new ArrayList<SonstigeAusgaben>();
	private List<Tanken> tankfuellungen = new ArrayList<Tanken>();
	private List<Tank> befuellung = new ArrayList<Tank>();
	private Benutzer benutzer = null;
	private List<Benutzer> allBenutzer = new ArrayList<Benutzer>();

	private static final String FROM = "FROM ";
	private static final String WHERE = " WHERE ";
	private static final String AND = " AND ";

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
			e.printStackTrace();
		}
		return orte;
	}

	public List<Auto> getAutos(Benutzer benutzer) {
		try {
			giveAutosByBenutzer(benutzer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return autos;
	}

	private void load() {
		try {
			giveLaender();
			giveBenzinarten();
			giveBenutzer();
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

	private void giveTankbefuellung() throws Exception {
		befuellung = (List<Tank>) this.select(FROM + "Tank");
	}

	private void giveAutosByBenutzer(Benutzer benutzer) throws Exception {
		benutzer = (Benutzer) this.select(FROM + "Benutzer " + WHERE + "id = " + benutzer.getId()).get(0);
		autos =  benutzer.getAutos();
	}

	public List<Tank> getBefuellung() {
		try {
			giveTankbefuellung();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return befuellung;
	}

	public List<Benzinart> getBenzinarten() {
		try {
			giveBenzinarten();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return benzinarten;
	}

	public void giveSonstigeAusgabenByAuto(Auto auto) throws Exception {
		sontigeAusgaben = (List<SonstigeAusgaben>) this.select(FROM
				+ "SonstigeAusgaben" + WHERE + "auto_id = " + auto.getId());
	}

	public List<SonstigeAusgaben> getSonstigeAusgabens(Settings settings) {

		try {
			giveSonstigeAusgabenByAuto(settings.getAktuellAuto());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sontigeAusgaben;
	}

	public void giveTankfuellungByAuto(Auto auto) throws Exception {
		tankfuellungen.clear();
		Set<Tanken> tankungen = ((List<Auto>) this.select(FROM + "Auto" + WHERE
				+ "id = " + auto.getId())).get(0).getTankfuellungen();
		 
		for (Tanken t : tankungen){
			tankfuellungen.add(t);
		}
	}

	public List<Tanken> getTankfuellungen(Settings settings) {
		try {
			giveTankfuellungByAuto(settings.getAktuellAuto());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tankfuellungen;
	}

	private Benutzer giveBenutzer(Benutzer benutzer)  {
		for (Benutzer b : getBenutzer()) {
			if (b.getName().equals(benutzer.getName())
					&& b.getPasswort().equals(benutzer.getPasswort())) {
				return b;
			}
		}

		return null;
	}

	public Benutzer getBenutzer(Benutzer benutzer) {
		
		return giveBenutzer(benutzer);
		
	}

	private void giveBenutzer() throws Exception {
		String query = FROM + "Benutzer";

		this.allBenutzer = (List<Benutzer>) this.select(query);

	}

	public List<Benutzer> getBenutzer() {
		if (this.allBenutzer.isEmpty()) {
			try {
				giveBenutzer();
			} catch (Exception e) {
				System.out.println("Datenbank nicht erreichbar");
			}
		}

		return this.allBenutzer;
	}
}
