package de.gw.auto.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wro", ignoreUnknownFields = true)
public class WroProperty { 
	private boolean debug = false;

	private boolean gzipEnable = true;

	private boolean jmxEnabled = true;

	private String mbeanName = "wro";

	private String encoding = "ISO-8859-15";

	private int cacheUpdatePeriod = 100;

	private int modelUpdatePeriod = 100;

	private String fileLocation = "classpath:/wro.xml";

	private boolean ignoreMissingResources = false;

	private String uriLocators = "servletContext,classpath,uri";

	private String preProcessors = "less4j,customEncoding,cssImport,cssUrlRewriting,semicolonAppender";

	public WroProperty() {
		// TODO Auto-generated constructor stub
	}

	public boolean isGzipEnable() {
		return gzipEnable;
	}

	public void setGzipEnable(boolean gzipEnable) {
		this.gzipEnable = gzipEnable;
	}

	public boolean isJmxEnabled() {
		return jmxEnabled;
	}

	public void setJmxEnabled(boolean jmxEnabled) {
		this.jmxEnabled = jmxEnabled;
	}

	public String getMbeanName() {
		return mbeanName;
	}

	public void setMbeanName(String mbeanName) {
		this.mbeanName = mbeanName;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public boolean isIgnoreMissingResources() {
		return ignoreMissingResources;
	}

	public void setIgnoreMissingResources(boolean ignoreMissingResources) {
		this.ignoreMissingResources = ignoreMissingResources;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public int getCacheUpdatePeriod() {
		return cacheUpdatePeriod;
	}

	public void setCacheUpdatePeriod(int cacheUpdatePeriod) {
		this.cacheUpdatePeriod = cacheUpdatePeriod;
	}

	public int getModelUpdatePeriod() {
		return modelUpdatePeriod;
	}

	public void setModelUpdatePeriod(int modelUpdatePeriod) {
		this.modelUpdatePeriod = modelUpdatePeriod;
	}

	public String getUriLocators() {
		return uriLocators;
	}

	public void setUriLocators(String uriLocators) {
		this.uriLocators = uriLocators;
	}

	public String getPreProcessors() {
		return preProcessors;
	}

	public void setPreProcessors(String preProcessors) {
		this.preProcessors = preProcessors;
	}
	/*
	 * @Value("${wro.postProcessors}") private String wroPostProcessors;
	 * 
	 * @Value("${wro.parallelPreprocessing}") private String
	 * wroParallelPreprocessing;
	 * 
	 * @Value("${wro.resourceWatcherAsync}") private String
	 * wroResourceWatcherAsync;
	 * 
	 * @Value("${wro.resourceWatcherUpdatePeriod}") private String
	 * wroResourceWatcherUpdatePeriod;
	 */
}
