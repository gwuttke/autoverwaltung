package de.gw.auto;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * 
 *
 */
// @org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
@Service
public class ExportSchema {

	private static final String PACKAGENAMES = "de.gw.auto.domain";

	@Autowired
	private Environment env;

	private final static String packageName = "hibernate.export.packageNames";

	private final static String dialectString = "spring.jpa.database-platform";

	private final static String hbm2ddl = "hibernate.export.ddl-auto";

	private final String directoryString = "src//main//resources//db//migration//";

	private Configuration cfg;

	public ExportSchema() {

	}

	/**
	 * Main Method to generate the ddl-sql File for the DBMS
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ExportSchema gen = new ExportSchema();
		//gen.inputConfig();
		//gen.generate(Dialect.POSGRESQL);
		// gen.generate(Dialect.ORACLE);
		// gen.generate(Dialect.HSQL);
		// gen.generate(null);
	}

	/**
	 * Method that input Configuration into the Object Configuration
	 * 
	 * @throws Exception
	 */
	public void inputConfig() throws Exception {

		cfg = new Configuration();
		if (env != null) {
			cfg.setProperty("hibernate.hbm2ddl.auto", env.getProperty(hbm2ddl));
			for (Class<?> clazz : getClasses(env.getProperty(packageName))) {
				cfg.addAnnotatedClass(clazz);
			}
		} else {
			cfg.setProperty("hibernate.hbm2ddl.auto", "create");

			for (Class<?> clazz : getClasses(PACKAGENAMES)) {
				cfg.addAnnotatedClass(clazz);
			}
		}

	}

	/**
	 * Method that generate the File Name
	 * 
	 * @return String
	 */
	private String generateName() {

		StringBuilder sb = new StringBuilder();

		sb.append(directoryString)
				.append("ddl")
				.append('_')
				.append(cfg.getProperty("hibernate.dialect").toLowerCase())
				.append('_')
				.append(cfg.getProperty("hibernate.hbm2ddl.auto").toLowerCase())
				.append(".sql");
		return sb.toString().replaceAll(":", "_");
	}

	/**
	 * Method that actually creates the file.
	 * 
	 * @param dbDialect
	 *            to use
	 */
	public void generate(Dialect dialect) {
		if (dialect == null) {
			cfg.setProperty("hibernate.dialect", env.getProperty(dialectString));
		} else {
			cfg.setProperty("hibernate.dialect", dialect.getDialectClass());
		}
		File f = new File(directoryString);
		if (!f.exists()) {
			f.mkdirs();
		}

		SchemaExport export = new SchemaExport(cfg);
		export.setDelimiter(";");
		export.setOutputFile(generateName());
		export.execute(true, false, false, false);
	}

	/**
	 * Utility method used to fetch Class list based on a package name.
	 * 
	 * @param packageName
	 *            (should be the package containing your annotated beans).
	 */
	private List<Class<?>> getClasses(String packageName) throws Exception {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		File directory = null;
		try {
			ClassLoader cld = Thread.currentThread().getContextClassLoader();
			if (cld == null) {
				throw new ClassNotFoundException("Can't get class loader.");
			}
			String path = packageName.replace('.', '/');
			URL resource = cld.getResource(path);
			if (resource == null) {
				throw new ClassNotFoundException("No resource for " + path);
			}
			directory = new File(resource.getFile());
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(packageName + " (" + directory
					+ ") does not appear to be a valid package");
		}
		if (directory.exists()) {
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				if (files[i].endsWith(".class")) {
					// removes the .class extension
					classes.add(Class.forName(packageName + '.'
							+ files[i].substring(0, files[i].length() - 6)));
				}
			}
		} else {
			throw new ClassNotFoundException(packageName
					+ " is not a valid package");
		}

		return classes;
	}

}
