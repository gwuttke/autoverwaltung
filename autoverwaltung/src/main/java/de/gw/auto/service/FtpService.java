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

	public void getCurrentVersion() {
		if (versionen.isCurrentversion() == false) {
			downloadCurrentversion();
		}
	}

	private void downloadCurrentversion() {
		Version current = versionen.getCurrentVersion();
		download(versionen.getDownloadString(current));
	}
}
