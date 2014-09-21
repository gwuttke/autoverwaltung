package de.gw.auto.dao;

import de.gw.auto.domain.Ftp;

public class FtpDao {

	Ftp ftp = new Ftp();
	public void download(String versionString){
		ftp.download(versionString);
	}
	 
}
