package de.gw.auto;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import de.gw.auto.repository.UserRepository;
import de.gw.auto.service.I_BenutzerService;
import de.gw.auto.service.implementation.BenutzerService;
import de.gw.auto.view.ViewName;

@Configuration
@EnableWebMvcSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private I_BenutzerService benutzerService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/css**", "/" + ViewName.REGISTER,
						"/" + ViewName.REGISTRATION, "/"+ViewName.LOGIN).permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/").permitAll()
				.and().logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(benutzerService);
	}
}