package de.gw.auto;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationWro {
	/*
	private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance(
			"E, dd MMM yyyy HH:mm:ss z",
			TimeZone.getTimeZone("GMT"),
			Locale.US);

	
	@Autowired
	private CustomEncodingProcessor customEncodingProcessor;

	@Autowired
	private CustomWroXmlModelFactory wroModelFactory;
	
	@Autowired
	private WroProperty wroProperty;

	@Bean
	public ConfigurableWroFilter wroFilter() throws IOException {

		final Properties properties = wroFilterProperties();
		final CustomWroManagerFactory managerFactory = new CustomWroManagerFactory();
		managerFactory.setProperties(properties);
		final Map<String, ResourcePreProcessor> customPreProcessors = new HashMap<String, ResourcePreProcessor>();
		customPreProcessors.put("customEncoding", this.customEncodingProcessor);
		managerFactory.setCustomPreProcessors(customPreProcessors);
		managerFactory.setModelFactory(this.wroModelFactory);

		final ConfigurableWroFilter filter = new ConfigurableWroFilter();
		filter.setProperties(properties);
		filter.setWroManagerFactory(managerFactory);

		return filter;

	}

	private Properties wroFilterProperties() {
		final Properties properties = new Properties();
		properties.put("debug", wroProperty.isDebug());
		properties.put("encoding", wroProperty.getEncoding());
		properties.put("uriLocators", wroProperty.getUriLocators());
		//properties.put("postProcessors", this.wroPostProcessors);
		properties.put("preProcessors", wroProperty.getPreProcessors());
		/*
			properties.put("parallelPreprocessing", this.wroParallelPreprocessing);
			properties.put("postProcessors", this.wroPostProcessors);
			properties.put("preProcessors", this.wroPreProcessors);
			properties.put("resourceWatcherAsync", this.wroResourceWatcherAsync);
			properties.put("resourceWatcherUpdatePeriod", this.wroResourceWatcherUpdatePeriod);

		/
		properties.put("cacheUpdatePeriod", wroProperty.getCacheUpdatePeriod());
		properties.put("modelUpdatePeriod", wroProperty.getModelUpdatePeriod());

		final Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.YEAR, 1);
		properties.put("header", "Expires: " + DATE_FORMAT.format(cal.getTimeInMillis()) + " | cache-control: public, max-age=600");
		return properties;
	}
*/
}
