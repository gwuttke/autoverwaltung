package de.gw.auto;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 
 *         http://www.patrick-gotthard.de/einfuehrung-in-spring-data-jpa-mit-hibernate
 * @author angepasst Georg
 **/
@EnableAutoConfiguration
@ComponentScan(basePackages="de.gw.auto")
@EntityScan("de.gw.auto.domain")
@EnableJpaRepositories("de.gw.auto.repository")

public abstract class Application extends  WebMvcConfigurerAdapter{
	
	   @Bean 
	    public HibernateExceptionTranslator hibernateExceptionTranslator(){ 
	      return new HibernateExceptionTranslator(); 
	    }
}
