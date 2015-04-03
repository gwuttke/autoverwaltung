package de.gw.auto.dao;

import java.net.URL;

import javax.jnlp.DownloadService;
import javax.jnlp.DownloadServiceListener;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;

public class Update extends Ftp {
	
	private void update(){
		DownloadService ds; 

	    try { 
	        ds = (DownloadService)ServiceManager.lookup("javax.jnlp.DownloadService"); 
	    } catch (UnavailableServiceException e) { 
	        ds = null; 
	    } 

	    if (ds != null) { 

	        try { 
	            String ftpUrl = "ftp://%s:%s@%s/%s";
	            String version = VersionDao.getVersionString(new VersionDao().getCurrentversion());
	     
	            ftpUrl = String.format(ftpUrl, getUser(), getPass(), getServer(), getVerzeichnis());
	        	
	        	
	            // determine if a particular resource is cached
	            URL url = 
	                    new URL(ftpUrl); 
	            boolean cached = ds.isResourceCached(url, version); 
	            // remove the resource from the cache 
	            if (cached) { 
	                ds.removeResource(url, "1.0"); 
	            } 
	            // reload the resource into the cache 
	            DownloadServiceListener dsl = ds.getDefaultProgressWindow(); 
	            ds.loadResource(url, version, dsl); 
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	    } 
	}

}
