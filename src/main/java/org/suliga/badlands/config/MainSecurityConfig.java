package org.suliga.badlands.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

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
    	auth.jdbcAuthentication()
    		.dataSource(dataSource)
    		.passwordEncoder(new BCryptPasswordEncoder());
    }
    
    /*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().fullyAuthenticated()
				.and()
			.formLogin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()
				.userDnPatterns("uid={0},ou=people")
				.groupSearchBase("ou=groups")
				.contextSource(contextSource())
				.passwordCompare()
					.passwordEncoder(new LdapShaPasswordEncoder())
					.passwordAttribute("userPassword");
	}

	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		return  new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389/"), "dc=springframework,dc=org");
	}
	*/	
}


