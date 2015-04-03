package de.gw.auto;

import java.text.SimpleDateFormat;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 
 *         http://www.patrick-gotthard.de/einfuehrung-in-spring-data-jpa-mit-hibernate
 * @author angepasst Georg
 **/
@EnableAutoConfiguration
@ComponentScan(basePackages="de.gw.auto")
@EntityScan("de.gw.auto.domain")
@EnableJpaRepositories("de.gw.auto.repository")
@PropertySource("classpath:application.properties")

public abstract class Application extends JFrame{
	
	   @Bean 
	    public HibernateExceptionTranslator hibernateExceptionTranslator(){ 
	      return new HibernateExceptionTranslator(); 
	    }
	 /*  
	   @Bean
	   public Jackson2ObjectMapperFactoryBean jacksonBuilder() {
	       Jackson2ObjectMapperFactoryBean builder = new Jackson2ObjectMapperFactoryBean();
	       builder.setIndentOutput(true); 
	       builder.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	       return builder;
	   }
	*/
}
