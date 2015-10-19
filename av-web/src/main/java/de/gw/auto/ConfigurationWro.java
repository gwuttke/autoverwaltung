package de.gw.auto;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;

@Configuration
public class ConfigurationWro {
	private static final Logger log = LoggerFactory
			.getLogger(ConfigurationWro.class);

	private static final String[] OTHER_WRO_PROP = new String[] {
		ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS,
		ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS };
	
	@Bean
	FilterRegistrationBean webResourceOptimizer(Environment env) {
		FilterRegistrationBean fr = new FilterRegistrationBean();
		ConfigurableWroFilter filter = new ConfigurableWroFilter();
		Properties props = buildWroProperties(env);
		filter.setProperties(props);
		filter.setWroManagerFactory(new Wro4jCustomXmlModelManagerFactory(props));
		fr.setFilter(filter);
		fr.addUrlPatterns("/wro/*");
		return fr;
	}

	private Properties buildWroProperties(Environment env) {
		Properties prop = new Properties();
		for (ConfigConstants c : ConfigConstants.values()) {
			addProperty(env, prop, c.name());
		}
		for (String name : OTHER_WRO_PROP) {
			addProperty(env, prop, name);
		}
		log.debug("WRO4J properties {}", prop);
		return prop;
	}

	private void addProperty(Environment env, Properties to, String name) {
		String value = env.getProperty("wro." + name);
		if (value != null) {
			to.put(name, value);
		}
	}
	/*
	 * private static final FastDateFormat DATE_FORMAT =
	 * FastDateFormat.getInstance( "E, dd MMM yyyy HH:mm:ss z",
	 * TimeZone.getTimeZone("GMT"), Locale.US);
	 * 
	 * 
	 * @Autowired private CustomEncodingProcessor customEncodingProcessor;
	 * 
	 * @Autowired private CustomWroXmlModelFactory wroModelFactory;
	 * 
	 * @Autowired private WroProperty wroProperty;
	 * 
	 * @Bean public ConfigurableWroFilter wroFilter() throws IOException {
	 * 
	 * final Properties properties = wroFilterProperties(); final
	 * CustomWroManagerFactory managerFactory = new CustomWroManagerFactory();
	 * managerFactory.setProperties(properties); final Map<String,
	 * ResourcePreProcessor> customPreProcessors = new HashMap<String,
	 * ResourcePreProcessor>(); customPreProcessors.put("customEncoding",
	 * this.customEncodingProcessor);
	 * managerFactory.setCustomPreProcessors(customPreProcessors);
	 * managerFactory.setModelFactory(this.wroModelFactory);
	 * 
	 * final ConfigurableWroFilter filter = new ConfigurableWroFilter();
	 * filter.setProperties(properties);
	 * filter.setWroManagerFactory(managerFactory);
	 * 
	 * return filter;
	 * 
	 * }
	 * 
	 * private Properties wroFilterProperties() { final Properties properties =
	 * new Properties(); properties.put("debug", wroProperty.isDebug());
	 * properties.put("encoding", wroProperty.getEncoding());
	 * properties.put("uriLocators", wroProperty.getUriLocators());
	 * //properties.put("postProcessors", this.wroPostProcessors);
	 * properties.put("preProcessors", wroProperty.getPreProcessors()); /*
	 * properties.put("parallelPreprocessing", this.wroParallelPreprocessing);
	 * properties.put("postProcessors", this.wroPostProcessors);
	 * properties.put("preProcessors", this.wroPreProcessors);
	 * properties.put("resourceWatcherAsync", this.wroResourceWatcherAsync);
	 * properties.put("resourceWatcherUpdatePeriod",
	 * this.wroResourceWatcherUpdatePeriod);
	 * 
	 * / properties.put("cacheUpdatePeriod",
	 * wroProperty.getCacheUpdatePeriod()); properties.put("modelUpdatePeriod",
	 * wroProperty.getModelUpdatePeriod());
	 * 
	 * final Calendar cal = Calendar.getInstance(); cal.roll(Calendar.YEAR, 1);
	 * properties.put("header", "Expires: " +
	 * DATE_FORMAT.format(cal.getTimeInMillis()) +
	 * " | cache-control: public, max-age=600"); return properties; }
	 */
}
