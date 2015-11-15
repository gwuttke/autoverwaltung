package de.gw.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ch.qos.logback.classic.joran.action.ConfigurationAction;
import de.gw.auto.view.ViewName;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableTransactionManagement
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ApplicationWeb extends Application {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/"+ViewName.LOGIN).setViewName(ViewName.LOGIN);
		registry.addRedirectViewController("/", ViewName.USER_MAIN_PAGE);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(!registry.hasMappingForPattern("/fonts/**")){
			registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/assert/bootstrap/3_3_5/fonts/");
		}
	}
	
	@Bean
	public ConfigurationSecurity configurationSecurity(){
		return new ConfigurationSecurity();
	}
	
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ApplicationWeb.class, args);
        
        /*
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        */
    }
    
    
    
    
}
