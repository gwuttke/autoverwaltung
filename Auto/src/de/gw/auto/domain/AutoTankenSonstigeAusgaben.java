package de.gw.auto.domain;

public class AutoTankenSonstigeAusgaben {

	private Auto auto;
	private Tanken tanken;
	private SonstigeAusgaben sonstigeAusgaben;

	public AutoTankenSonstigeAusgaben(Auto auto, Tanken tanken,
			SonstigeAusgaben sonstigeAusgaben) {
		super();
		this.auto = auto;
		this.tanken = tanken;
		this.sonstigeAusgaben = sonstigeAusgaben;
	}

	public Auto getAuto() {
		return auto;
	}

	public Tanken getTanken() {
		return tanken;
	}

	public SonstigeAusgaben getSonstigeAusgaben() {
		return sonstigeAusgaben;
	}
}
