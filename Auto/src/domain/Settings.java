package domain;

public class Settings {
	
	private static Auto  auto = null;
	private static Benutzer benutzer = null;

	public static Auto getAuto() {
		return auto;
	}

	public static void setAuto(Auto auto) {
		Settings.auto = auto;
	}
}
