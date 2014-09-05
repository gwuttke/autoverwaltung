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
	
	public static Info getAVGVerbrauchPro100(Info gefahrenKm, Info anzahlLiter){
		Info info = new Info(Constans.VERBRAUCH_AUF_100_KM);
		if (gefahrenKm.getDiesesJahr().max(BigDecimal.ZERO) != BigDecimal.ZERO){
			info.setDiesesJahr(anzahlLiter.getDiesesJahr().divide(gefahrenKm.getDiesesJahr()).multiply(new BigDecimal(100)));
		}
		if (gefahrenKm.getVorjahr().max(BigDecimal.ZERO) != BigDecimal.ZERO){
			info.setVorjahr(anzahlLiter.getVorjahr().divide(gefahrenKm.getVorjahr()).multiply(new BigDecimal(100)));			
		}
		if (gefahrenKm.getVorjahr().max(BigDecimal.ZERO) != BigDecimal.ZERO){
			info.setGesammt(anzahlLiter.getGesammt().divide(gefahrenKm.getGesammt()).multiply(new BigDecimal(100)));	
		}
		return info;
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
	
	public static Double getKosten(Double liter, Double preisProLiter){
		return liter * preisProLiter;
	}
	
	public static Double getPreisProLiter(Double liter, Double kosten){
		return kosten / liter;
	}
	
	public static Double getLiter(Double kosten, Double preisProLiter){
		return kosten / preisProLiter;
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
