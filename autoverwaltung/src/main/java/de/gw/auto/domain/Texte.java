package de.gw.auto.domain;

public class Texte {
	public static class Form {
		public static class Label {
			public final static String LAND = "Land:";
			public final static String ORT = "Ort:";
			public final static String DATUM = "Datum:";
			public final static String TANKEN_KOSTEN_GES = "Kosten Gesammt:";
			public final static String TANKEN_KOSTEN_AKT_JAHR = "Kosten dieses Jahr:";
			public final static String TANKEN_KOSTEN_LET_JAHR = "Kosten letztes Jahr:";
			public final static String BENUTZER = "Benutzer";
			public static final String PASSWORT = "Passwort";
		}

		public static class Button {
			public final static String HINZUFUEGEN = "Hinzufügen";
			public final static String ABBRUCH = "Abbruch";
			public final static String BEENDEN = "Beenden";
		}

		public static class FensterTitel {
			public final static String ADD_TANKEN = "Tanken hinzufügen";
		}

		public static class AndereKomponennte {
			public final static String BITTE_AUSWAELEN = "bitte auswählen";
		}
	}

	public static class Tabelle {
		public final static String SPALTEN_NAME = "es gibt keine Spaltennamen";
		public final static String KEINE_DATEN = "es sind noch keine Daten vorhanden.";
	}

	public static class Error {
		public static class Titel {
			public final static String CONNECTION = "Keine Verbindung zur Datenbank";
			public final static String FALSCHE_EINGABE = "Falsche Eingabe";
		}
		

		public static class Text {
			public final static String CONNECTION_TEXT = "Es konnte keine Verbindung zur Datenbank hergestellt werden, bitte prüfen sie ob sie mit dem Internet verbunden sind.";
		}
	}
}
