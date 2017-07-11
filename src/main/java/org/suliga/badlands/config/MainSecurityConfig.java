package org.suliga.badlands.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MainSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity hs) throws Exception {
        hs
            .authorizeRequests()
                .antMatchers("/", "/index", "/home").permitAll()
                // currently needed to allow login.html to access login.css
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()               
            .logout()
                .permitAll();
        
        // add this line to use H2 web console
    	hs.headers().frameOptions().disable();
   	 	hs.csrf().ignoringAntMatchers("/h2-console/**", "/console/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("user").roles("USER").and()
                .withUser("tom").password("tom").roles("USER", "ADMIN", "DBA").and()
    	  		.withUser("admin").password("Secret1").roles("ADMIN").and()
    			.withUser("dba").password("Secret1").roles("DBA");
    }
}


