package de.gw.auto.wro;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;

public class CustomWroManagerFactory extends ConfigurableWroManagerFactory {

	private Map<String, ResourcePreProcessor> customPreProcessors = new HashMap<String, ResourcePreProcessor>();

	private Properties properties = new Properties();

	public Map<String, ResourcePreProcessor> getCustomPreProcessors() {
		return this.customPreProcessors;
	}

	public Properties getProperties() {
		return this.properties;
	}

	public void setCustomPreProcessors(final Map<String, ResourcePreProcessor> customPreProcessors) {
		this.customPreProcessors = customPreProcessors;
	}

	public void setProperties(final Properties properties) {
		this.properties = properties;
	}

	@Override
	protected void contributePreProcessors(final Map<String, ResourcePreProcessor> map) {
		map.putAll(this.customPreProcessors);
	}

	@Override
	protected Properties newConfigProperties() {
		return this.properties;
	}

}
