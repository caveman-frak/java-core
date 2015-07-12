package uk.co.bluegecko.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;


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

}
