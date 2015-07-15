package uk.co.bluegecko.core;


import static uk.co.bluegecko.core.server.ServerConstants.BASE_PATH;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import uk.co.bluegecko.core.server.ServerConfig;


@SuppressWarnings( "javadoc" )
@SpringBootApplication
public class Application extends SpringBootServletInitializer
{

	public static void main( final String... args ) throws Exception
	{
		SpringApplication.run( Application.class, args );
	}

	@Override
	protected SpringApplicationBuilder configure( final SpringApplicationBuilder builder )
	{
		return builder.sources( Application.class );
	}

	@Bean
	public ServletRegistrationBean jerseyServlet()
	{
		final ServletRegistrationBean registration = new ServletRegistrationBean( new ServletContainer(), BASE_PATH + "/*" );
		registration.addInitParameter( ServletProperties.JAXRS_APPLICATION_CLASS, ServerConfig.class.getName() );
		return registration;
	}

}
