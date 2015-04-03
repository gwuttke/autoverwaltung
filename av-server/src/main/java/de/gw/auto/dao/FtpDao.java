package de.gw.auto.dao;


public class FtpDao {

	Ftp ftp = new Ftp();
	public void download(String versionString){
		ftp.download(versionString);
	}
	 
}
