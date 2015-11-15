package de.gw.auto.view.model;

import java.util.Comparator;
import java.util.Date;

import de.gw.auto.domain.Tankfuellung;

public class TankenModel extends Tankfuellung{
	private String cssStyleRow;

	public String getCssStyleRow() {
		if (getTank() == null) {
			cssStyleRow = CssStyles.CSS_KEINE_ANGABE;
			return cssStyleRow;
		}
		switch (getTank().getId()) {
			case 1:
				cssStyleRow = CssStyles.CSS_EIN_VIRTEL;
				break;
			case 2:
				cssStyleRow = CssStyles.CSS_HALB_VOLL;
				break;
			case 3:
				cssStyleRow = CssStyles.CSS_DREI_VIRTEL;
				break;
			case 4:
				cssStyleRow = CssStyles.CSS_VOLL;
				break;
			default:
				cssStyleRow = CssStyles.CSS_KEINE_ANGABE;
				break;
		}
		return cssStyleRow;
	}
	
	public TankenModel(Tankfuellung t) {
		super(t);
	}
	
}
