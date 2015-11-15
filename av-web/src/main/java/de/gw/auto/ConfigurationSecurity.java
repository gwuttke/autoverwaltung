package de.gw.auto;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.gw.auto.view.ViewName;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/", "/wro/**", "/" + ViewName.REGISTER,
						"/" + ViewName.REGISTRATION, "/login").permitAll()
				.anyRequest().fullyAuthenticated()
				.and().formLogin().loginPage("/login")
				.failureUrl("/login?error")
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
	}

}