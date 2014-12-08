package de.gw.auto.service;

import de.gw.auto.Constans;
import de.gw.auto.dao.FtpDao;
import de.gw.auto.domain.Version;

public class FtpService {

	FtpDao ftp = new FtpDao();
	Versionierungsservice versionen = new Versionierungsservice();

	private void download(String versionString) {
		ftp.download(versionString);
	}

	public boolean downloadCurrentVersion() {
		if (versionen.isCurrentversion() == false) {
			downloadCurrentversion();
			Constans.PROGRAMM_VERSION.newVersion(versionen.getCurrentVersion());
			return true;
		}
		return false;
	}
	
	private void downloadCurrentversion() {
		download(versionen.getDownloadString(versionen.getCurrentVersion()));
	}
}
