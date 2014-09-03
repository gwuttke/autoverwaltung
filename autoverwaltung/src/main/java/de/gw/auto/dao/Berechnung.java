package de.gw.auto.dao;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.gw.auto.Constans;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;

public class Berechnung {
	private double b;
	private Map<String, List<Info>> ausgabenBerechnungen = new HashMap<String, List<Info>>();
	
	
	public List<Info> getTankenInfos(TankenDao tDao, Settings setting) {
		if (ausgabenBerechnungen.size() == 0){
			addAusgabenBerechnungen(Constans.TANKEN, new TankenInfo(tDao, setting).getTankenInfos());
		}
		return ausgabenBerechnungen.get(Constans.TANKEN);
	}
	
	public void addAusgabenBerechnungen(String bezeichnung, List<Info> info){
		this.ausgabenBerechnungen.put(bezeichnung, info);
	}

	public String getRound(BigDecimal value, int nachkommata) {
		return round(value, nachkommata);

	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}
	
	public static int getInsgGefahreneKm(Auto auto){
		return auto.getKmAktuell() - auto.getKmKauf();
	}
	
	public static BigDecimal getVerbrachPro100Km(Tanken tanken, Tanken tankenVorher){
		if (tankenVorher == null){
			return BigDecimal.ZERO;
		}
		if (tanken.getTank()== tankenVorher.getTank()){
			BigDecimal gefahreneKm = new BigDecimal( getGefahreneKilometer(tankenVorher, tanken));
			return tanken.getLiter().divide(gefahreneKm).multiply(new BigDecimal(100));
		}
		return BigDecimal.ZERO;
	}
	
	public static int getGefahreneKilometer(Tanken vorherTanken, Tanken tanken){
		return tanken.getKmStand() - vorherTanken.getKmStand();
	}

	private String round(BigDecimal value, int nachkommaStelle) {
		DecimalFormat df = new DecimalFormat();
		switch (nachkommaStelle) {

		case 2:
			df = new DecimalFormat(",##0.00");
			return df.format(value);
		case 3:
			df = new DecimalFormat(",###0.000");
			return df.format(value);
		default:
			return "keine gültige eingabe";
		}

	}
	
	public static BigDecimal findAverage(Set<BigDecimal> bigDecimals){
		if (bigDecimals.isEmpty()){
			return BigDecimal.ZERO;
		}
		BigDecimal summe = BigDecimal.ZERO;
		
		for(BigDecimal bd : bigDecimals){
			summe.add(bd);
		}
		return summe.divide(new BigDecimal(bigDecimals.size()));
	}
	
	private int kmDifferenz(Tanken tVorher, Tanken tAktuell){
		return tAktuell.getKmStand() - tVorher.getKmStand();
	} 
	
}
