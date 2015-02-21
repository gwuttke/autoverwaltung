package de.gw.auto;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.net.ftp.parser.EnterpriseUnixFTPEntryParser;
import org.apache.log4j.Logger;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
import de.gw.auto.gui.LogIn;
import de.gw.auto.gui.Willkommen;
import de.gw.auto.hibernate.example.User;
import de.gw.auto.repository.UserRepository;

/**
 * @author 
 *         http://www.patrick-gotthard.de/einfuehrung-in-spring-data-jpa-mit-hibernate
 * @author angepasst Georg
 **/
@ComponentScan("de.gw.auto")
@EnableAutoConfiguration
@EntityScan("de.gw.auto.domain")
@EnableJpaRepositories(basePackages = "de.gw.auto.repository")
@PropertySource("classpath:application.properies")
@EnableTransactionManagement
public class Application {
	
	private static Logger LOGGER =Logger.getLogger(Application.class);

	public static void main(final String[] args) {
		
		new Willkommen().show();
		
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		
		for(String s : ctx.getBeanDefinitionNames()){
			System.out.println(s);
		}
		
		 ctx.getBean(LogIn.class).show();;
		
		UserRepository ur = ctx.getBean(UserRepository.class);

		if (ur.findOne(1) == null) {
			final User user = new User();
			user.test();
		}
	}
}
