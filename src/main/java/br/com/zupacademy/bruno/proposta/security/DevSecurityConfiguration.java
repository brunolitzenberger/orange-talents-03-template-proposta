package br.com.zupacademy.bruno.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().headers().frameOptions().sameOrigin();
	}

}
