package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import de.gw.auto.Constans;
import de.gw.auto.domain.Version;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.hibernate.UpdateDaten;

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
		this.versionen.add(version);
		new UpdateDaten().addVersion(version);
	}

	public Version get(String plattform) {
		for (Version v : versionen) {
			if (v.getPlattform().equals(plattform)) {
				return v;
			}
		}
		return null;
	}
	
	
	
	public Version getCurrentversion(){
		return get(Constans.PROGRAMM_VERSION.getPlattform());
	}
	
	public static String getDownloadFileString(Version version){
		StringBuilder sbVersion = new StringBuilder();
		StringBuilder sbNummer = new StringBuilder();
		char[] cNummer = String.valueOf(version.getNummer()).toCharArray();
		for (char c : cNummer) {
			sbNummer.append(c).append("_");
		}
		sbNummer.deleteCharAt(sbNummer.length() -1);

		sbVersion.append(version.getPlattform()).append("_")
				.append(sbNummer.toString()).append(".jar");
		return sbVersion.toString();
	}

	public static String getVersionString(Version version) {
		StringBuilder sbVersion = new StringBuilder();
		StringBuilder sbNummer = new StringBuilder();
		char[] cNummer = String.valueOf(version.getNummer()).toCharArray();
		for (char c : cNummer) {
			sbNummer.append(c).append(".");
		}
		sbNummer.deleteCharAt(sbNummer.length() -1);

		sbVersion.append(version.getPlattform()).append("_")
				.append(sbNummer.toString());
		return sbVersion.toString();
	}
	
	public Boolean isCurrent(){
		final Version programm = Constans.PROGRAMM_VERSION;
		final Version current = getCurrentversion();
		if(current.getPlattform().equals(programm.getPlattform())){
			if (current.getNummer() == programm.getNummer()){
				return true;
			}
		}
		return false;
	}
}
