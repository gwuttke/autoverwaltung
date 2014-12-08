package de.gw.auto;

import java.io.File;

import org.jfree.io.FileUtilities;

import de.gw.auto.domain.Version;
import de.gw.auto.service.Versionierungsservice;

public class Constans {

	private static final String MAX = "Maximal ";
	private static final String MIN = "Minimal ";
	private static final String DURCHSCHNITT = "Durchschnitt";
	private static final String PREIS = "Preis";
	private static final String ANZAHL = "Anzahl";
	private static final String LITER = "Liter";
	private static final String KOSTEN = "Kosten";
	private static final String GESAMT = "Gesamt ";
	public static final Version PROGRAMM_VERSION = new Version(12,
			Versionierungsservice.DESKTOP);
	public static final String MAX_PREIS = MAX + PREIS;
	public static final String MIN_PREIS = MIN + PREIS;
	public static final String ANZAHL_LITER = ANZAHL + LITER;
	public static final String AVG_Preis = DURCHSCHNITT + PREIS;
	public static final String GEFAHRENE_KM = "Gefahrene Km";
	public static final String TANKEN = "Tankungen ";
	public static final String TANKEN_KOSTEN = TANKEN + KOSTEN;
	public static final String SONSTIGE_AUSGABEN = "Sonstige Ausgaben ";
	public static final String SONSTIGEAUSGABEN_KOSTEN = SONSTIGE_AUSGABEN
			+ KOSTEN;
	public static final String GESAMT_KOSTEN = GESAMT + KOSTEN;
	public static final String VERBRAUCH_AUF_100_KM = DURCHSCHNITT
			+ "Verbrauch auf 100 Km";
	protected static final String HINZUFUEGEN = "hinzufügen";
	public static final String CLOSE_WINDOW = "Fenster Schliessen";
	public static final String ROOT_DIRECTORY = System.getProperty("user.dir") + File.separator;

}
