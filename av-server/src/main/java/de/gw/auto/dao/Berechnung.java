package de.gw.auto.dao;

import java.awt.Component;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.Constans;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Tanken;

@Service
public class Berechnung {
	
	@Autowired
	TankenDao tankenDao;
	
	@Autowired
	TankenInfo tankenInfo;
	
	protected Berechnung(){
	}
	
	private double b;
	private Map<String, List<Info>> ausgabenBerechnungen = new HashMap<String, List<Info>>();


	public void addAusgabenBerechnungen(String bezeichnung, List<Info> info) {
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

	public static Font updateFont(Component comp, String compText, int fontStyle) {
		Font testFont = null;
		int size = 0;
		for (int i = 1; i < comp.getHeight(); i++) {
			testFont = new Font("Dialog", Font.BOLD, i);
			comp.setFont(testFont);
			if (comp.getFontMetrics(testFont).stringWidth(compText) > comp
					.getWidth())
				break;
			if (comp.getFontMetrics(testFont).getHeight() > comp.getHeight())
				break;
			size = (i - 2); // vermutlich n�tig, da ich die Insets nicht beachte
		}
		return new Font("Dialog", fontStyle, size);
	}

	public static int getInsgGefahreneKm(Auto auto) {
		return auto.getKmAktuell() - auto.getKmKauf();
	}

	public static Info getAVGVerbrauchPro100(Info gefahrenKm, Info anzahlLiter) {
		Info info = new Info(Constans.VERBRAUCH_AUF_100_KM);
		Double kmDJ = Double.valueOf(gefahrenKm.getDiesesJahr().toString());
		Double literDJ =Double.valueOf(anzahlLiter.getDiesesJahr().toString());
		Double kmLJ = Double.valueOf(gefahrenKm.getVorjahr().toString());
		Double literLJ =Double.valueOf(anzahlLiter.getVorjahr().toString());  
		Double kmG = Double.valueOf(gefahrenKm.getGesammt().toString());
		Double literG =Double.valueOf(anzahlLiter.getGesammt().toString());  
		
		if (gefahrenKm.getDiesesJahr().max(BigDecimal.ZERO) != BigDecimal.ZERO) {
			try {
			info.setDiesesJahr(getVerbrauchPro100Km(literDJ , kmDJ));
			} catch (ArithmeticException ae) {

			}
		}
		
		if (gefahrenKm.getVorjahr().max(BigDecimal.ZERO) != BigDecimal.ZERO) {
			try {
				info.setVorjahr(getVerbrauchPro100Km(literLJ , kmLJ));
			} catch (ArithmeticException ae) {

			}
		}
		if (gefahrenKm.getVorjahr().max(BigDecimal.ZERO) != BigDecimal.ZERO) {
			try {
				info.setGesammt(getVerbrauchPro100Km(literG , kmG));
			} catch (ArithmeticException ea) {
			}
		}
		return info;
	}
	
	private static BigDecimal getVerbrauchPro100Km(Double liter,Double km){
		return new BigDecimal(liter / km * 100);
	}

	public static BigDecimal getVerbrachPro100Km(Tanken tanken,
			Tanken tankenVorher) {
		if (tankenVorher == null) {
			return BigDecimal.ZERO;
		}
		if (tanken.getTank() == tankenVorher.getTank()) {
			BigDecimal gefahreneKm = new BigDecimal(getGefahreneKilometer(
					tankenVorher, tanken));
			Double liter =Double.valueOf(tanken.getLiter().toString());
			Double km = Double.valueOf(gefahreneKm.toString());
			return getVerbrauchPro100Km(liter, km);
		}
		return BigDecimal.ZERO;
	}

	public static int getGefahreneKilometer(Auto auto, Tanken tanken) {

		return tanken.getKmStand() - auto.getKmKauf();
	}

	public static int getGefahreneKilometer(Tanken vorherTanken, Tanken tanken) {

		return tanken.getKmStand() - vorherTanken.getKmStand();
	}

	public static Double getKosten(Double liter, Double preisProLiter) {
		return (liter * preisProLiter);
		
	}

	public static Double getPreisProLiter(Double liter, Double kosten) {
		return (kosten / liter);
	}

	public static Double getLiter(Double kosten, Double preisProLiter) {
		return (kosten / preisProLiter);
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
			return "keine g�ltige eingabe";
		}

	}

	public static BigDecimal findAverage(Set<BigDecimal> bigDecimals) {
		if (bigDecimals.isEmpty()) {
			return BigDecimal.ZERO;
		}
		BigDecimal summe = BigDecimal.ZERO;

		for (BigDecimal bd : bigDecimals) {
			summe.add(bd);
		}
		return summe.divide(new BigDecimal(bigDecimals.size()));
	}

	private int kmDifferenz(Tanken tVorher, Tanken tAktuell) {
		return tAktuell.getKmStand() - tVorher.getKmStand();
	}

}
