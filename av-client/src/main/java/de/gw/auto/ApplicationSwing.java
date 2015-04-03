package de.gw.auto;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import de.gw.auto.gui.LogIn;
import de.gw.auto.gui.Willkommen;

/**
 * @author 
 *         http://www.patrick-gotthard.de/einfuehrung-in-spring-data-jpa-mit-hibernate
 * @author angepasst Georg
 **/

public class ApplicationSwing extends Application{
	
	private static Logger LOGGER =Logger.getLogger(ApplicationSwing.class);

	public static void main(final String[] args) {
		
		new Willkommen().show();
		
		ApplicationContext ctx = SpringApplication.run(ApplicationSwing.class, args);
		
		 ctx.getBean(LogIn.class).show();
	}
}
