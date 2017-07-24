package org.suliga.badlands.config;

import java.util.Locale;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MainConfig extends WebMvcConfigurerAdapter {
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang"); // http://localhost:8080/i18n?lang=de
		return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	// Redirect http to https
	@Bean
	public EmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
	    final TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory() {
		    @Override
		    protected void postProcessContext(Context context) {
		        SecurityConstraint securityConstraint = new SecurityConstraint();
		        securityConstraint.setUserConstraint("CONFIDENTIAL");
		        SecurityCollection collection = new SecurityCollection();
		        collection.addPattern("/*");
		        securityConstraint.addCollection(collection);
		        context.addConstraint(securityConstraint);
		    }
		};
	    factory.addAdditionalTomcatConnectors(this.createConnection());
	    return factory;
	}

	private Connector createConnection() {
	    final String protocol = "org.apache.coyote.http11.Http11NioProtocol";
	    final Connector connector = new Connector(protocol);

	    connector.setScheme("http");
	    connector.setPort(8080);
	    connector.setRedirectPort(8443);
	    return connector;
	}	
}

