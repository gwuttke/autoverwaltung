package de.gw.auto.dao;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;

import de.gw.auto.Constans;
import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Info;
import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;

public class Berechnung {
	private double b;
	private Map<String, List<Info>> ausgabenBerechnungen = new HashMap<String, List<Info>>();

	public List<Info> getTankenInfos(TankenDao tDao, Settings setting) {
		if (ausgabenBerechnungen.size() == 0) {
			addAusgabenBerechnungen(Constans.TANKEN, new TankenInfo(tDao,
					setting).getTankenInfos());
		}
		return ausgabenBerechnungen.get(Constans.TANKEN);
	}

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
			size = (i - 2); // vermutlich nötig, da ich die Insets nicht beachte
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

	public static BigDecimal getKosten(Double liter, Double preisProLiter) {
		return new BigDecimal(liter * preisProLiter);
		
	}

	public static BigDecimal getPreisProLiter(Double liter, Double kosten) {
		return new BigDecimal (kosten / liter);
	}

	public static BigDecimal getLiter(Double kosten, Double preisProLiter) {
		return new BigDecimal(kosten / preisProLiter);
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
