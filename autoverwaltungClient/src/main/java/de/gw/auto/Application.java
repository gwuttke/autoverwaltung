package de.gw.auto;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.net.ftp.parser.EnterpriseUnixFTPEntryParser;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.gw.auto.domain.Benutzer;
import de.gw.auto.hibernate.example.User;
import de.gw.auto.repository.UserRepository;

/**
 * @author 
 *         http://www.patrick-gotthard.de/einfuehrung-in-spring-data-jpa-mit-hibernate
 * @author angepasst Georg
 **/
@ComponentScan("de.gw.auto")
@EntityScan("de.gw.auto.domain")
@EnableJpaRepositories(basePackages="de.gw.auto.repository")
@PropertySource("classpath:application.properies")
@EnableTransactionManagement
public class Application {

	@Resource
	private Environment env;

	// Werte sind unter src/main/resources/application.properties
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_HIBERNATE_HB2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "application.entertyScanPackage";

	// @Value("#{hibernate.hbm2ddl.auto}")
	@Value("#{spring.jpa.hibernate.ddl-auto}")
	private static String hibernateHbm2ddl;

	@Value("#{application.entertyScanPackage}")
	private static String applicationEntertyScanPackage;

	public static void main(final String[] args) {

		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		SpringApplicationContext sac = new SpringApplicationContext();
		sac.setApplicationContext(ctx);
		final User user = new User();
		user.test();

	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		return dataSource;
	}

	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		// Automatically create tables for the entity classes. Don't do this in
		// production!
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setShowSql(true);
		return hibernateJpaVendorAdapter;
	}
/*
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory());

		return transactionManager;
	}
	*/
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean  entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean
				.setPackagesToScan(env
						.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		localContainerEntityManagerFactoryBean
				.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		localContainerEntityManagerFactoryBean
				.setJpaProperties(additionalProperties());
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	@Autowired
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		return properties;
	}
}
