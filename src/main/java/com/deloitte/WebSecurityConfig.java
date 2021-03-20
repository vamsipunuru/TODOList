package com.deloitte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/home","/tasks/**").permitAll().antMatchers("/welcome")
		.hasAnyRole("USER", "ADMIN").antMatchers("/getList").hasAnyRole("USER", "ADMIN")
		.antMatchers("/addtask").hasAnyRole("USER", "ADMIN")
		.antMatchers("/h2-console/**").permitAll().anyRequest().authenticated().and().formLogin()
		.loginPage("/login")		
		.usernameParameter("u").passwordParameter("p")				
		.permitAll()
		.failureUrl("/loginerror")
		.defaultSuccessUrl("/loginsuccess")
		.and()
		.logout().permitAll()
		.logoutSuccessUrl("/logoutsuccess")
		.and()
		.exceptionHandling().accessDeniedPage("/403");
		 http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.inMemoryAuthentication().withUser("User1").password("$2a$10$DGPGl5WLqZKLtBX07pri1.dIAIWqNZSPc68qh7oviBmllqS781t0q")
		.authorities("ROLE_USER").and().withUser("User2").password("$2a$10$2v45GzwnZ6X7k8UcRIby8ePXkvYzwrnMQ3baT5cw0WOiz9TKHUPVC")
		.authorities("ROLE_USER", "ROLE_ADMIN").and().withUser("User3").password("$2a$10$zgVsdGr50P2gI8E25pf/dOU85YKTQlu0zcIl0ZQxEiVW8HvLSGcuy")
		.authorities("ROLE_USER", "ROLE_ADMIN");
	}


}
