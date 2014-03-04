package domain;

public class Texte {
	public static class Form {
		public static class Label {
			public final static String LAND = "Land";
			public final static String ORT = "Ort";
			public final static String DATUM = "Datum:";
		}

		public static class Button {
			public final static String BUTTON_HINZUFUEGEN = "Hinzufügen";
			public final static String BUTTON_ABBRUCH = "Abbruch";
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
