package de.gw.auto.wro;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import de.gw.auto.property.WroProperty;
import ro.isdc.wro.model.factory.XmlModelFactory;

/**
 * when deploying to weblogic, it packages the war classes/resources to a zip (like a jar).
 * so, files cannot be references anymore by "resource.getFile()".
 * this class ensures that wro accesses its wro xml only as inputstream.
 */
@Component
public class CustomWroXmlModelFactory extends XmlModelFactory {


	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private WroProperty wroProperty;

	@Override
	protected java.io.InputStream getModelResourceAsStream() throws java.io.IOException {
		final InputStream inputStream = this.applicationContext.getResource(this.wroProperty.getFileLocation()).getInputStream();
		return inputStream;
	}

}
