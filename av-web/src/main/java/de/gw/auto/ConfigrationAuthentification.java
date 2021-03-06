package de.gw.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

import de.gw.auto.service.I_BenutzerService;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class ConfigrationAuthentification extends GlobalAuthenticationConfigurerAdapter{
	
	@Autowired
	private I_BenutzerService benutzerService;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(benutzerService);
	}
	
}
