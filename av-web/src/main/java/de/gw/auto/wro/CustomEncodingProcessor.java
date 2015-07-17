package de.gw.auto.wro;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.SupportedResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;

@Component
@SupportedResourceType(ResourceType.JS)
public class CustomEncodingProcessor implements ResourcePreProcessor {

	private static final int BYTE_SIZE = 4096;

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void process(final Resource resource, final Reader reader, final Writer writer) throws IOException {
		final UniversalDetector detector = new UniversalDetector(null);

		InputStream inputStream = this.applicationContext.getResource(resource.getUri()).getInputStream();

		final byte[] buf = new byte[BYTE_SIZE];
		int nread;
		while (((nread = inputStream.read(buf)) > 0) && !detector.isDone()) {
			detector.handleData(buf, 0, nread);
		}
		detector.dataEnd();

		final String encoding = detector.getDetectedCharset();

		inputStream.close();
		inputStream = this.applicationContext.getResource(resource.getUri()).getInputStream();

		if ("UTF-8".equals(encoding)) {
			final BOMInputStream bomInputStream = new BOMInputStream(inputStream);
			IOUtils.copy(bomInputStream, writer, encoding);
		}
		else {
			IOUtils.copy(reader, writer);
		}

		inputStream.close();
		reader.close();
		writer.close();
	}

}
