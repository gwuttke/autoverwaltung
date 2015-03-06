package de.gw.auto.domain;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import de.gw.auto.Constans;

public class Ftp {
	private static final int BUFFER_SIZE = 4096;

	private final String server = "192.168.178.53";
	private final String user = "auto";
	private final String pass = "123auto321";
	private final String verzeichnis = "";

	public String getServer() {
		return server;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public String getVerzeichnis() {
		return verzeichnis;
	}

	public void download(String versionString) {
		FTPClient ftpClient = new FTPClient();

		try {
			ftpClient.connect(server);
			ftpClient.login(user, pass);
			ftpClient.changeWorkingDirectory(verzeichnis);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			String remoteFile2 = versionString;
			File downloadFile2 = new File(Constans.ROOT_DIRECTORY
					+ versionString);
			OutputStream outputStream2 = new BufferedOutputStream(
					new FileOutputStream(downloadFile2));
			InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
			byte[] bytesArray = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(bytesArray)) != -1) {
				outputStream2.write(bytesArray, 0, bytesRead);
			}
			

			boolean success = ftpClient.completePendingCommand();
			if (success) {
				System.out
						.println("new Version has been downloaded successfully.");
				ProcessBuilder pb = new ProcessBuilder(new String[]{"javaw","-jar",downloadFile2.getAbsolutePath()});
				pb.start();
				
			}
			outputStream2.close();
			inputStream.close();

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
