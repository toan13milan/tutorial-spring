package com.example.demo.Config;

import javax.sql.DataSource;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.demo.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public BCryptPasswordEncoder passWordEncoder() {
		BCryptPasswordEncoder cryptPassEncoder = new BCryptPasswordEncoder();
		return cryptPassEncoder;
	}
	@Autowired
	public void confiureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passWordEncoder());
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// not request login
		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
		//request role ROle_user and role_admin
		// redirect login
		http.authorizeRequests().antMatchers("/userInfor").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')");
		
		// only role admin
		http.authorizeRequests().antMatchers("/admin","/getProduct", "/editProduct", "/history", "/deleteProduct", "/addProduct").access("hasAnyRole('ROLE_ADMIN')");
		
		//while user has role user
		// page allow accessing with role admin
		//exception throws
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		//config login form
		http.authorizeRequests().and().formLogin()
				.loginProcessingUrl("/j_spring_security_check")
				.loginPage("/login")
				.defaultSuccessUrl("/welcome")
				.failureUrl("/login?error=true")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
		// config remember
		http.authorizeRequests().and()
			.rememberMe().tokenRepository(this.persistentTokenRepository())
			.tokenValiditySeconds(1*24*60*60);// 24h
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}

}
