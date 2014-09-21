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
import de.gw.auto.gui.Willkommen;

public class Ftp {
	private static final int BUFFER_SIZE = 4096 ;
	
	private  final String server = "192.168.178.21";
	private  final String user = "ftpuser";
	private  final String pass = "123";
	private  final String verzeichnis = "Auto\\";

public void download(String versionString){
	FTPClient ftpClient = new FTPClient();
	
	try {
		ftpClient.connect(server);
		ftpClient.login(user, pass);
		ftpClient.changeWorkingDirectory("Auto");
		ftpClient.enterLocalPassiveMode();
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	
	String remoteFile2 = versionString;
     File downloadFile2 = new File(Constans.ROOT_DIRECTORY + versionString);                
     OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
     InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
     byte[] bytesArray = new byte[4096];
     int bytesRead = -1;
     while ((bytesRead = inputStream.read(bytesArray)) != -1) {
         outputStream2.write(bytesArray, 0, bytesRead);
     }

    boolean success = ftpClient.completePendingCommand();
     if (success) {
         System.out.println("File #2 has been downloaded successfully.");
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

	public void download2(String versionString) {
		long startTime = System.currentTimeMillis() ;
		String ftpUrl = "ftp:";//**username**:**password**@filePath ;
		
		String savePath = Constans.ROOT_DIRECTORY + versionString;
		ftpUrl = String.format(ftpUrl, user, pass, server);
		System.out.println("Connecting to FTP server") ;

		try{
			URL url = new URL(ftpUrl) ;
			URLConnection conn = url.openConnection() ;
			InputStream inputStream = conn.getInputStream() ;
			long filesize = conn.getContentLength() ;
			System.out.println("Size of the file to download in kb is:-" + filesize/1024 ) ;

			FileOutputStream outputStream = new FileOutputStream(savePath) ;

			byte[] buffer = new byte[BUFFER_SIZE] ;
			int bytesRead = -1 ;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead) ;
			}
			long endTime = System.currentTimeMillis() ;
			System.out.println("File downloaded") ;
			System.out.println("Download time in sec. is:-" + (endTime-startTime)/1000)  ;
			outputStream.close() ;
			inputStream.close() ;
		}
		catch (IOException ex){
			ex.printStackTrace() ;
		}


//Read more: http://mrbool.com/java-ftp-how-to-download-file-with-java/29831#ixzz3DyFemWKa

	}
	
	public void download3(String versionString) {

		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server);
			ftpClient.login(user, pass);
			ftpClient.changeWorkingDirectory("/home/ftpuser/Auto");
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// APPROACH #1: using retrieveFile(String, OutputStream)
			String remoteFile1 = Constans.ROOT_DIRECTORY + versionString;
			File downloadFile1 = new File(versionString);
			OutputStream outputStream1 = new BufferedOutputStream(
					new FileOutputStream(downloadFile1));
			boolean success = ftpClient
					.retrieveFile(remoteFile1, outputStream1);
			outputStream1.close();

			if (success) {
				System.out.println("File has been downloaded successfully.");
			}

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
