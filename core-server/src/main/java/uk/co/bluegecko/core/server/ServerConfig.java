package uk.co.bluegecko.core.server;


import static uk.co.bluegecko.core.server.ServerConstants.PATH;
import static uk.co.bluegecko.core.server.ServerConstants.PORT;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@SuppressWarnings( "javadoc" )
@Configuration
@ApplicationPath( PATH )
@PropertySource( name = "swaggerProperties", value = "swagger.properties", ignoreResourceNotFound = true )
public class ServerConfig extends ResourceConfig implements EnvironmentAware
{

	public ServerConfig()
	{
		register( JacksonFeature.class );

		// register packages
		packages( "uk.co.bluegecko.core.server" );
	}

	protected BeanConfig swaggerConfig( final Environment environment )
	{
		final BeanConfig beanConfig = new BeanConfig();
		beanConfig.setTitle( environment.getProperty( "swagger.title", "Example Server" ) );
		beanConfig.setDescription( environment.getProperty( "swagger.description", "This is an Example Server" ) );
		beanConfig.setTermsOfServiceUrl( "http://www.bluegecko.co.uk/terms/" );
		beanConfig.setContact( "Caveman Frak" );
		beanConfig.setLicense( "Apache 2.0" );
		beanConfig.setLicenseUrl( "http://www.apache.org/licenses/LICENSE-2.0.html" );
		beanConfig.setHost( "red-dragon.local:" + PORT );
		beanConfig.setBasePath( PATH );
		beanConfig.setVersion( environment.getProperty( "swagger.version", "0.0.0" ) );
		beanConfig.setSchemes( new String[]
			{ "http" } );
		beanConfig.setResourcePackage( "uk.co.bluegecko.core.server.resource" );
		beanConfig.setScan( true );

		register( ApiListingResource.class );
		register( SwaggerSerializers.class );

		return beanConfig;
	}

	@Override
	public void setEnvironment( final Environment environment )
	{
		setApplicationName( environment.getProperty( "swagger.id", "example-server" ) );

		// setup swagger
		registerInstances( swaggerConfig( environment ) );
	}
}