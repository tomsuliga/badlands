package org.suliga.badlands.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {  
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("greetings")
				.apiInfo(apiInfo())
				.select()
				.paths(PathSelectors.regex("/rest/*.*"))
				.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Badlands Swagger Title")
				.description("Badlands Swagger Desc")
				.version("2.0")
				.termsOfServiceUrl("http://termsofservice.com")
				.contact(new Contact("Tom Suliga", "http://www.tomsuliga.org", "tsuliga@tomsuliga.org"))
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/tomsuliga/badlands/master/LICENSE")		
				.build();
	}
	
/*	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
*/

}
