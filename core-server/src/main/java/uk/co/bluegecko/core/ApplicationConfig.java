package uk.co.bluegecko.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SuppressWarnings( "javadoc" )
@SpringBootApplication
public class ApplicationConfig extends SpringBootServletInitializer
{

	public static void main( final String... args ) throws Exception
	{
		SpringApplication.run( ApplicationConfig.class, args );
	}

	@Override
	protected SpringApplicationBuilder configure( final SpringApplicationBuilder builder )
	{
		return builder.sources( ApplicationConfig.class ).properties( "spring.jersey.type:filter" );
	}

}
