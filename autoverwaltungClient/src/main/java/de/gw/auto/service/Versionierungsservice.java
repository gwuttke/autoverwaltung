package de.gw.auto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.gw.auto.dao.VersionDao;
import de.gw.auto.domain.Version;
@Controller
public class Versionierungsservice {

	@Autowired
	private final VersionDao versionen = new VersionDao();
	public static final String DESKTOP = "Desktop";

	public Version get(final String plattform) {
		return versionen.get(plattform);
	}

	public void save(final Version version) {
		versionen.add(version);
	}
	
	public Version getCurrentVersion(){
		return versionen.getCurrentversion();
	}
	
	public static String getCurrentVersionString(){
		return getVersionString(new Versionierungsservice().getCurrentVersion());
	}
	
	public static String getVersionString(Version version){
		return VersionDao.getVersionString(version);
	}
	public String getDownloadString(Version version){
		return VersionDao.getDownloadFileString(version);
	}	
	
	public boolean isCurrentversion(){
		return versionen.isCurrent();
	}
}
