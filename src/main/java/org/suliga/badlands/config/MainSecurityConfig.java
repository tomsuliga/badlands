package org.suliga.badlands.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MainSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity hs) throws Exception {
        hs
            .authorizeRequests()
            
                .antMatchers("/", "/index", "/home", "/font", "/XXXrest/**").permitAll()
                .antMatchers("/rest/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_DBA")
                .antMatchers("/adduser").hasAnyAuthority("ROLE_ADMIN", "ROLE_DBA")
                .antMatchers("/listusers").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_DBA")
                // needed to allow login.html to access src/main/resources/static/resources/css/login.css
                .antMatchers("/resources/**").permitAll()
                
                .anyRequest().authenticated()
                //.and().csrf().and()
                .and()
            .httpBasic()
            	.and()
            .exceptionHandling()
            	.accessDeniedPage("/403")
            	.and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()             
            .logout()
                .permitAll();

        
        // add this line to use H2 web console
    	hs.headers().frameOptions().disable();
   	 	hs.csrf().ignoringAntMatchers("/h2-console/**", "/console/**", "/rest/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 /*       auth
            .inMemoryAuthentication()
                .withUser("user").password("user").roles("USER").and()
                .withUser("tom").password("tom").roles("USER", "ADMIN", "DBA").and()
    	  		.withUser("admin").password("Secret1").roles("ADMIN").and()
    			.withUser("dba").password("Secret1").roles("DBA"); */
    	auth.jdbcAuthentication()
    		.dataSource(dataSource)
    		.passwordEncoder(new BCryptPasswordEncoder());
    }
}


