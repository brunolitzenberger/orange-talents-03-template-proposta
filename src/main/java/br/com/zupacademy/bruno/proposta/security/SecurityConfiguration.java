package br.com.zupacademy.bruno.proposta.security;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
@EnableWebSecurity
@Profile("prod")
public class SecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter{
	
	 @Autowired
	    public void configureGlobal(
	      AuthenticationManagerBuilder auth) throws Exception {
	 
	        KeycloakAuthenticationProvider keycloakAuthenticationProvider
	          = keycloakAuthenticationProvider();
	        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(
	          new SimpleAuthorityMapper());
	        auth.authenticationProvider(keycloakAuthenticationProvider);
	    }

	    @Bean
	    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
	        return new KeycloakSpringBootConfigResolver();
	    }

	    @Bean
	    @Override
	    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
	        return new RegisterSessionAuthenticationStrategy(
	          new SessionRegistryImpl());
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        super.configure(http);
	        http.
	        csrf().disable()
	        .authorizeRequests()
	          .antMatchers("/actuator/**").permitAll()	          
	          .anyRequest()
	          .authenticated();
	    }
}
