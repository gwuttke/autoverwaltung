package de.gw.auto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gw.auto.Constans;
import de.gw.auto.domain.Version;
import de.gw.auto.hibernate.DatenAbrufen;
import de.gw.auto.hibernate.UpdateDaten;
import de.gw.auto.repository.VersionenRepository;

@Service
public class VersionDao {
	@Autowired
	private VersionenRepository versionenRepository;

	public VersionDao() {
		super();
	}

	public void add(Version version) {
		versionenRepository.save(version);
	}

	public Version get(String plattform) {
		return versionenRepository.findByPlattform(plattform);
		
	}

	public Version getCurrentversion() {
		return get(Constans.PROGRAMM_VERSION.getPlattform());
		
	}

	public static String getDownloadFileString(Version version) {
		StringBuilder sbVersion = new StringBuilder();
		StringBuilder sbNummer = new StringBuilder();
		char[] cNummer = String.valueOf(version.getNummer()).toCharArray();
		for (char c : cNummer) {
			sbNummer.append(c).append("_");
		}
		sbNummer.deleteCharAt(sbNummer.length() - 1);

		sbVersion.append(version.getPlattform()).append("_")
				.append(sbNummer.toString()).append(".jar");
		return sbVersion.toString();
	}

	public static String getVersionString(Version version) {
		StringBuilder sb = new StringBuilder();
		sb.append("Version: ").append(getVersionToString(version));

		return sb.toString();
	}

	private static String getVersionToString(Version version) {
		StringBuilder sbVersion = new StringBuilder();
		StringBuilder sbNummer = new StringBuilder();
		char[] cNummer = String.valueOf(version.getNummer()).toCharArray();
		for (char c : cNummer) {
			sbNummer.append(c).append(".");
		}
		sbNummer.deleteCharAt(sbNummer.length() - 1);

		sbVersion.append(version.getPlattform()).append("_")
				.append(sbNummer.toString());
		return sbVersion.toString();
	}

	public Boolean isCurrent() {
		final Version programm = Constans.PROGRAMM_VERSION;
		final Version current = getCurrentversion();
		if (current.getPlattform().equals(programm.getPlattform())) {
			if (current.getNummer() == programm.getNummer()) {
				return true;
			}
		}
		return false;
	}
}
