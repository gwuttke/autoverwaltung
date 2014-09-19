package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.List;

import de.gw.auto.domain.Version;
import de.gw.auto.hibernate.DatenAbrufen;

public class VersionDao {

	private List<Version> versionen = new ArrayList<Version>();

	public VersionDao() {
		super();
		load();
	}

	public void load() {
		this.versionen = new DatenAbrufen().getVersionen();
	}

	public void add(Version version) {
		versionen.add(version);
	}

	public Version get(String plattform) {
		for (Version v : versionen) {
			if (v.getPlattform() == plattform) {
				return v;
			}
		}
		return null;
	}

	public String getVersionString(Version version) {
		StringBuilder sbVersion = new StringBuilder();
		StringBuilder sbNummer = new StringBuilder();
		char[] cNummer = String.valueOf(version.getNummer()).toCharArray();
		for (char c : cNummer) {
			sbNummer.append(c).append(".");
		}
		sbNummer.deleteCharAt(sbNummer.length());

		sbVersion.append(version.getPlattform()).append("_")
				.append(sbNummer.toString());
		return sbVersion.toString();
	}
}
